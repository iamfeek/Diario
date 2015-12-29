package authentication;

import DAO.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6ServerSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Syafiq Hanafee
 * Dated: 15/12/15.
 */
public class Challenge extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login Request: " + request.getParameter("username"));
        try {
            handler(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void handler(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        response.setContentType("application/json");
        String username = request.getParameter("username");

        Map<String, BigInteger> saltAndB = new HashMap<String, BigInteger>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        SRP6ServerSession srp = new SRP6ServerSession(SRP6CryptoParams.getInstance());
        User challenger = new User(request.getParameter("username"));
        HashMap<String, String> saltAndVerifier = challenger.getSaltAndVerifier();
        String salt = saltAndVerifier.get("salt");
        String verifier = saltAndVerifier.get("verifier");

        BigInteger saltBI = BigIntegerUtils.fromHex(salt);
        BigInteger verifierBI = BigIntegerUtils.fromHex(verifier);

        BigInteger b = srp.step1(username, saltBI, verifierBI);

        saltAndB.put("salt", saltBI);
        saltAndB.put("b", b);

        System.out.println("Salt from db: " + salt);
        System.out.println("Biginteger Salt .toString(): " + saltBI.toString());

        String saltAndBJson = gson.toJson(saltAndB);
        response.getWriter().write(saltAndBJson);
    }
}

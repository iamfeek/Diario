package authentication;

import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by: Syafiq Hanafee
 * Dated: 15/12/15.
 */
public class Authenticate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Authenticate Value Ready");
        response.setContentType("application/json");

        String username = request.getParameter("username");
        String StringM1 = request.getParameter("M1");
        String StringA = request.getParameter("A");

        BigInteger M1 = BigIntegerUtils.fromHex(StringM1);
        BigInteger A = BigIntegerUtils.fromHex(StringA);

        System.out.println(username);
        System.out.println(M1);
        System.out.println(A);

        SRP6ServerSession srp = new SRP6ServerSession(SRP6CryptoParams.getInstance());
        
        try {
            BigInteger ret  = srp.step2(A, M1);
            System.out.println(ret);
        } catch (SRP6Exception e) {
            e.printStackTrace();
        }
    }
}

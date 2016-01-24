package authentication;

import com.bitbucket.thinbus.srp6.js.SRP6JavascriptServerSession;
import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;
import database.Db;
import org.apache.commons.codec.binary.Hex;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by: Syafiq Hanafee
 * Dated: 15/12/15.
 */
public class Authenticate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/plain");

        //checking if srp object is available
        if(null != session.getAttribute("srp")){
            System.out.println("Authenticate Value Ready");

            String M1 = request.getParameter("M1");
            String A = request.getParameter("A");

            System.out.println("A: " + A);
            System.out.println("M1: " + M1);

            SRP6JavascriptServerSession srp = (SRP6JavascriptServerSession) request.getSession().getAttribute("srp");

            //checking if B of session and db is the same
            if(getB(srp.getUserID()).equals(srp.getPublicServerValue()))
                System.out.println("The same B");
            else
                System.out.println("Not the same B. FIX THIS.");

            System.out.println(srp.getUserID());

            try {
                String M2 = srp.step2(A, M1);
                response.getWriter().write(M2);
            } catch (Exception e) {
                //authentication failed
                System.out.println("AUTH FAILED");
                response.getWriter().write("bad");
            }
        } else{
            response.getWriter().write("Session Error");
        }
        System.out.println("|========> END " + request.getParameter("username")+"'s Request<========|");
    }

    private static String getB(String username){
        String b = null;
        try{
            Connection conn = Db.getConnection();
            String query = "SELECT b FROM b_temp where username = '" + username + "';";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                b = rs.getString("b");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }
}

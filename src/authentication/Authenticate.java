package authentication;

import com.bitbucket.thinbus.srp6.js.SRP6JavascriptServerSession;
import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;
import org.apache.commons.codec.binary.Hex;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;

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
            try {
                String M2 = srp.step2(A, M1);
                response.getWriter().write(M2);
            } catch (Exception e) {
                //authentication failed
                System.out.println("AUTH FAILED");
                response.getWriter().write("Status: 502");
            }
        } else{
            response.getWriter().write("Session Error");
        }
        System.out.println("|========> END " + request.getParameter("username")+"'s Request<========|");
    }
}

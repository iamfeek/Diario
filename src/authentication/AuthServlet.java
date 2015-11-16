package authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

/**
 * Created by: Syafiq Hanafee
 * Dated: 9/11/15.
 */
public class AuthServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String flag = request.getParameter("flag");
        if(flag.equals("login")){
            System.out.println("[AuthServlet] Login Request. Delegating...");
            try {
                LoginHandler.login(request.getParameter("username"), request.getParameter("password"), response);
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(flag.equals("register")){
            try{
                RegistrationHandler.register(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
    }
}

package authentication;

import DAO.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by: Syafiq Hanafee
 * Dated: 15/12/15.
 */
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Registration incoming!");
        PrintWriter pw = response.getWriter();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String salt = request.getParameter("salt");
        String verifier = request.getParameter("verifier");

        User userToCheck = new User(username, email);
        try {
            boolean emailAvailable = userToCheck.checkifEmailExists(userToCheck.getEmail());
            boolean usernameAvailable = userToCheck.checkIfUsernameExists(userToCheck.getUsername());

            if(emailAvailable && usernameAvailable){
                System.out.println("Registration: Username and Email available");
                User toRegister = new User(username, email, salt, verifier);
                boolean registrationStatus = toRegister.register();
                System.out.println("Registration Status: " + registrationStatus);
            } else{
                pw.write("Email or Username Taken");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

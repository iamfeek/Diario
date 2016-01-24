package authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IamFeeK on 25/1/2016.
 */
@WebServlet(name = "SignOut")
public class SignOut extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getSession().setAttribute("loggedIn", false);
         request.getSession().removeAttribute("username");

         response.sendRedirect("/");
    }
}

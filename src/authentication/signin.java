package authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jy on 16-Jan-16.
 */
public class signin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("loggedIn", true);
        request.getSession().setAttribute("username", "tester");

        System.out.println(request.getSession().getAttribute("username"));
        response.sendRedirect("/dashboard");
    }
}

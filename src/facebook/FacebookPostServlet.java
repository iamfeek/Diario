package facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glenice on 8 Dec 2015.
 */
public class FacebookPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        try {
            facebook.postStatusMessage("Test");
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath()+ "/");
    }
}

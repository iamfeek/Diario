package facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.auth.AccessToken;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glenice on 3 Dec 2015.
 */
public class AccessTokenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");

        AccessToken info = facebook.getOAuthAccessToken();
        request.getSession().setAttribute("token", info);


        response.sendRedirect(request.getContextPath() + "/dashboard");

    }
}

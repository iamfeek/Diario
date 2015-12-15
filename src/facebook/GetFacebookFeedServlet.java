package facebook;


import facebook4j.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by glenice on 5 Dec 2015.
 */
public class GetFacebookFeedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Facebook fb = (Facebook) request.getSession().getAttribute("facebook");

            ResponseList<Friend> feed = fb.getFriends();

            List<Friend> fbFriendList = (List) feed;

            request.getSession().setAttribute("fbFriendList", fbFriendList);
            response.sendRedirect("/dashboard");

        }catch (FacebookException ex){
            throw new ServletException(ex);
        }
    }
}

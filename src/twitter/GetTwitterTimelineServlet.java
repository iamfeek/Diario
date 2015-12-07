package twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.Status;
import twitter4j.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gleni on 29 Nov 2015.
 */
public class GetTwitterTimelineServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

            User user = twitter.verifyCredentials();

            List<Status> statusList = twitter.getHomeTimeline();
            request.getSession().setAttribute("twitterTimelineList", statusList);
            response.sendRedirect("/dashboard");

        }catch (TwitterException ex){
            throw new ServletException(ex);
        }


    }


}

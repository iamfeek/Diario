package twitter;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gleni on 23 Jan 2016.
 */
@WebServlet(name = "UserTimelineServlet")
public class UserTimelineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

        request.getSession().setAttribute("userTimelineActive", "active");
        request.getSession().setAttribute("homeTimelineActive", "");
        request.getSession().setAttribute("messagesActive", "");

        List<Status> statusList = null;
        try {
            statusList = twitter.getUserTimeline();

            Paging page = new Paging(2, 40);

            statusList.addAll(twitter.getUserTimeline(page));

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("userTimeline", statusList);
        response.sendRedirect("/dashboard");

    }
}

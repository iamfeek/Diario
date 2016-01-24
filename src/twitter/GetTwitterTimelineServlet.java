package twitter;

import twitter4j.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by glenice on 29 Nov 2015.
 */
public class GetTwitterTimelineServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            request.getSession().setAttribute("homeTimelineActive", "active");
            request.getSession().setAttribute("userTimelineActive", "");
            request.getSession().setAttribute("messagesActive", "");
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

            List<Status> statusList = twitter.getHomeTimeline();

            Paging page = new Paging(2, 40);

            statusList.addAll(twitter.getHomeTimeline(page));

            System.out.println(statusList.get(statusList.size()-1));

            request.getSession().setAttribute("twitterTimelineList", statusList);
            response.sendRedirect("/dashboard");

        }catch (TwitterException ex){
            throw new ServletException(ex);
        }


    }


}

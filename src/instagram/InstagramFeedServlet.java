package instagram;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jinstagram.Instagram;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by gleni on 23 Jan 2016.
 */
@WebServlet(name = "InstagramFeedServlet")
public class InstagramFeedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("InstagramUserTimelineActive", "");
        request.getSession().setAttribute("InstagramHomeTimelineActive", "active");
        request.getSession().setAttribute("InstagramSearch", "");

        Instagram instagram = (Instagram) request.getSession().getAttribute("instagram");

        MediaFeed mf = instagram.getPopularMedia();

        List<MediaFeedData> listMedia = mf.getData();

        request.getSession().setAttribute("instaHomeFeed", listMedia);

        response.sendRedirect("/dashboard");
    }
}

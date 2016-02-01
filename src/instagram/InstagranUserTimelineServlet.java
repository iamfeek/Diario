package instagram;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by glenice on 23 Jan 2016.
 */
@WebServlet(name = "InstagranUserTimelineServlet")
public class InstagranUserTimelineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("InstagramUserTimelineActive", "active");
        request.getSession().setAttribute("InstagramHomeTimelineActive", "");
        request.getSession().setAttribute("InstagramSearch", "");

        Instagram instagram = (Instagram) request.getSession().getAttribute("instagram");

        MediaFeed mf = instagram.getRecentMediaFeed(instagram.getCurrentUserInfo().getData().getId());

        List<MediaFeedData> listMedia = mf.getData();

        for (int i = 0; i < listMedia.size(); i++) {

            Date date = new Date(Long.parseLong(listMedia.get(i).getCreatedTime())*1000);
            SimpleDateFormat df2 = new SimpleDateFormat("dd MMM");
            String dateText = df2.format(date);
            listMedia.get(i).setCreatedTime(dateText);
        }

        request.getSession().setAttribute("userFeed", listMedia);

        response.sendRedirect("/dashboard");
    }
}

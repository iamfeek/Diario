package instagram;

import org.jinstagram.Instagram;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.entity.users.feed.UserFeedData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.List;

/**
 * Created by gleni on 23 Jan 2016.
 */
@WebServlet(name = "SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Instagram instagram = (Instagram) request.getSession().getAttribute("instagram");

        request.getSession().setAttribute("InstagramUserTimelineActive", "");
        request.getSession().setAttribute("InstagramHomeTimelineActive", "");
        request.getSession().setAttribute("InstagramSearch", "active");

        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");

        UserFeed userFeed = instagram.searchUser(text);

        List<UserFeedData> userFeedDatas = userFeed.getUserList();

        System.out.print(userFeedDatas.get(0).getUserName());

        request.getSession().setAttribute("searchResults", userFeedDatas);

        response.sendRedirect("/dashboard");
    }
}

package instagram;

import DAO.InstagramDAO;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by glenice on 8 Dec 2015.
 */

public class InstagramCallbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        InstagramService service = (InstagramService) request.getSession().getAttribute("instagramService");

        Verifier verifier = new Verifier(code);

        Token accessToken = service.getAccessToken(null, verifier);

        Instagram instagram = new Instagram(accessToken);

        request.getSession().setAttribute("instagramUserInfo", instagram.getCurrentUserInfo().getData());

        InstagramDAO.saveToken(accessToken,(String) request.getSession().getAttribute("username"));

        request.getSession().setAttribute("instagram", instagram);

        response.sendRedirect("/instagramUserTimeline");
    }
}

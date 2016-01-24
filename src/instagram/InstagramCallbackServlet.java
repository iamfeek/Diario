package instagram;

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

        System.out.println("==========================");
        System.out.print(accessToken);

        Instagram instagram = new Instagram(accessToken);

        request.getSession().setAttribute("instagramUserInfo", instagram.getCurrentUserInfo().getData());
        /*for(int i =0;i<feed.size();i++){
            reader = new JsonReader(new StringReader(feed.get(i).getImages().getLowResolution().getImageUrl()));

            reader.setLenient(true);

            MediaFeedData mdf = gson.fromJson(reader, MediaFeedData.class);

            feed.set(i, mdf);

        }

        request.getSession().setAttribute("instagramfeed", feed);
*/
        request.getSession().setAttribute("instagram", instagram);

        response.sendRedirect("/instagramUserTimeline");
    }
}

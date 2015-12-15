package instagram;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by gleni on 8 Dec 2015.
 */
public class InstagramLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Properties props = new Properties();
        props.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

        String apikey = (String) props.get("instagram.clientId");
        String apisecret = (String) props.get("instagram.clientSecret");
        String callback = (String) props.get("instagram.redirectUri");

        InstagramService service = new InstagramAuthService().apiKey(apikey).apiSecret(apisecret).callback(callback).build();

        String authorizationURL = service.getAuthorizationUrl(null);
        request.getSession().setAttribute("instagramService",service);

        response.sendRedirect(authorizationURL);
    }
}

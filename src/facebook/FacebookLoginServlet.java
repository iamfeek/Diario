package facebook;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by glenice on 3 Dec 2015.
 */
public class FacebookLoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Facebook fb = new FacebookFactory().getInstance();

        Properties props = new Properties();
        props.load(getServletContext().getResourceAsStream("/WEB-INF/facebook4j.properties"));
        String app_id =
                (String)props.get("oauth.appId");
        String app_secret =
                (String)props.get("oauth.appSecret");


        fb.setOAuthAppId(app_id,app_secret);

        request.getSession().setAttribute("facebook",fb);

        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index,callbackURL.length(),"").append("/facebookcallback");
        response.sendRedirect(fb.getOAuthAuthorizationURL(callbackURL.toString()));
    }
}

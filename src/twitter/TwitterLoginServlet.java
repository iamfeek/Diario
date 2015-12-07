package twitter;

/**
 * Created by glenice on 29 Nov 2015.
 * Description: Login using Twitter
 *
 *
 * asdfgh
 */

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;


public class TwitterLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Properties props = new Properties();
        props.load(getServletContext().getResourceAsStream("/WEB-INF/twitter4j.properties"));
        String consumer_key =
                (String)props.get("twitter4j.oauth.consumerKey");
        String consumer_secret =
                (String)props.get("twitter4j.oauth.consumerSecret");

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumer_key, consumer_secret);
        req.getSession().setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = req.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(),
                    "").append("/twittercallback");
            RequestToken requestToken =
                    twitter.getOAuthRequestToken(callbackURL.toString());
            req.getSession().setAttribute("requestToken", requestToken);

            resp.sendRedirect(requestToken.getAuthenticationURL());

        } catch (TwitterException e) {
            resp.sendRedirect(req.getContextPath() + "/twitterlogout");
        }
    }
}
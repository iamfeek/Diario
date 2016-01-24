package twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glenice on 29 Nov 2015.
 * Description: This is the callback servlet for Twitter login
 */

public class TwitterCallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        if(req.getParameter("denied") != null) {
            resp.sendRedirect(req.getContextPath() + "/twitterlogout");
        }
        else {
            Twitter twitter = (Twitter)
                    req.getSession().getAttribute("twitter");
            RequestToken requestToken = (RequestToken)
                    req.getSession().getAttribute("requestToken");
            String verifier = req.getParameter("oauth_verifier");
            try {
                twitter.getOAuthAccessToken(requestToken, verifier);

                System.out.println(requestToken);
                System.out.println(verifier);

                req.getSession().removeAttribute("requestToken");
            } catch (TwitterException e) {
                resp.sendRedirect(req.getContextPath() + "/twitterlogout");
            }
            resp.sendRedirect(req.getContextPath() + "/twitterTimeline");
        }
    }
}

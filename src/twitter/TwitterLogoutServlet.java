package twitter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gleni on 29 Nov 2015.
 * Description: Logout from twitter
 */
public class TwitterLogoutServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
        }
}

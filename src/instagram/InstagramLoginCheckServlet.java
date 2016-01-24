package instagram;

import DAO.InstagramDAO;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gleni on 24 Jan 2016.
 */
@WebServlet(name = "InstagramLoginCheckServlet")
public class InstagramLoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");

        Token token = InstagramDAO.getToken(username);

        if(token == null){
            System.out.println("Null");
            response.sendRedirect("/instagramLogin");
        }
        else {
            Instagram instagram = new Instagram(token);

            request.getSession().setAttribute("instagram", instagram);

            response.sendRedirect("/instagramUserTimeline");

        }

    }
}

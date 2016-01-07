package friends;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 1/3/2016.
 */
@WebServlet(urlPatterns = {"/unfriend"})
public class btnUnFriendServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public btnUnFriendServlet (){
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("unfriend"));
        FriendsBean eb = new FriendsBean();
        eb.unfriend(id);
        response.sendRedirect("scripts/friendslist.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}

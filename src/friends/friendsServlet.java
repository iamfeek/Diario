package friends;


import DAO.Friend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 12/15/2015.
 */
@WebServlet(urlPatterns = {"/friend"})
public class friendsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public friendsServlet() { super(); }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendsBean fb = new FriendsBean();
        List<Friend> f = fb.getFriends();
        request.setAttribute("f", f);
        response.sendRedirect("friendslist.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}

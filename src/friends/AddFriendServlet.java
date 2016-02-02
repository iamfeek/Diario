package friends;

import DAO.Friend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 1/7/2016.
*/
@WebServlet(urlPatterns = {"/addFriend"})

public class AddFriendServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddFriendServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend f = new Friend();
        f.setId(Integer.parseInt(request.getParameter("id")));

        f.setF_username(request.getParameter("fUNAME"));
        f.setLocation(request.getParameter("location"));
        f.setMF(request.getParameter("MF"));
        FriendsBean fb = new FriendsBean();
        fb.addFriend(f);
        response.sendRedirect("profile.jsp");

       //request.getRequestDispatcher("friendslist.jsp").forward(request, response);


    }
}

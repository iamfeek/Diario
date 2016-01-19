package friends;
import DAO.Friend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by lenovo on 12/31/2015.
 */
@WebServlet(urlPatterns = {"/view"})
public class btnViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public btnViewServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("view"));
        FriendsBean bean = new FriendsBean();
        Friend fr = bean.getFriend(id);
        request.setAttribute("nono", fr);
        request.getRequestDispatcher("friendslist.jsp").forward(request, response);
       // response.sendRedirect("profile.jsp"); set (if else)

    }


}

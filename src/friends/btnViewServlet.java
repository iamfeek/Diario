package friends;
import DAO.Friend;

import javax.servlet.RequestDispatcher;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("user");
        System.out.println("View ID: " + username);
        FriendsBean bean = new FriendsBean();
        Friend fr = bean.getFriend(username);
        request.setAttribute("nono", fr);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);
        //response.sendRedirect("profile.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }


}

package post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;

import DAO.DAOPost;
import DAO.KeyGen;

/**
 * Created by Jy on 10-Dec-15.
 */
public class postSystem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") == null)   {
            response.sendRedirect("/");
            return;
        }

        String username = (String) request.getSession().getAttribute("username");
        String text = request.getParameter("text");
        int seculvl = Integer.parseInt(request.getParameter("text_secu_level"));
        if (seculvl == 0) {
            DAOPost.storeMessage(username, text, false);
        }
        else if (seculvl == 50)    {
            DAOPost.storeMessage(username, text, true);
        }
        //TODO: Share with friends
        else    {

        }
        response.sendRedirect("/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedIn") != null)  {
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/post.jsp");
            rd.forward(request, response);
        }
        else
            response.sendRedirect("/");
    }
}

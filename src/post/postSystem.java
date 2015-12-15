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

        if (Integer.parseInt(request.getParameter("secu_level")) > 0) {

            PublicKey pk = KeyGen.getPubKey(username);
            String encrypted = Encryption.encrypt(request.getParameter("text"), pk);
            DAOPost.storeMessage(username, encrypted, true);
        }
        else    {
            DAOPost.storeMessage(username, request.getParameter("text"), false);
        }
        response.sendRedirect("/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/post.jsp");
        rd.forward(request, response);
    }
}

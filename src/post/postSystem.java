package post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import DAO.DAOPost;
import sentiment.sentimentAnalysis;
import sentiment.sentimentDAO;

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
            System.out.println("User " + username + " posting unencrypted message");
            int postid = DAOPost.storeMessage(username, text, false, true);
            sentimentAnalysis.analysis((String)request.getSession().getAttribute("username"), text, postid);
            request.getSession().setAttribute("sentResults", sentimentDAO.getSentiment(postid));
        }
        else if (seculvl == 50)    {
            System.out.println("User " + username + " posting encrypted message");
            DAOPost.storeMessage(username, text, true);
            request.getSession().setAttribute("sentResults", null);
        }
        else    {
            String str_selected_friends = request.getParameter("selected_friends");
            String[] selected_friends = str_selected_friends.split(",");

            String AESKey = Encryption.generateBase64AESKey();
            String encryptedPost = Encryption.encryptWithAES(text, AESKey);

            int postid = DAOPost.storeMessage(username, encryptedPost, true, true);
            DAOPost.storeAESKey(postid, username, Encryption.generateAESxRSAKeyForUser(username, AESKey));
            System.out.print("User " + username + " posted encrypted message shared with: ");
            for (int i = 0; i < selected_friends.length; i++)   {
                System.out.print(selected_friends[i] + ", ");
                DAOPost.storeAESKey(postid, selected_friends[i], Encryption.generateAESxRSAKeyForUser(selected_friends[i], AESKey));
            }
            sentimentAnalysis.analysis((String)request.getSession().getAttribute("username"), text, postid);
            request.getSession().setAttribute("sentResults", sentimentDAO.getSentiment(postid));
            System.out.println();
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Posted!');");
        out.println("</script>");
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

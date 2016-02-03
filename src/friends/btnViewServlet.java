package friends;
import DAO.Friend;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        String viewParams = request.getParameter("view");
        System.out.println(viewParams);

        if (request.getParameter("view") != null) {
            forwardRequest(request, response, Integer.parseInt(request.getParameter("view").toString()));
        }else{
            int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString());
            forwardRequest(request, response, userid);
        }
    }

    protected void forwardRequest(HttpServletRequest request, HttpServletResponse response, int userid)
            throws ServletException, IOException{
        System.out.println("View ID: " + userid);
        FriendsBean bean = new FriendsBean();
        Friend fr = bean.getFriend(userid);
        request.setAttribute("nono", fr);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);
        //response.sendRedirect("profile.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }


}

package Sentiment;

/*import org.python.core.PyObject;
import org.python.netty.util.internal.SystemPropertyUtil;
import org.python.util.PythonInterpreter;*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glenice on 10 Jan 2016.
 */
public class jythonTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("post");

        // Constructor pass in username, text and PostID
        sentimentAnalysis.analysis((String)request.getSession().getAttribute("username"), text, 1);

        request.getSession().setAttribute("sentText", text);

        request.getSession().setAttribute("sentResults", sentimentDAO.getSentiment(1));

        response.sendRedirect(request.getContextPath()+ "/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

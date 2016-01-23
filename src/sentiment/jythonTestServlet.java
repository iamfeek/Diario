package sentiment;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.python.core.PyObject;
import org.python.netty.util.internal.SystemPropertyUtil;
import org.python.util.PythonInterpreter;

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

        PythonInterpreter interp = new PythonInterpreter();
        System.out.println("Testing vader");
        interp.exec("from vaderSentiment.vaderSentiment import sentiment as vaderSentiment");
        interp.exec("text = \"" + text + "\"");
        interp.exec("vader = vaderSentiment(text)");
        PyObject res = interp.get("vader");

        request.getSession().setAttribute("sentAnalysis", res);

        //JSONParser parse = new JSONParser();
        //try {
          //  JSONObject json = (JSONObject)  parse.parse(res.toString());

            //String compound = (String) json.get("compoung");

            //request.getSession().setAttribute("compoung", compound);

        //} catch (ParseException e) {
          //  e.printStackTrace();
        //}


        request.getSession().setAttribute("sentText", text);

        response.sendRedirect(request.getContextPath()+ "/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

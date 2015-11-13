package authentication;

import java.io.IOException;

/**
 * Created by: Syafiq Hanafee
 * Dated: 9/11/15.
 */
public class AuthServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String flag = request.getParameter("flag");
        if(flag.equals("login")){
            System.out.println("[AuthServlet] Request wants to login in. Sending to handler.");
            Login.login(response, request.getParameter("name"));
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Auth Status Here.");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}

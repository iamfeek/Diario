package authentication;

import DAO.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by: Syafiq Hanafee
 * Dated: 9/11/15.
 */


public class AuthServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String flag = request.getParameter("flag");
        System.out.println("[AuthServlet] Delegating.....");

        try {
            if (flag.equals("login")) {
                User toLogin = new User(request.getParameter("username"), request.getParameter("password"));
                if(toLogin.login()){
                    Cookie userCookie = new Cookie("User", toLogin.getUsername());
                    response.addCookie(userCookie);
                    response.sendRedirect("dashboard");
                } else {
                    response.setContentType("text/html");
                    response.getWriter().write("error");
                }
            } else if(flag.equals("register")){
                User toRegister = new User(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
                if(toRegister.register()){
                    User tologin = new User(toRegister.getUsername(), toRegister.getPassword());
                    if(tologin.login()){
                        System.out.println("LOGGING IN");
                        Cookie userCookie = new Cookie("User", tologin.getUsername());
                        response.addCookie(userCookie);
                        response.sendRedirect("dashboard");
                    }
                }
            } else if(flag.equals("logout")){
                Cookie ck=new Cookie("User","");
                ck.setMaxAge(0);
                response.addCookie(ck);
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", true);
                response.sendRedirect("/");
                System.out.println("Logout Request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

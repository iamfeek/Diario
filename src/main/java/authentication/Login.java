package authentication;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * Created by: Syafiq Hanafee
 * Dated: 13/11/15.
 */
public class Login {
    static void login(javax.servlet.http.HttpServletResponse response, String name, String password) throws IOException{
        System.out.println("[Login] Username: " + name);
        System.out.println("[Login] Password: " + password);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");




        if(name.equals("me@iamfeek.com")){
            //creating cookie for session
            Cookie loginCookie = new Cookie("name", name);
            response.addCookie(loginCookie);
            response.sendRedirect("/");
        }
    }
}

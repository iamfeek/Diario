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

        //setting the response type
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");


        if(name.equals("me@iamfeek.com")){
            response.sendRedirect("http://google.com");
            System.out.println("name is legit");
            //creating cookie for session
            Cookie loginCookie = new Cookie("name", name);
            response.addCookie(loginCookie);
            response.sendRedirect("index");
        } else
            System.out.println("name is not legit");
    }
}

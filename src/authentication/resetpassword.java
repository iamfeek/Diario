package authentication;

import DAO.User;
import com.ziclix.python.sql.connect.Connect;
import database.Db;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IamFeeK on 25/1/2016.
 */
@WebServlet(name = "reset-password")
public class ResetPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reset Request for: " + request.getParameter("username") + request.getParameter("email"));
        PrintWriter pw = response.getWriter();
        pw.write("");
        int stage = Integer.parseInt(request.getParameter("stage"));
        if (stage == 1) {
            System.out.println("Stage 1 reset");
            try {
                boolean sendResetEmailStatus = sendResetEmail(request);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(stage == 2){

        }
    }



    public static boolean sendResetEmail(HttpServletRequest request) throws ServletException, IOException, MessagingException, SQLException {
        String host = "smtp.gmail.com";
        String from = "diarionyp@gmail.com";
        String pass = "Hanafee1804";
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String recipient = getEmail(username, email);
        if(recipient != null) {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            String[] to = {recipient}; // added this line

            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) { // changed from a while loop
                toAddress[i] = new InternetAddress(to[i]);
            }


            for (int i = 0; i < toAddress.length; i++) { // changed from a while loop
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject("Diario - Reset your password");
            int rand = (int) (Math.random() * (99999999 - 11111111) + 11111111);
            message.setText("Please enter this digits: " + rand + ". Or you can follow this link. https://localhost:8443/reset-password-now?username=iamfeek&email="+email+"&key=" + rand);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            saveToDb(username, rand);
        } else
            return false;
        return false;
    }

    public static void saveToDb(String username, int rand) throws SQLException {
        if (removeFromDb(username)) {
            Connection conn = Db.getConnection();
            //generating the sqls and stuff before executeUpdate
            String sql = "INSERT INTO reset (username, rand) VALUES (?, ?);";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setInt(2, rand);

            int status = preparedStmt.executeUpdate();
            conn.close();

            if (status == 1) {
                System.out.println("Saved Rand to DB");
            } else {
                System.out.println("SAVE FAILED");

            }
        }
    }

    private static boolean removeFromDb(String username) {
        try {
            Connection conn = Db.getConnection();
            String query = "delete FROM reset where username = ?;";

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println("cleared");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getEmail(String username, String userEmail) throws SQLException {
        String email = "";
        User user = new User(username);
        email = user.getEmail();
        if(userEmail.equals(email))
            return email;
        else
            return null;
    }

    public static void main(String[] args) {
        try {
            sendResetEmail(null);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

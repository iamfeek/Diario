package authentication;

import DAO.Key;
import DAO.User;
import database.Db;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by: Syafiq Hanafee
 * Dated: 15/12/15.
 */
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Registration incoming!");
        PrintWriter pw = response.getWriter();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String salt = request.getParameter("salt");
        String verifier = request.getParameter("verifier");
        String pubkey = request.getParameter("pubkey");

        User userToCheck = new User(username, email);
        try {
            boolean emailAvailable = userToCheck.checkifEmailExists(userToCheck.getEmail());
            boolean usernameAvailable = userToCheck.checkIfUsernameExists(userToCheck.getUsername());

            if (emailAvailable && usernameAvailable) {
                System.out.println("Registration: Username and Email available");
                User toRegister = new User(username, email, salt, verifier);
                boolean registrationStatus = toRegister.register();
                if (registrationStatus) {
                    System.out.println("Received public key:");
                    System.out.println(pubkey);
                    Key.store(username, pubkey);
                }
                System.out.println("Registration Status: " + registrationStatus);
                pw.write("done");
                sendVerificationEmail(email);
//                request.getSession().setAttribute("username", username);
//                request.getSession().setAttribute("loggedIn", true);
            } else {
                pw.write("Email or Username Taken");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sendVerificationEmail(String email) throws MessagingException, SQLException {
        String host = "smtp.gmail.com";
        String from = "diarionyp@gmail.com";
        String pass = "Hanafee1804";

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        String[] to = {email}; // added this line

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
        message.setSubject("Diario - Verify Your Account");
        String token = new SessionIdentifierGenerator().nextSessionId();

        message.setContent(
                "<h1>Verify your Diario Account</h1></br>" +
                        "Thank you for creating an account with us. Verify now and gain access to Diario!"+
                        "<br><table cellspacing='0' cellpadding='0'>\n" +
                        "<tr>\n" +
                        "<td align='center' width='300' height='40' bgcolor='#000091' style='-webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; color: #ffffff; display: block;'>\n" +
                        "<a href='https://localhost:8443/verify?email="+email+"&token="+token+"' style='font-size:16px; font-weight: bold; font-family: Helvetica, Arial, sans-serif; text-decoration: none; line-height:40px; width:100%; display:inline-block'><span style='color: #FFFFFF'>Verify Your Account</span></a>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</table>",
                "text/html");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

        saveToDb(email, token);
    }

    public static void saveToDb(String email, String token) throws SQLException {
        Connection conn = Db.getConnection();
        //generating the sqls and stuff before executeUpdate
        String sql = "INSERT INTO verify (email_address, token) VALUES (?, ?);";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString(1, email);
        preparedStmt.setString(2, token);

        int status = preparedStmt.executeUpdate();
        conn.close();

        if (status == 1) {
            System.out.println("Saved token to DB");
        } else {
            System.out.println("SAVE FAILED");
        }
    }

    private static String getEmail(String username, String userEmail) throws SQLException {
        String email = "";
        User user = new User(username);
        email = user.getEmail();
        if (userEmail.equals(email))
            return email;
        else
            return null;
    }

}

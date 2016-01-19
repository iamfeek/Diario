import DAO.Key;
import org.apache.commons.codec.binary.Base64;
import post.Encryption;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;


/**
 * Created by Jy on 16-Jan-16.
 */
public class tester extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String plainText = "Hello World!";
        response.getWriter().println("Plain Text: " + plainText);
        String key = Encryption.generateBase64AESKey();
        response.getWriter().println("Base 64 AES Key: " + key);
        response.getWriter().println("Encrypted with AES Key: " + Encryption.encryptWithAES(plainText,key));
        response.getWriter().println("RSAxAES Key for tester: " + Encryption.generateAESxRSAKeyForUser("tester", key));
    }
}

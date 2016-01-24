
import DAO.DAOImages;
import post.Watermarking;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jy on 16-Jan-16.
 */
public class tester extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage image = DAOImages.getImage(1);
        Watermarking.watermarkEntire(image, "HELLO", 20, 0.1f);
        byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }
}

package post;

import DAO.DAOImages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jy on 16-Jan-16.
 */
public class getImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DAOImages.checkForOwnership(Integer.parseInt(request.getParameter("imgid")), request.getSession().getAttribute("username").toString())) {
            BufferedImage image = DAOImages.getImage(Integer.parseInt(request.getParameter("imgid")));
            Watermarking.watermarkEntire(image, "Diario", 20, 0.1f);
            byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
            response.setContentType("image/jpeg");
            response.setContentLength(imageBytes.length);
            response.getOutputStream().write(imageBytes);
        }
    }
}

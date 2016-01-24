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
        int imgid = Integer.parseInt(request.getParameter("imgid"));
        if (DAOImages.checkForOwnership(imgid, request.getSession().getAttribute("username").toString())) {
            int secu = DAOImages.getImageSecu(imgid);
            if (secu == 0)  {
                BufferedImage image = DAOImages.getImage(Integer.parseInt(request.getParameter("imgid")));
                byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
                response.setContentType("image/jpeg");
                response.setContentLength(imageBytes.length);
                response.getOutputStream().write(imageBytes);
            }
            else if (secu == 25)    {
                BufferedImage image = DAOImages.getImage(Integer.parseInt(request.getParameter("imgid")));
                Watermarking.watermarkLogo(image);
                byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
                response.setContentType("image/jpeg");
                response.setContentLength(imageBytes.length);
                response.getOutputStream().write(imageBytes);
            }
            else if (secu == 50)    {
                BufferedImage image = DAOImages.getImage(Integer.parseInt(request.getParameter("imgid")));
                Watermarking.watermarkLogo(image);
                Watermarking.watermarkText(image, DAOImages.getImageOwner(imgid));
                byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
                response.setContentType("image/jpeg");
                response.setContentLength(imageBytes.length);
                response.getOutputStream().write(imageBytes);
            }
            else if (secu == 75)    {
                BufferedImage image = DAOImages.getImage(Integer.parseInt(request.getParameter("imgid")));
                Watermarking.watermarkLogo(image);
                Watermarking.watermarkText(image, "Owner: " + DAOImages.getImageOwner(imgid) + " Viewer: " + request.getSession().getAttribute("username").toString());
                byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
                response.setContentType("image/jpeg");
                response.setContentLength(imageBytes.length);
                response.getOutputStream().write(imageBytes);
            }
            else    {
                BufferedImage image = DAOImages.getImage(Integer.parseInt(request.getParameter("imgid")));
                Watermarking.watermarkLogo(image);
                Watermarking.watermarkEntire(image, "Owner: " + DAOImages.getImageOwner(imgid) + " Viewer: " + request.getSession().getAttribute("username").toString());
                byte[] imageBytes = Watermarking.encodeJPEG(image, 100);
                response.setContentType("image/jpeg");
                response.setContentLength(imageBytes.length);
                response.getOutputStream().write(imageBytes);
            }
        }
    }
}

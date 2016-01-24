package DAO;

import database.Db;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Jy on 17-Jan-16.
 */
public class DAOImages {
    public static int postImage(String username, int secu, InputStream imageStream) {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`images` (username, img, secu) VALUES (?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, username);
            preparedStmt.setBlob(2, imageStream);
            preparedStmt.setInt(3, secu);
            int status = preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            int imgid = -1;
            if (rs != null && rs.next()) {
                imgid = rs.getInt(1);
            }
            conn.close();
            return imgid;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean checkForOwnership(int imgid, String username)  {
        Connection conn = Db.getConnection();
        String query = "SELECT username FROM diario.`images` where imgid = '" + imgid + "';";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            String retrieved = rs.getString("username");
            conn.close();
            if (retrieved.equals(username))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Integer> getImageIds(String username)    {
        Connection conn = Db.getConnection();
        String query = "SELECT imgid FROM diario.`images` where username = '" + username + "';";

        ArrayList<Integer> ids = new ArrayList<Integer>();

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())   {
                ids.add(rs.getInt("imgid"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static BufferedImage getImage(int imgid) {
        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.`images` where imgid = '" + imgid + "';";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            byte[] imgBytes = rs.getBytes("img");
            InputStream in = new ByteArrayInputStream(imgBytes);
            BufferedImage img = ImageIO.read(in);
            conn.close();
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getImageSecu(int imgid) {
        Connection conn = Db.getConnection();
        String query = "SELECT secu FROM diario.`images` where imgid = '" + imgid + "';";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            int secu = rs.getInt("secu");
            conn.close();
            return secu;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getImageOwner(int imgid) {
        Connection conn = Db.getConnection();
        String query = "SELECT username FROM diario.`images` where imgid = '" + imgid + "';";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            String username = rs.getString("username");
            conn.close();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

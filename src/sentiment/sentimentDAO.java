package sentiment;

import DAO.SentimentResult;
import database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gleni on 24 Jan 2016.
 */
public class sentimentDAO {

    public static boolean saveSentiment(int postID, Double pos, Double neg, Double neu, Double compound, String username) {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`sentimentanalysis` (idPost, pos, neu, neg, compound, username) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, postID);
            preparedStmt.setDouble(2, pos);
            preparedStmt.setDouble(3, neu);
            preparedStmt.setDouble(4, neg);
            preparedStmt.setDouble(5, compound);
            preparedStmt.setString(6, username);

            preparedStmt.executeUpdate();

            conn.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static SentimentResult getSentiment(int idPost) {
        SentimentResult result = new SentimentResult();

        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.`sentimentanalysis` where idPost = '" + idPost + "';";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            result.setIdPost(idPost);
            result.setCompound(rs.getDouble("compound"));
            result.setNeg(rs.getDouble("neg"));
            result.setNeu(rs.getDouble("neu"));
            result.setPos(rs.getDouble("pos"));

            conn.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

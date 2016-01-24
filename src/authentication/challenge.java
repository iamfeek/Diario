package authentication;

import DAO.User;
import com.bitbucket.thinbus.srp6.js.SRP6JavascriptServerSessionSHA256;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;
import database.Db;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Syafiq Hanafee
 * Dated: 15/12/15.
 */
public class Challenge extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("|========> " + request.getParameter("username")+"'s Request<========|");
        try {
            handler(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void handler(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        response.setContentType("application/json");
        String username = request.getParameter("username");

        Map<String, String> saltAndB = new HashMap<String, String>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        String N = "21766174458617435773191008891802753781907668374255538511144643224689886235383840957210909013086056401571399717235807266581649606472148410291413364152197364477180887395655483738115072677402235101762521901569820740293149529620419333266262073471054548368736039519702486226506248861060256971802984953561121442680157668000761429988222457090413873973970171927093992114751765168063614761119615476233422096442783117971236371647333871414335895773474667308967050807005509320424799678417036867928316761272274230314067548291133582479583061439577559347101961771406173684378522703483495337037655006751328447510550299250924469288819";
        String g = "2";
        SRP6JavascriptServerSessionSHA256 srp = new SRP6JavascriptServerSessionSHA256(N, g);

        User challenger = new User(request.getParameter("username"));

        //getting salt and verifier in hashmap from DB
        HashMap<String, String> saltAndVerifier = challenger.getSaltAndVerifier();
        System.out.println("SAV: " + saltAndVerifier);
        if(saltAndVerifier != null) {
            String salt = saltAndVerifier.get("salt");
            String verifier = saltAndVerifier.get("verifier");

            try {
                srp.step1(username, salt, verifier);

                saltAndB.put("salt", salt);
                saltAndB.put("b", srp.getPublicServerValue());
                storeB(srp.getUserID(), srp.getPublicServerValue());


            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("bad");
            }
            String saltAndBJson = gson.toJson(saltAndB);

            System.out.println("Salt And B Reply");
            response.getWriter().write(gson.toJson(saltAndBJson));
            System.out.println("Response to Client: " + saltAndBJson.toString());
            HttpSession session = request.getSession();
            session.setAttribute("srp", srp);
        } else {
            System.out.println("Sending out a bad response");
            response.getWriter().write("{status: bad}");
        }
    }

    private static void storeB(String username, String b){
        if(!bIsThere(username)){
            try{
                Connection conn = Db.getConnection();

                //generating the sqls and stuff before executeUpdate
                String sql = "INSERT INTO b_temp (username, b) VALUES (?, ?);";
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, username);
                preparedStmt.setString(2, b);

                int status = preparedStmt.executeUpdate();
                if(status == 1)
                    System.out.println("Saved B");
                else
                    System.out.println("NOT Saved B");
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else{
            removeB(username);
            storeB(username, b);
        }

    }

    private static boolean bIsThere(String username){
        String retrievedUsername = null;
        try{
            Connection conn = Db.getConnection();
            String query = "SELECT username FROM b_temp where username = '" + username + "';";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                retrievedUsername = rs.getString("username");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        if(retrievedUsername != null)
            return true;
        else
            return false;
    }

    private static void removeB(String username){
        try{
            Connection conn = Db.getConnection();
            String query = "delete FROM b_temp where username = ?;";

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println("B has been deleted.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

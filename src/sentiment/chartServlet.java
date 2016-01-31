package sentiment;

import DAO.DAOPost;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import post.Post;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gleni on 30 Jan 2016.
 */
@WebServlet(name = "chartServlet")
public class chartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userTimeline") == null) {
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

            List<Status> statusList = null;
            try {
                statusList = twitter.getUserTimeline();

            } catch (TwitterException e) {
                e.printStackTrace();
            }

            request.getSession().setAttribute("userTimeline", statusList);
        }

        List<analyzedData> twitterresults = moodGenerator.generateMoodFromTwitter((List<Status>) request.getSession().getAttribute("userTimeline"));

        List<analyzedData> postresults = moodGenerator.generateMoodFromPost((ArrayList<Post>) DAOPost.getPosts((String) request.getSession().getAttribute("username")));


        ArrayList<Double> compound = new ArrayList<Double>();
        ArrayList<String> dateList = new ArrayList<String>();

        twitterresults.addAll(postresults);
        Collections.sort(twitterresults);


        /*for(int i = 0;i<twitterresults.size(); i++){
            if(i != 0){
                if(twitterresults.get(i).getDate().equals(twitterresults.get(i+1).getDate())){
                    try {
                        JSONObject json = (JSONObject) new JSONParser().parse(twitterresults.get(i).getText());
                        JSONObject json2 = (JSONObject) new JSONParser().parse(twitterresults.get(i+1).getText());

                        Double com = ((Double) json.get("compond") + (Double) json2.get("compound"))/2;

                        json.replace("compound",com);


                        twitterresults.remove(i-1);
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(twitterresults.size() >= i+1){
                break;
            }
        }*/


        SimpleDateFormat format = new SimpleDateFormat("dd MMM");

        for (int i = twitterresults.size() - 1; i > 0; i--) {

            try {
                JSONObject json = (JSONObject) new JSONParser().parse(twitterresults.get(i).getText());

                Double com = (Double) json.get("compound");

                if (dateList.contains("\'" + format.format(twitterresults.get(i).getDate()) + "\'")) {
                    int index = dateList.indexOf("\'" + format.format(twitterresults.get(i).getDate()) + "\'");
                    compound.set(index, (compound.get(index) + com * 100) / 2);
                } else {
                    compound.add(com * 100);
                    dateList.add("\'" + format.format(twitterresults.get(i).getDate()) + "\'");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        request.getSession().setAttribute("dateList", dateList);
        request.getSession().setAttribute("compound", compound);

        response.sendRedirect("/mood");
    }
}

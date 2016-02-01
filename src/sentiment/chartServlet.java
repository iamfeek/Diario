package sentiment;

import DAO.DAOPost;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import post.Post;
import twitter4j.Paging;
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

        String value = request.getParameter("value");

        if (request.getSession().getAttribute("twitter") == null) {
            response.sendRedirect("/twitterlogin");
        }

        ArrayList<Double> compound = new ArrayList<Double>();
        ArrayList<String> dateList = new ArrayList<String>();

        if (value == null || value.equals("refresh")|| request.getSession().getAttribute("dateListFull") == null || request.getSession().getAttribute("compoundFull") == null) {

            if (request.getSession().getAttribute("userTimeline") == null) {
                Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

                List<Status> statusList = null;
                try {
                    statusList = twitter.getUserTimeline();

                    Paging page = new Paging(2, 40);

                    statusList.addAll(twitter.getUserTimeline(page));

                } catch (TwitterException e) {
                    e.printStackTrace();
                }

                request.getSession().setAttribute("userTimeline", statusList);
            }

            List<analyzedData> twitterresults = moodGenerator.generateMoodFromTwitter((List<Status>) request.getSession().getAttribute("userTimeline"));

            List<analyzedData> postresults = moodGenerator.generateMoodFromPost((ArrayList<Post>) DAOPost.getPosts((String) request.getSession().getAttribute("username")));


            twitterresults.addAll(postresults);
            Collections.sort(twitterresults);


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

            request.getSession().setAttribute("dateListFull", dateList);
            request.getSession().setAttribute("compoundFull", compound);
            request.getSession().setAttribute("dateList", dateList);
            request.getSession().setAttribute("compound", compound);

        }

        if(value != null) {

            compound = (ArrayList<Double>) request.getSession().getAttribute("compoundFull");
            dateList = (ArrayList<String>) request.getSession().getAttribute("dateListFull");

            if (!value.equals("refresh")) {

                ArrayList<Double> compoundsub = new ArrayList<Double>();
                ArrayList<String> dateListsub = new ArrayList<String>();

                for (int i = 0; i < dateList.size(); i++) {
                    if (dateList.get(i).contains(value)) {
                        dateListsub.add(dateList.get(i));
                        compoundsub.add(compound.get(i));
                    }
                }

                request.getSession().setAttribute("dateList", dateListsub);
                request.getSession().setAttribute("compound", compoundsub);
            }
        }

        response.sendRedirect("/mood");
    }
}

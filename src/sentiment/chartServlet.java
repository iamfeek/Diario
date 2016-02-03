package sentiment;

import DAO.DAOPost;
import org.jinstagram.entity.users.feed.MediaFeedData;
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

        //check if usertimeline is retrieved
        if (request.getSession().getAttribute("userTimeline") == null) {
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

            List<Status> statusList = null;
            try {
                statusList = twitter.getUserTimeline();

                Paging page = new Paging(2, 40);

                statusList.addAll(twitter.getUserTimeline(page));

            } catch (TwitterException e) {
                response.sendRedirect("/twitterlogin");
            }

            request.getSession().setAttribute("userTimeline", statusList);
        }



        //Get all data
        List<analyzedData> allResults = moodGenerator.generateMoodFromTwitter((List<Status>) request.getSession().getAttribute("userTimeline"));
        allResults.addAll(moodGenerator.generateMoodFromPost(DAOPost.getPosts(String.valueOf(request.getSession().getAttribute("username")))));
        allResults.addAll(moodGenerator.instagramToData((List<MediaFeedData>) request.getSession().getAttribute("userFeed")));

        ArrayList<Double> compound = new ArrayList<Double>();
        ArrayList<String> dateList = new ArrayList<String>();
        ArrayList<analyzedData> data = new ArrayList<analyzedData>();

        if (value == null || value.equals("refresh") || request.getSession().getAttribute("allData") == null) {
            Collections.sort(allResults,Collections.<analyzedData>reverseOrder());

            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

            for (int i = 0; i < allResults.size(); i++) {
                try {
                    //get compound
                    JSONObject json = (JSONObject) new JSONParser().parse(allResults.get(i).getText());
                    Double com = (Double) json.get("compound");

                    //set format date
                    allResults.get(i).setFormatDate(format.format(allResults.get(i).getDate()));

                    //check if date already exists
                    if (dateList.contains("\'" + allResults.get(i).getFormatDate() + "\'")) {
                        //consolidate the compound and average it
                        int index = dateList.indexOf("\'" + allResults.get(i).getFormatDate() + "\'");
                        compound.set(index, (compound.get(index) + com * 100) / 2);

                        //consolidate content
                        data.get(index).setContent(allResults.get(i).getContent() + "\n" + data.get(index).getContent());

                        //add in url

                    } else {
                        //new entry
                        compound.add(com * 100);
                        data.add(allResults.get(i));
                        dateList.add("\'" + allResults.get(i).getFormatDate() + "\'");
                    }
                } catch (ParseException e) {
                }

            }

            //set all sessions
            request.getSession().setAttribute("allData", data);
            request.getSession().setAttribute("allDateList", dateList);
            request.getSession().setAttribute("allCompoundList", compound);
            request.getSession().setAttribute("compound", compound);
            request.getSession().setAttribute("dateList", dateList);
            request.getSession().setAttribute("contentList", data);

            response.sendRedirect("/mood");
        } else {

            compound = (ArrayList<Double>) request.getSession().getAttribute("allCompoundList");
            dateList = (ArrayList<String>) request.getSession().getAttribute("allDateList");
            data = (ArrayList<analyzedData>) request.getSession().getAttribute("allData");

            ArrayList<Double> compoundsub = new ArrayList<Double>();
            ArrayList<String> dateListsub = new ArrayList<String>();
            ArrayList<analyzedData> contentsub = new ArrayList<analyzedData>();

            for (int i = 0; i < data.size(); i++) {
                if(dateList.get(i).contains(value)){
                    dateListsub.add(dateList.get(i));
                    compoundsub.add(compound.get(i));
                    contentsub.add(data.get(i));
                }
            }

            if (compoundsub.size() == 0) {
                request.getSession().setAttribute("listEmpty", true);
                request.getSession().setAttribute("contentList", null);
                request.getSession().setAttribute("dateList", null);
                request.getSession().setAttribute("compound", null);
            } else {
                request.getSession().setAttribute("dateList", dateListsub);
                request.getSession().setAttribute("compound", compoundsub);
                request.getSession().setAttribute("contentList", contentsub);
                request.getSession().setAttribute("listEmpty", false);
            }

            response.sendRedirect("/mood");
        }

    }
}

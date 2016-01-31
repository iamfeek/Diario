package sentiment;

import com.google.gson.stream.JsonReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import post.Post;
import twitter4j.Status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sentiment.sentimentAnalysis;

/**
 * Created by glenice on 25 Jan 2016.
 */
public class moodGenerator {

    public static List<analyzedData> generateMoodFromTwitter(List<Status> twitterStatus) {

        List<analyzedData> results = new ArrayList<analyzedData>();

        for (int i = 0; i < twitterStatus.size(); i++) {

            results.add(new analyzedData(twitterStatus.get(i).getCreatedAt(), sentimentAnalysis.textAnalysis(twitterStatus.get(i).getText())));

            //System.out.println(generateJSON(results.get(i).getText(),results.get(i).getDate()));
        }

        return results;
    }

    public static List<analyzedData> generateMoodFromPost(ArrayList<Post> posts){

        List<analyzedData> results = new ArrayList<analyzedData>();
        for (int i = 0; i < posts.size(); i++) {

            results.add(new analyzedData(posts.get(i).getDate(), sentimentAnalysis.textAnalysis(posts.get(i).getText())));
            System.out.println(generateJSON(results.get(i).getText(),results.get(i).getDate()));
        }
        return results;
    }

    public static JSONObject generateJSON(String analysis, Date date) {

        JSONObject jsonResult;

        jsonResult = new JSONObject();

        jsonResult.put(date, analysis);

        return jsonResult;

    }

    /*public static List<analyzedData> consolidate(List<Status> twitterStatus){

    }*/
}

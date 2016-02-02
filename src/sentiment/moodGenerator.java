package sentiment;

import org.jinstagram.entity.users.feed.MediaFeedData;
import org.json.simple.JSONObject;
import post.Post;
import twitter4j.Status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by glenice on 25 Jan 2016.
 */
public class moodGenerator {

    public static List<analyzedData> generateMoodFromTwitter(List<Status> twitterStatus) {

        List<analyzedData> results = new ArrayList<analyzedData>();

        for (int i = 0; i < twitterStatus.size(); i++) {

            results.add(new analyzedData(twitterStatus.get(i).getCreatedAt(), sentimentAnalysis.textAnalysis(twitterStatus.get(i).getText()), twitterStatus.get(i).getText()));

            //System.out.println(generateJSON(results.get(i).getText(),results.get(i).getDate()));
        }

        return results;
    }

    public static List<analyzedData> instagramToData(List<MediaFeedData> listMedia){
        List<analyzedData> data = new ArrayList<analyzedData>();

        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

        for(int i =0; i<listMedia.size();i++){
            data.add(new analyzedData(listMedia.get(i).getImages().getLowResolution().getImageUrl(), listMedia.get(i).getCaption().getText(), listMedia.get(i).getCreatedTime(),sentimentAnalysis.textAnalysis(listMedia.get(i).getCaption().getText())));
        }


        return data;
    }

    public static List<analyzedData> generateMoodFromPost(ArrayList<Post> posts) {

        List<analyzedData> results = new ArrayList<analyzedData>();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getEncrypted() == false) {
                results.add(new analyzedData(posts.get(i).getDate(), sentimentAnalysis.textAnalysis(posts.get(i).getText()), posts.get(i).getText()));
            }
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

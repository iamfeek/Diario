package sentiment;

import DAO.SentimentResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Created by gleni on 24 Jan 2016.
 */
public class sentimentAnalysis {

    public static boolean analysis(String username, String text, int postid) {

        PythonInterpreter interp = new PythonInterpreter();
        System.out.println("Testing vader");
        interp.exec("from vaderSentiment.vaderSentiment import sentiment as vaderSentiment");
        interp.exec("text = \"" + text + "\"");
        interp.exec("vader = vaderSentiment(text)");
        PyObject res = interp.get("vader");

        String jsonResult = res.toString().replace('\'', '\"');

        try {
            JSONObject json = (JSONObject) new JSONParser().parse(jsonResult);
            Double compound = (Double) json.get("compound");
            Double neg = (Double) json.get("neg");
            Double pos = (Double) json.get("pos");
            Double neu = (Double) json.get("neu");

            return sentimentDAO.saveSentiment(postid, pos, neg, neu, compound);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }


}


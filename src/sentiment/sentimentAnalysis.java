package Sentiment;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Created by glenice on 24 Jan 2016.
 */


public class sentimentAnalysis {

    public static boolean analysis(String username, String text, int postid) {

        String jsonResult = textAnalysis(text);

        try {
            JSONObject json = (JSONObject) new JSONParser().parse(jsonResult);
            Double compound = (Double) json.get("compound");
            Double neg = (Double) json.get("neg");
            Double pos = (Double) json.get("pos");
            Double neu = (Double) json.get("neu");

            return sentimentDAO.saveSentiment(postid, pos, neg, neu, compound,username);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String textAnalysis(String text){

        text = StringEscapeUtils.escapeJava(text);

        PythonInterpreter interp = new PythonInterpreter();
        System.out.println("Testing vader");
        interp.exec("from vaderSentiment.vaderSentiment import sentiment as vaderSentiment");
        interp.exec("text = \"" + text + "\"");
        interp.exec("vader = vaderSentiment(text)");
        PyObject res = interp.get("vader");

        String jsonResult = res.toString().replace('\'', '\"');

        return jsonResult;
    }


}


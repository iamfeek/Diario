package Sentiment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by glenice on 30 Jan 2016.
 */
public class analyzedData implements Comparable<analyzedData> {
    private Date date;
    private String text;
    private String content;
    private String formatDate;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public analyzedData(){}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public analyzedData(Date date, String text, String content){
        this.date = date;
        this.text = text;
        this.content = content;

    }

    public analyzedData(String url, String content, String formatDate, String text){
        this.url = url;
        this.content = content;
        this.formatDate = formatDate;
        this.text = text;

        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        try {

            this.date =  formatter.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int compareTo(analyzedData data){

        return  this.date.compareTo(data.getDate());
    }
}

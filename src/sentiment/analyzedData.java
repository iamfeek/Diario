package sentiment;

import java.util.Date;

/**
 * Created by glenice on 30 Jan 2016.
 */
public class analyzedData implements Comparable<analyzedData> {
    private Date date;
    private String text;
    private String content;
    private String formatDate;

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

    public analyzedData(String text, String content, String formatDate){
        this.text = text;
        this.content = content;
        this.formatDate = formatDate;
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

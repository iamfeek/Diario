package sentiment;

import java.util.Date;

/**
 * Created by glenice on 30 Jan 2016.
 */
public class analyzedData implements Comparable<analyzedData> {
    private Date date;
    private String text;

    public analyzedData(){}


    public analyzedData(Date date, String text){
        this.date = date;
        this.text = text;
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
        return  data.date.compareTo(this.date);
    }
}

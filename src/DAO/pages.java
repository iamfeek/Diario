package DAO;
import java.sql.*;
/**
 * Created by NH on 12/5/2015.
 */
public class pages {

    private String title;
    private String content;
    private String date;


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public pages (){}


}

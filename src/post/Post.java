package post;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Jy on 17-Jan-16.
 */
public class Post implements Comparable<Post>{
    int postid;
    String username;
    String text;
    Boolean encrypted;
    Boolean shared;
    Date date;

    public Post(int postid, String username, String text, Boolean encrypted, Boolean shared, Date date) {
        this.postid = postid;
        this.username = username;
        this.text = text;
        this.encrypted = encrypted;
        this.shared = shared;
        this.date = date;
    }

    public Boolean getShared() {
        return shared;
    }

    public int getPostid() {
        return postid;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public Boolean getEncrypted() {
        return encrypted;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Post o) {
        return o.getDate().compareTo(getDate());
    }
}

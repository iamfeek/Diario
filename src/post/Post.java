package post;

/**
 * Created by Jy on 17-Jan-16.
 */
public class Post {
    int postid;
    String username;
    String text;
    Boolean encrypted;
    Boolean shared;

    public Post(int postid, String username, String text, Boolean encrypted, Boolean shared) {
        this.postid = postid;
        this.username = username;
        this.text = text;
        this.encrypted = encrypted;
        this.shared = shared;
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
}

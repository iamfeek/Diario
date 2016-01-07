package DAO;

/**
 * Created by lenovo on 1/3/2016.
 */
public class Friend {

    private int id;
    private String fUNAME;
    private String MF;
    private String Posts;
    private String Friends;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfUNAME() {
        return fUNAME;
    }

    public void setfUNAME(String fUNAME) {
        this.fUNAME = fUNAME;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMF() {
        return MF;
    }

    public void setMF(String MF) {
        this.MF = MF;
    }

    private String Location;

    public String getPosts() {
        return Posts;
    }

    public void setPosts(String posts) {
        Posts = posts;
    }

    public String getFriends() {
        return Friends;
    }

    public void setFriends(String friends) {
        Friends = friends;
    }

}

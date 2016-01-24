package DAO;

/**
 * Created by gleni on 24 Jan 2016.
 */
public class SentimentResult {

    private Double pos, neu, neg, compound;
    private int idPost;

    public SentimentResult(){

    }

    public SentimentResult(Double pos, Double neu, Double neg, Double compound){
        this.pos = pos;
        this.neg = neg;
        this.neu = neu;
        this.compound = compound;
    }

    public SentimentResult(int idPost, Double pos, Double neu, Double neg, Double compound) {
        this.idPost = idPost;
        this.pos = pos;
        this.neg = neg;
        this.neu = neu;
        this.compound = compound;
    }

    public Double getNeg() {
        return neg;
    }

    public void setNeg(Double neg) {
        this.neg = neg;
    }

    public Double getCompound() {
        return compound;
    }

    public void setCompound(Double compound) {
        this.compound = compound;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public Double getNeu() {
        return neu;
    }

    public void setNeu(Double neu) {
        this.neu = neu;
    }

    public Double getPos() {

        return pos;
    }

    public void setPos(Double pos) {
        this.pos = pos;
    }
}

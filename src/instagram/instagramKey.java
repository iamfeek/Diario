package instagram;

/**
 * Created by gleni on 1 Feb 2016.
 */
public class instagramKey {

    private byte[] key;
    private byte[] iv;

    public instagramKey(byte[] key, byte[] iv){
        this.key = key;
        this.iv = iv;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }
}

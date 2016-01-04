package authentication;

import org.json.simple.JSONObject;

/**
 * Created by: Syafiq Hanafee
 * Dated: 16/11/15.
 */
public class authTest {

    //    public static void main(String[] args) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
//        System.out.println("==== Registration Test ====");
//        User toRegister = new User("aisyah","123123", "me@iamnur.com");
//        System.out.println(toRegister.register());
//
//        System.out.println("\n==== Login Test ====");
//        User toLogin = new User("syafiqhanafee", "123123");
//        System.out.println(toLogin.login());
//    }
    public static void main(String[] args) {
        JSONObject jo = new JSONObject();
        jo.put("username", "syafiq");
        System.out.println(jo.toJSONString());
    }

}

package authentication;

import DAO.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

/**
 * Created by: Syafiq Hanafee
 * Dated: 16/11/15.
 */
public class authTest {

    public static void main(String[] args) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        System.out.println("==== Registration Test ====");
        User toRegister = new User("aisyah","123123", "me@iamnur.com");
        System.out.println(toRegister.register());

        System.out.println("\n==== Login Test ====");
        User toLogin = new User("syafiqhanafee", "123123");
        System.out.println(toLogin.login());
    }
}

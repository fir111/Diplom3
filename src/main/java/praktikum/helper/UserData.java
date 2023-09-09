package praktikum.helper;

import java.util.concurrent.ThreadLocalRandom;

public class UserData {
    public static final String EMAIL = "@diplom3.ru";
    public static final String PASSWORD = "P@ssw0rd";
    public static final String NAME = "name";

    public static String getEmail(){
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        return randomNum + EMAIL;
    }
}
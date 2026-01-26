package ute.shop.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

    // Hash password (khi register)
    public static String hashPassword(String plainPassword) {
        // strength = 10 là mức phổ biến
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    // Verify password (khi login)
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2")) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
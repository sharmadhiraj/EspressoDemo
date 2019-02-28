package com.sharmadhiraj.espressodemo;

public class Utils {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";

    public static boolean isValidUsername(String username) {
        return USERNAME.equals(username);
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD.equals(password);
    }

}

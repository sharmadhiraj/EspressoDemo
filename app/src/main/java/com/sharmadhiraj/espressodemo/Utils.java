package com.sharmadhiraj.espressodemo;

class Utils {

    static final String VALID_USERNAME = "admin";
    static final String VALID_PASSWORD = "12345";

    static boolean isValidUsername(String username) {
        return VALID_USERNAME.equals(username);
    }

    static boolean isValidPassword(String password) {
        return VALID_PASSWORD.equals(password);
    }

}

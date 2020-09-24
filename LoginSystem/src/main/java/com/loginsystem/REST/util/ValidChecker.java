package com.loginsystem.REST.util;

import com.loginsystem.REST.database.UserInfo;

public class ValidChecker {
    private String message;

    // error message
    public String getMessage() {
        return this.message;
    }

    public static boolean idValid (String id) {
        return id != null && id.matches("[0-9]{1,8}");
    }

    public static boolean idValid (int id) {
        return id > 0 && id < 100000000;
    }

    public static boolean pwValid (String pw) {
        return pw != null && pw.matches("[0-9a-zA-Z\\.\\?!_]{6,16}");
    }

    public static boolean nameValid (String name) {
        return name != null &&
                name.matches("[\\sa-zA-Z\\p{InCJKUnifiedIdeographs}\\p{InHiragana}\\p{InKatakana}]{1,20}");
    }

    public static boolean deptNoValid (String deptNo) {
        return deptNo != null && deptNo.matches("[0-9]{4}");
    }

    public static boolean deptNoValid (int deptNo) {
        return deptNo > 999 && deptNo < 10000;
    }

    // validation for [POST /api/v1.0/users]
    public boolean isRegisterDataValid (UserInfo user) {
        if (!idValid(user.getId())) {
            message = "[id] invalid. Must be no more than 8 digits";
            return false;

        } else if (!pwValid(user.getPw())) {
            message = "[pw] invalid. Must be 6 to 16 letters, numbers or ?!._ ";
            return false;

        } else if (!nameValid(user.getName())) {
            message = "[name] invalid. Must be 1 to 20 alphabet, Katakana, Hiragana or Kanji";
            return false;

        } else if (!deptNoValid(user.getDeptNo())) {
            message = "[dept_no] invalid. Must be 4 digits";
            return false;

        } else {
            return true;
        }

    }

    // validation for [POST /api/v1.0/password]
    public boolean isIdPwValid (UserInfo user) {
        if (!idValid(user.getId())) {
            message = "[id] invalid. Must be no more than 9 digits";
            return false;

        } else if (!pwValid(user.getPw())) {
            message = "[pw] invalid. Must be 6 to 15 letters, numbers or ?!._ ";
            return false;

        } else {
            return true;
        }

    }

}

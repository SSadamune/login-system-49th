package com.loginsystem.REST.util;

import com.loginsystem.REST.database.UserInfo;

public class JsonString {

    // a json string as response-body, with "status" and "data"
    public static String responseBody (String status, String data) {
        return data.matches("\\{.*\\}") ?
                // if data is a json string, no quote around it
                "{\"status\": \"" + status + "\", \"data\": " + data + "}" :
                    "{\"status\": \"" + status + "\", \"data\": \"" + data + "\"}";
    }

    // a json string as a sql exception, with "status" and "data"
    public static String unexpectedSqlException (String sqlState, String errorMessage) {
        return "{\"sql_state\": \"" + sqlState + "\", \"error_message\": \"" + errorMessage + "\"}";
    }

    // a json String as user information without password, replace "deptNo" by "dept_no"
    public static String userInfo (UserInfo user) {
        return "{\"id\": " + user.getId()
                + ", \"name\": \"" + user.getName() + "\""
                + ", \"dept_no\": " + user.getDeptNo()
                + ", \"registerDate\": \"" + user.getRgstDate() + "\""
                + "}";
    }
}

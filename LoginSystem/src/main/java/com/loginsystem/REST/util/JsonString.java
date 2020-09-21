package com.loginsystem.REST.util;

public class JsonString {
    // generate a json string, with "status" and "data"
    public static String responseBody (String status, String data) {
        return data.matches("\\{.*\\}") ?
                // if data is a json object, no quote around it
                "{\"status\": \"" + status + "\", \"data\": " + data + "}" :
                "{\"status\": \"" + status + "\", \"data\": \"" + data + "\"}";
    }
}

package com.loginsystem.REST.util;

public class JsonResponse {

    public static String statusData (String status, String data) {
        // if data is a json object, no quote around it
        return data.matches("\\{.*\\}") ?
                "{\"status\": \"" + status + "\", \"data\": " + data + "}" :
                "{\"status\": \"" + status + "\", \"data\": \"" + data + "\"}";
    }

}


package com.loginsystem.REST.util;

public class JsonResponse {
	public static String getUser (String status, String data) {
		// no quote surround data
		return "{\"status\": \"" + status + "\", \"data\": " + data + "}";
	}
	public static String message (String status, String data) {
		return "{\"status\": \"" + status + "\", \"data\": \"" + data + "\"}";
	}
}

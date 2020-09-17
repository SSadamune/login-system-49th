package com.loginsystem.REST.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PostJsonReader {
	public static String toString(HttpServletRequest request) throws IOException {
		// read request
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		StringBuilder sb = new StringBuilder();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			br.close();
		}
		return sb.toString();

	}

	public static JsonObject toJsonObj(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		// read request
		Gson gson = new Gson();

		String strJson = toString(request);

		//jsonStr to jsonObject
		JsonObject json = gson.fromJson(strJson, JsonObject.class);
		return json;
	}
}

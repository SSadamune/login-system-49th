package com.loginsystem.REST.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.loginsystem.REST.db.UserInfo;

public class PostReader {
	public static String toJsonStr(HttpServletRequest request) throws IOException {
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

	public static UserInfo toUserObj(HttpServletRequest request) throws IOException {
		// read request
		Gson gson = new Gson();

		String strJson = toJsonStr(request);

		//jsonStr to jsonObject
		UserInfo json = gson.fromJson(strJson, UserInfo.class);
		return json;

	}

}

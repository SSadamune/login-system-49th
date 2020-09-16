package com.loginsystem.REST.db;

import java.sql.SQLException;

import com.google.gson.Gson;


public class TestGson {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Gson gson = new Gson();

		//
		String jstr = gson.toJson(100);
		System.out.println(jstr);

		//
		String [] stringArray = {"alpha", "bravo", "charlie"};
		System.out.println(gson.toJson(stringArray));

		//
		int currentUserId = 1000003;
		UserInf testUser = new UserInf();
		try {
			testUser.selectFromDb(currentUserId);
			String jsonUser = gson.toJson(testUser.getStr());
			System.out.println(jsonUser);
		} catch (SQLException ex) {
			System.out.println("SQLException");
		}


	}

}

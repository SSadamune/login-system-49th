package com.loginsystem.REST.db;

import java.sql.SQLException;

public class TestJDBC {
	public static void main(String args[]) {
		/*
		//部署情報を取得
		try {
			DeptList deptList = new DeptList();

			System.out.println(deptList.getSize());
			System.out.println(deptList.getNo());
			System.out.println("------");
		} catch (SQLException ex) {
			System.out.println("SQLException");
		}

		*/

		//ユーザー情報を挿入
		UserInfo testUser1 = new UserInfo();

		testUser1.setId(2001);
		testUser1.setPw("testPw");
		testUser1.setName("test名前");
		testUser1.setDeptNo(1002);
		testUser1.setRgstDate("2020-09-01");

		try {
			System.out.println(testUser1.insertIntoDb());
		} catch (SQLException ex) {
			System.out.println("SQLException");
		}
		System.out.println("------");

		/*
		 *
		//ユーザー情報を取得
		int currentUserId = 1000003;

		UserInfo testUser2 = new UserInfo();
		try {
			String jsonUser2 = testUser2.selectFromDb(currentUserId);
			System.out.println(jsonUser2);
		} catch (SQLException ex) {
			System.out.println("SQLException");
		}
		System.out.println("------");



		//パスワード検証
		try {
			UserInf testUser3 = new UserInf();
			System.out.println(testUser3.checkIdPw(1000005, "testPw"));
			System.out.println(testUser3.checkIdPw(1000005, "falsepw"));
			System.out.println(testUser3.checkIdPw(2000005, "testPw"));
			testUser3.setId(1000005);
			testUser3.setPw("testPw");
			System.out.println(testUser3.checkIdPw());
		} catch (SQLException ex) {
			System.out.println("error code : " + ex.getErrorCode() + "\n");
			System.out.println("error message : " + ex.getLocalizedMessage());
		}
		System.out.println("------");

		try {

		} catch (SQLException ex) {
			String sqlEx = "SQLException: \n";
			for(Throwable e : ex ) {
				sqlEx += e;
			}

		}
		 */
	}
}




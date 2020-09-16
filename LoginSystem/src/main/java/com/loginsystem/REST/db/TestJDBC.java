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

		//ユーザー情報を挿入
		UserInf testUser1 = new UserInf();

		testUser1.setId(1000005);
		testUser1.setPw("testPw");
		testUser1.setName("テストなまえ");
		testUser1.setDeptNo(1002);
		testUser1.setRgstDate("2020-09-01");

		try {
			System.out.println(testUser1.insertIntoDb());
		} catch (SQLException ex) {
			System.out.println("SQLException");
		}
		System.out.println("------");


		//ユーザー情報を取得
		int currentUserId = 1000003;

		UserInf testUser2 = new UserInf();
		try {
			testUser2.selectFromDb(currentUserId);

			String currentUserName = testUser2.getName();
			int currentUserDept = testUser2.getDeptNo();
			String currentUserRgstDate = testUser2.getRgstDate();

			System.out.println(currentUserName);
			System.out.println(currentUserDept);
			System.out.println(currentUserRgstDate);
		} catch (SQLException ex) {
			System.out.println("SQLException");
		}
		System.out.println("------");
		*/


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


		/*
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




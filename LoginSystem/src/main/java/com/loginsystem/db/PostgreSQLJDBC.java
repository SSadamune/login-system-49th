package com.loginsystem.db;

public class PostgreSQLJDBC {
	public static void main(String args[]) {

		/*
		//部署情報を取得
		DeptList deptList = new DeptList();

		System.out.println(deptList.getSize());
		System.out.println(deptList.getNo());
		*/

		
		//ユーザー情報を挿入
		UserInf testUser1 = new UserInf();

		testUser1.setId(1000005);
		testUser1.setPw("testPw");
		testUser1.setName("猪");
		testUser1.setDeptNo(1002);
		testUser1.setRgstDate("2020-09-01");

		testUser1.insertIntoDb();
		
		/*
		//ユーザー情報を取得
		int currentUserId = 1000003;

		UserInf testUser2 = new UserInf();
		testUser2.selectFromDb(currentUserId);

		String currentUserName = testUser2.getName();
		int currentUserDept = testUser2.getDeptNo();
		String currentUserRgstDate = testUser2.getRgstDate();

		System.out.println(currentUserName);
		*/

	}
}




package api;

import jdbc.*;

public class TestRegister {
	public static void main(String args[]) {

		UserInf currentUser = new UserInf();

		currentUser.userId(1005001);
		currentUser.userPw("testPW");
		currentUser.userName("テスト名前");
		currentUser.userDeptNo(1001);
		currentUser.userRgstDate("2020-09-07");

		currentUser.insert();
	}
}
package jdbc;

import java.io.*;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
	   UserInf testUser1 = new UserInf();

	   testUser1.userId(1000001);
	   testUser1.userPw("testPw");
	   testUser1.userName("张三");
	   testUser1.userDeptNo(1002);
	   testUser1.userRgstDate("2020-09-01");

	   testUser1.insert();
   }
}





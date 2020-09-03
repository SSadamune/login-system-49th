package jdbc;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
	   //部署情報を取得
	   DeptList dpList = new DeptList();
	   System.out.println(dpList.size);
	   System.out.println(dpList.no);
	   System.out.println(dpList.name);

	   /*
	   //ユーザー情報を挿入
	   UserInf testUser1 = new UserInf();

	   testUser1.userId(1000001);
	   testUser1.userPw("testPw");
	   testUser1.userName("张三");
	   testUser1.userDeptNo(1002);
	   testUser1.userRgstDate("2020-09-01");

	   testUser1.insert();
	   */
   }
}





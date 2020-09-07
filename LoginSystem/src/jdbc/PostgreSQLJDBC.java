package jdbc;

public class PostgreSQLJDBC {
   public static void main(String args[]) {

	   /*
	   //部署情報を取得 arraylist
	   DeptList deptList = new DeptList();

	   System.out.println(deptList.size);
	   System.out.println(deptList.no);
	   System.out.println(deptList.name);
	  */

	   //ユーザー情報を挿入
	   UserInf testUser1 = new UserInf();

	   testUser1.userId(1000005);
	   testUser1.userPw("testPw");
	   testUser1.userName("猪");
	   testUser1.userDeptNo(1002);
	   testUser1.userRgstDate("2020-09-01");

	   testUser1.insert();


	   /*
	   //ユーザー情報を取得
	   int currentUserId = 1000003;

	   UserInf testUser2 = new UserInf();
	   testUser2.selectInf(currentUserId);

	   String currentUserName = testUser2.name;
	   int currentUserDept = testUser2.deptNo;
	   String currentUserRgstDate = testUser2.rgstDate;

	   System.out.println(currentUserName)
	   */

   }
}




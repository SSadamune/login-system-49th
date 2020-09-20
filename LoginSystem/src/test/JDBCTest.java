import java.sql.SQLException;

import com.loginsystem.REST.database.DeptList;
import com.loginsystem.REST.database.UserInfo;
import com.loginsystem.REST.util.InsertExceptionResponse;
import com.loginsystem.REST.util.JsonResponse;

public class JDBCTest {
    public static void main(String args[]) {

        //部署情報を取得
        try {
            DeptList deptList = new DeptList();
            System.out.println(deptList.getListNo());
            System.out.println(deptList.toJson());
            System.out.println("------");
        } catch (SQLException ex) {
            InsertExceptionResponse er = new InsertExceptionResponse(ex);
            System.out.println(er.getStatusCode());
            System.out.println(er.getErrorMessage());
        }


        //ユーザー情報を挿入
        UserInfo testUser1 = new UserInfo();

        testUser1.setId(2001);
        testUser1.setPw("testPw");
        testUser1.setName("test名前");
        testUser1.setDeptNo(1002);
        testUser1.setRgstDate("2020-09-01");

        try {
            testUser1.insertIntoDb();
            System.out.println("success");
        } catch (SQLException ex) {
            System.out.println(JsonResponse.statusData("sql_exception",
                    "{\"sql_state\": \"" + ex.getSQLState() +
                    "\", \"error_message\": \"" + ex.getLocalizedMessage() + "\"}"));
        }
        System.out.println("------");


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
            UserInfo testUser3 = new UserInfo();
            System.out.println(UserInfo.authStatus(1000005, "testPw"));
            System.out.println(UserInfo.authStatus(1000005, "falsepw"));
            System.out.println(UserInfo.authStatus(2000005, "testPw"));
            testUser3.setId(1000005);
            testUser3.setPw("testPw");
            System.out.println(testUser3.authStatus());
        } catch (SQLException ex) {
            System.out.println("error code : " + ex.getErrorCode() + "\n");
            System.out.println("error message : " + ex.getLocalizedMessage());
        }
        System.out.println("------");


    }

}




import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loginsystem.REST.database.UserInfo;
import com.loginsystem.REST.util.ValidChecker;



public class GsonTest {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        //0
        System.out.println(gson.toJson(100));
        System.out.println(gson.fromJson("100", int.class));
        System.out.println(gson.fromJson("String", String.class));
        System.out.println();

        //1
        System.out.println("----1----");
        String [] stringArray = {"alpha", "bravo", "charlie"};
        System.out.println(gson.toJson(stringArray));
        System.out.println();

        //2
        System.out.println("----2----");
        int currentUserId = 2001;
        UserInfo testUser = new UserInfo();
        try {
            testUser.selectFromDb(currentUserId);
            String jstrUser = gson.toJson(testUser);
            System.out.println(jstrUser);

        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
        System.out.println();

        //3
        System.out.println("----3----");
        String jstrUser = "{\"id\":1008,\"pw\":\"testpassword\",\"name\":\"名前1010\",\"deptNo\":1001,\"registerDate\":\"2020-09-17\"}";
        UserInfo testUser3 = gson.fromJson(jstrUser, UserInfo.class);
        System.out.println(gson.toJson(testUser3));
        try {
            testUser3.insertIntoDb();

        } catch (SQLException ex) {
            System.out.println("fail");
        }

        //4
        System.out.println("----4----");
        String jstrUser4 = "{\"id\":1008.11,\"pw\":\"testpassword\",\"name\":\"名前1010\"}";

        try {
            UserInfo testUser4 = gson.fromJson(jstrUser4, UserInfo.class);
            System.out.println(gson.toJson(testUser4));
            System.out.println(testUser4.getId());
            System.out.println(testUser4.getDeptNo());
            System.out.println(testUser4.getRgstDate());
            testUser4.getRgstDate().matches("[a-z] {1,8}");

        } catch (Exception ex) {
            System.out.println("fail");
        }

        //5
        System.out.println("----5----");
        ValidChecker vc = new ValidChecker();
        String jstrUser5 = "{\"id\":\"apple\",\"pw\":\"testpassword\",\"name\":\"名前1010\"}";
        UserInfo testUser5 = gson.fromJson(jstrUser5, UserInfo.class);
        // check validation of id, pw, name, deptNo
        if (!(vc.isRegisterDataValid(testUser5))) {
            System.out.println(ValidChecker.idValid(testUser5.getId()));
            System.out.println(vc.getMessage());
        }

    }

}


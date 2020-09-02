package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInf {
	int id;
	String password;
	String name;
	int deptNo;
	String rgstDate;

	public UserInf() {
	}

	//各情報を設置
	public void userId(int userId) {
		id = userId;
	}
	public void userPw(String userPw) {
		password = userPw;
	}
	public void userName(String userName) {
		name = userName;
	}
	public void userDeptNo(int userDeptNo) {
		deptNo = userDeptNo;
	}
	public void userRgstDate(String userRgstDate) {
		rgstDate = userRgstDate;
	}

	//テーブル T_USER に挿入
	public void insert(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        //接続文字列
        String url = "jdbc:postgresql://localhost:5432/loginsystem";
        String user = "postgres";
        String password = "pwpsql";

        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            //INSERT文の実行
            stmt = conn.createStatement();
            String sql = "INSERT INTO T_USER (USER_ID, USER_PW, USER_NAME, DEPT_NO, RGST_DATE) VALUES("
                  + id + ",'" + password + "', '" + name + "', '" + deptNo + "', '" + rgstDate + "');";
            stmt.executeUpdate(sql);
            conn.commit();
        }
        catch (SQLException e){
        	e.printStackTrace();
        	System.exit(0);
        }
        finally {
            try {
                if(rset != null)rset.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
        System.out.println("Records created successfully");
    }
}

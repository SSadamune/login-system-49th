package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertToDept {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
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
            int deptNo = 2003;
            String deptName = "test部署1";
            String sql = "INSERT INTO T_DEPT VALUES("
                  + deptNo + ",'" + deptName + "');";
            stmt.executeUpdate(sql);

            deptNo = 2004;
            deptName = "test部署2";
            sql = "INSERT INTO T_DEPT VALUES("
                  + deptNo + ",'" + deptName + "');";
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

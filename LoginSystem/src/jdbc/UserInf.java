package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInf {
	public int id;
	public String userPassword;
	public String name;
	public int deptNo;
	public String rgstDate;

	public UserInf() {
	}

	//use there methods to assign user inf.
	public void userId(int userId) {
		id = userId;
	}
	public void userPw(String userPw) {
		userPassword = userPw;
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

	//insert user inf. to table t_user
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
					+ id + ",'" + userPassword + "', '" + name + "', '" + deptNo + "', '" + rgstDate + "');";
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

	//select all the inf. from t_user whose id = userId
	public void selectInf(int userId) {
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

			//SELECT文の実行
			stmt = conn.createStatement();
			String sql = "select USER_NAME, DEPT_NO, RGST_DATE from T_USER \r\n" +
					"where USER_ID = "+ userId +";";
			rset = stmt.executeQuery(sql);

			//SELECT結果の受け取り
			while(rset.next()){
				name = rset.getString("USER_NAME");
				deptNo = rset.getInt("DEPT_NO");
				rgstDate = rset.getString("RGST_DATE");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
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
	}
}

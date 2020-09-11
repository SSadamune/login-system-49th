package com.loginsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInf {
	private int id;
	private String Pw;
	private String name;
	private int deptNo;
	private String rgstDate;

	public UserInf() {
	}

	//use there methods to assign user inf.
	public void setId(int userId) {
		this.id = userId;
	}

	public int getId() {
		return id;
	}

	public void setPw(String userPw) {
		this.Pw = userPw;
	}

	public String getPw() {
		return Pw;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getName() {
		return name;
	}

	public void setDeptNo(int userDeptNo) {
		this.deptNo = userDeptNo;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setRgstDate(String userRgstDate) {
		this.rgstDate = userRgstDate;
	}

	public String getRgstDate() {
		return rgstDate;
	}

	//insert user inf. to table t_user
	public String insertIntoDb(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		//接続文字列
		String url = "jdbc:postgresql://localhost:5432/loginsystem";
		String pgUser = "postgres";
		String pgPw = "pwpsql";

		try{
			//PostgreSQLへ接続
			conn = DriverManager.getConnection(url, pgUser, pgPw);

			//自動コミットOFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//INSERT文の実行
			stmt = conn.createStatement();
			String sql = "INSERT INTO T_USER (USER_ID, USER_PW, USER_NAME, DEPT_NO, RGST_DATE) VALUES("
					+ id + ",'" + Pw + "', '" + name + "', '" + deptNo + "', '" + rgstDate + "');";
			stmt.executeUpdate(sql);
			conn.commit();
			return "Inserted successfully";
		}
		catch (SQLException ex){
			String sqlEx = "SQLException: \n";
			for(Throwable e : ex ) {
				sqlEx += e;
        	}
			return sqlEx;
		}
		finally {
			try {
				if(rset != null)rset.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}
			catch (SQLException ex){
				for(Throwable e : ex ) {
	        		System.out.println("Error encountered: " + e);
	        	}
			}
		}
	}

	//select all the inf. from t_user whose id = userId
	public void selectFromDb(int userId) {
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
		catch (SQLException ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if(rset != null)rset.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}
			catch (SQLException ex){
				ex.printStackTrace();
			}

		}
	}
}

package com.loginsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInf {
	private int id;
	private String pw;
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
		this.pw = userPw;
	}

	public String getPw() {
		return pw;
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

	//insert user inf. into table t_user
	public String insertIntoDb(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ConnectDb cd = new ConnectDb();

		try{
			//PostgreSQLへ接続
			conn = DriverManager.getConnection(cd.url(), cd.user(), cd.pw());

			//自動コミットOFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//INSERT文の実行
			stmt = conn.createStatement();
			String sql = "INSERT INTO T_USER (USER_ID, USER_PW, USER_NAME, DEPT_NO, RGST_DATE) VALUES("
					+ id + ",'" + pw + "', '" + name + "', '" + deptNo + "', '" + rgstDate + "');";
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
		ConnectDb cd = new ConnectDb();

		try{
			//PostgreSQLへ接続
			conn = DriverManager.getConnection(cd.url(), cd.user(), cd.pw());

			//自動コミットOFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//SELECT文の実行
			stmt = conn.createStatement();
			String sql = "select USER_NAME, DEPT_NO, RGST_DATE from T_USER \r\n" +
					"where USER_ID = "+ userId +";";
			rset = stmt.executeQuery(sql);

			//SELECT結果の受け取り
			rset.next();
			this.name = rset.getString("USER_NAME");
			this.deptNo = rset.getInt("DEPT_NO");
			this.rgstDate = rset.getString("RGST_DATE");
			this.id = userId;
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

	// check the id & pw
	public String checkIdPw() {
		return checkIdPw(id, pw);
	}

	public String checkIdPw(int checkId, String checkPw) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ConnectDb cd = new ConnectDb();

		try{
			//PostgreSQLへ接続
			conn = DriverManager.getConnection(cd.url(), cd.user(), cd.pw());

			//自動コミットOFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//SELECT文の実行
			stmt = conn.createStatement();
			String sql = "select USER_PW from T_USER \r\n" +
					"where USER_ID = "+ checkId +";";
			rset = stmt.executeQuery(sql);

			if (!rset.next()) {
				return "User " + checkId + " does not exist";
			} else {
				String truePw = rset.getString("USER_PW");
				return truePw.equals(checkPw) ? "password correct" : "password incorrect";
			}
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
}

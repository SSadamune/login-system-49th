package com.loginsystem.REST.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
	@SerializedName(value = "id", alternate = {"user_id", "userId"})
	private int id;
	@SerializedName(value = "pw", alternate = {"password", "user_pw", "userPw"})
	private String pw;
	@SerializedName(value = "name", alternate = {"user_name", "userName"})
	private String name;
	@SerializedName(value = "deptNo", alternate = {"dept_no", "userDeptNo"})
	private int deptNo;
	private String registerDate;

	public UserInfo() {
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

	public void setRgstDate(String userRegisterDate) {
		this.registerDate = userRegisterDate;
	}

	public String getRgstDate() {
		return registerDate;
	}

	//insert user inf. into table t_user
	public String insertIntoDb() throws SQLException{
		Connection conn = null;
		Statement stmt = null;

		try {
			//connect PostgreSQL
			conn = DriverManager.getConnection(db.url, db.user, db.pw);

			//auto commit OFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//run INSERT
			stmt = conn.createStatement();
			String sql = "INSERT INTO T_USER (USER_ID, USER_PW, USER_NAME, DEPT_NO, RGST_DATE) VALUES("
					+ id + ",'" + pw + "', '" + name + "', '" + deptNo + "', '" + registerDate + "');";
			stmt.executeUpdate(sql);
			conn.commit();

		}catch (SQLException ex){
			//throw ex to Servlet
			throw ex;

		}finally {
			try {
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}

			catch (SQLException ex){
				throw ex;
			}
		}
		return "id = " + id + " inserted correctly";

	}

	//select all the inf. from t_user whose id = userId
	//and return json String
	public String selectFromDb(int userId) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			//connect PostgreSQL
			conn = DriverManager.getConnection(db.url, db.user, db.pw);

			//auto commit OFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//run SELECT
			stmt = conn.createStatement();
			String sql = "select USER_NAME, DEPT_NO, RGST_DATE from T_USER \r\n" +
					"where USER_ID = "+ userId +";";
			rset = stmt.executeQuery(sql);

			//get SELECT result
			rset.next();
			this.name = rset.getString("USER_NAME");
			this.deptNo = rset.getInt("DEPT_NO");
			this.registerDate = rset.getString("RGST_DATE");
			this.id = userId;

		} catch (SQLException ex){
			//throw ex to Servlet
			throw ex;

		} finally {
			try {
				if(rset != null)rset.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();

			} catch (SQLException ex) {
				throw ex;
			}
		}

		return toJsonStr();
	}

	// return a json String. replace "deptNo" by "dept_no"
	public String toJsonStr() {
//		return new Gson().toJson(this);
		return "{\"id\": " + id
				+ ",\"name\": \"" + name + "\""
				+ ",\"dept_no\": " + deptNo
				+ ",\"registerDate\": \"" + registerDate + "\""
				+ "}";
	}

	// check the id-pw pair of current instance
	public int checkIdPw() throws SQLException {
		return checkIdPw(id, pw);
	}

	// check any id-pw pair from input
	public static int checkIdPw(int checkId, String checkPw) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		int statusCode = 500;
		try {
			//connect PostgreSQL
			conn = DriverManager.getConnection(db.url, db.user, db.pw);

			//auto commit OFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//run SELECT
			stmt = conn.createStatement();
			String sql = "select USER_PW from T_USER \r\n" +
					"where USER_ID = " + checkId + ";";
			rset = stmt.executeQuery(sql);

			//No such ID
			if (!rset.next()) {
				statusCode = 404;

			} else {
				String truePw = rset.getString("USER_PW");
				statusCode = truePw.equals(checkPw) ? 200 : 401;
			}

		} catch (SQLException ex) {
			//throw ex to Servlet
			throw ex;

		} finally {
			try {
				if(rset != null)rset.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();

			} catch (SQLException ex){
				throw ex;
			}
		}
		return statusCode;

	}
}

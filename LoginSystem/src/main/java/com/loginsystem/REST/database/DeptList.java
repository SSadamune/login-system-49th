package com.loginsystem.REST.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeptList {
	private int size;
	private ArrayList<Integer> listNo = new ArrayList<Integer>();
	private ArrayList<String> listName = new ArrayList<String>();

	//get data from DB
	public DeptList() throws SQLException{

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			//connect PostgreSQL
			conn = DriverManager.getConnection(DBConnect.url, DBConnect.user, DBConnect.pw);

			//auto commit OFF
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");

			//run SELECT
			stmt = conn.createStatement();
			String sql = "SELECT * FROM T_DEPT";
			rset = stmt.executeQuery(sql);

			size = 0;
			//get the SELECT result
			while(rset.next()){
				listNo.add(rset.getInt("DEPT_NO"));
				listName.add(rset.getString("DEPT_NAME"));
				size += 1;
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
	}

	//show how many dept.s in table
	public int getSize() {
		return this.size;
	}

	//show the DeptNo row as ArrayList
	public ArrayList<Integer> getListNo() {
		return this.listNo;
	}

	// return a json String
	/* wait to do */
	public String toJson() {
		String jStr = "";
		//
		return jStr;
	}

}



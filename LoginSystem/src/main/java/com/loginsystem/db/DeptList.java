package com.loginsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeptList {
	private int size;
	private ArrayList<Integer> no = new ArrayList<Integer>();
	private ArrayList<String> name = new ArrayList<String>();

	//構造メソッド：sizeを確認
	public DeptList() {

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
            String sql = "SELECT * FROM T_DEPT";
            rset = stmt.executeQuery(sql);

            size = 0;
            //SELECT結果の受け取り、Integer.parseInt()を使ってintへ変換
            while(rset.next()){
            	no.add(rset.getInt("DEPT_NO"));
            	name.add(rset.getString("DEPT_NAME"));
            	size += 1;
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

	public int getSize() {
		return size;
	}

	public ArrayList<Integer> getNo() {
		return no;
	}

}



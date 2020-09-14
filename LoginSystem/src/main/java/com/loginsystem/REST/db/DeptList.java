package com.loginsystem.REST.db;

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

	//構造メソッド：DBから情報を取得、パラメータを設定
	public DeptList() {

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
        catch (SQLException ex){
        	for(Throwable e : ex ) {
        		System.out.println("Error encountered: " + e);
        	}
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

	//サイズを示す
	public int getSize() {
		return size;
	}

	//部署番号の列を ArrayList として示す
	public ArrayList<Integer> getNo() {
		return no;
	}

}



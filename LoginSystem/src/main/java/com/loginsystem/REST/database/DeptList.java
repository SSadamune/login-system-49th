package com.loginsystem.REST.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.loginsystem.REST.util.DbAccess;

public class DeptList {
    private int size;
    private ArrayList<Integer> listNo = new ArrayList<Integer>();
    private ArrayList<String> listName = new ArrayList<String>();

    //get data from DB
    public DeptList() throws SQLException{
        String sql = "SELECT * FROM T_DEPT";
        try (ResultSet rset = DbAccess.select(sql)) {
            this.size = 0;
            //get the SELECT result
            while(rset.next()){
                listNo.add(rset.getInt("DEPT_NO"));
                listName.add(rset.getString("DEPT_NAME"));
                size += 1;
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



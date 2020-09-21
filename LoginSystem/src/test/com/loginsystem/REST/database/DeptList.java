package com.loginsystem.REST.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.loginsystem.REST.util.DbAccess;

public class DeptList {
    private int count;
    private ArrayList<Integer> listNo = new ArrayList<Integer>();
    private ArrayList<String> listName = new ArrayList<String>();

    //get data from DB
    public DeptList() throws SQLException{
        String sql = "SELECT * FROM T_DEPT";
        try (ResultSet rset = DbAccess.select(sql)) {
            this.count = 0;
            //get the SELECT result
            while(rset.next()){
                listNo.add(rset.getInt("DEPT_NO"));
                listName.add(rset.getString("DEPT_NAME"));
                count += 1;
            }
        }
    }

    // how many departments in list
    public int getCount() {
        return this.count;
    }

    // return an ArrayList as all the No. of departments
    public ArrayList<Integer> getListNo() {
        return this.listNo;
    }

    // return a json String as all the departments
    public String toJson() {
        StringBuffer jStr = new StringBuffer("{ \"dept_list\": [ ");
        for (int i = 0 ; i < this.count ; i++) {
            jStr.append(this.objDept(listNo.get(i), listName.get(i)));
            if (i != this.count - 1) jStr.append(", ");
        }
        jStr.append(" ] }");
        return jStr.toString();
    }

    // return a json object as ONE department
    private String objDept (int deptNo, String deptName) {
        return "{ \"No.\": " + deptNo + ", \"name\": \"" + deptName + "\" }";
    }

}



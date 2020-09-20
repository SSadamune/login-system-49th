package com.loginsystem.REST.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.annotations.SerializedName;
import com.loginsystem.REST.util.DbAccess;

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
        return this.id;
    }

    public void setPw(String userPw) {
        this.pw = userPw;
    }

    public String getPw() {
        return this.pw;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getName() {
        return this.name;
    }

    public void setDeptNo(int userDeptNo) {
        this.deptNo = userDeptNo;
    }

    public int getDeptNo() {
        return this.deptNo;
    }

    public void setRgstDate(String userRegisterDate) {
        this.registerDate = userRegisterDate;
    }

    public String getRgstDate() {
        return this.registerDate;
    }

    //insert user inf. into table t_user
    public void insertIntoDb() throws SQLException{
        String sql = "INSERT INTO T_USER (USER_ID, USER_PW, USER_NAME, DEPT_NO, RGST_DATE) VALUES("
                + id + ",'" + pw + "', '" + name + "', '" + deptNo + "', '" + registerDate + "');";
        DbAccess.insert(sql);
    }

    //select all the inf. from t_user whose id = userId
    //and return json String
    public String selectFromDb(int userId) throws SQLException {
        String sql = "select USER_NAME, DEPT_NO, RGST_DATE from T_USER " +
                "where USER_ID = "+ userId +";";
        try (ResultSet rset = DbAccess.select(sql)) {
            //get the SELECT result
            rset.next();
            this.name = rset.getString("USER_NAME");
            this.deptNo = rset.getInt("DEPT_NO");
            this.registerDate = rset.getString("RGST_DATE");
            this.id = userId;
        }

        return toJsonStr();
    }

    // return a json String. replace "deptNo" by "dept_no"
    public String toJsonStr() {
        return "{\"id\": " + id
                + ",\"name\": \"" + name + "\""
                + ",\"dept_no\": " + deptNo
                + ",\"registerDate\": \"" + registerDate + "\""
                + "}";
    }

    // match the id-pw pair from the member variables, return status code
    public int authStatus() throws SQLException {
        return authStatus(id, pw);
    }

    // match any id-pw pair from input, return status code
    public static int authStatus(int checkId, String checkPw) throws SQLException {
        int statusCode = 500;
        String sql =  "select USER_PW from T_USER where USER_ID = " + checkId + ";";
        try (ResultSet rset = DbAccess.select(sql)) {
            // No such ID
            if (!rset.next()) {
                statusCode = 404;

            // password correct or not
            } else {
                String truePw = rset.getString("USER_PW");
                statusCode = truePw.equals(checkPw) ? 200 : 401;
            }
        }

        return statusCode;
    }
}

package com.loginsystem.REST.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// connect database
public class DbAccess {
    //Consts
    static final String url = "jdbc:postgresql://localhost:5432/loginsystem";
    static final String user = "postgres";
    static final String pw = "pwpsql";

    public static ResultSet select (String sql) throws SQLException{
        try (
                Connection conn = DriverManager.getConnection(url, user, pw);
                Statement stmt = conn.createStatement();
            ) {
            ResultSet rset = stmt.executeQuery(sql);
            return rset;
        }

    }

    public static void insert (String sql) throws SQLException{
        try (
                Connection conn = DriverManager.getConnection(url, user, pw);
                Statement stmt = conn.createStatement();
            ) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        }

    }

}

/*
* database: loginsystem
* create table SQL:

CREATE TABLE T_DEPT(
      DEPT_NO      INT          PRIMARY KEY    NOT NULL,
      DEPT_NAME    VARCHAR(20)  NOT NULL
      );

CREATE TABLE T_USER(
      USER_ID      INT          PRIMARY KEY    NOT NULL,
      USER_PW      VARCHAR(32)  NOT NULL,
      USER_NAME    VARCHAR(20)  NOT NULL,
      DEPT_NO      INT          references T_DEPT(DEPT_NO),
      RGST_DATE    DATE         NOT NULL
      );

CREATE TABLE T_LOGIN(
      LOGIN_NO     INT          PRIMARY KEY    NOT NULL,
      USER_ID      INT          references T_USER(USER_ID),
      LOGIN_DT     TIMESTAMPTZ  NOT NULL,
      LOGIN_HOST   text
      );

INSERT INTO T_DEPT VALUES(1001,'TEST部署0');
INSERT INTO T_DEPT VALUES(1002,'TEST部署1');
INSERT INTO T_DEPT VALUES(1003,'TEST部署2');

*/

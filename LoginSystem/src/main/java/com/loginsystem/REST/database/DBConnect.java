package com.loginsystem.REST.database;

//the connect information of database
public class DBConnect {
	public static final String url = "jdbc:postgresql://localhost:5432/loginsystem";
	public static final String user = "postgres";
	public static final String pw = "pwpsql";
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
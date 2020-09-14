package com.loginsystem.REST.db;

public class ConnectDb {
	private final String url = "jdbc:postgresql://localhost:5432/loginsystem";
	private final String pgUser = "postgres";
	private final String pgPw = "pwpsql";

	public String url() {
		return url;
	}

	public String user() {
		return pgUser;
	}

	public String pw() {
		return pgPw;
	}
}

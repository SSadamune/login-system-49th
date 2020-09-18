package com.loginsystem.REST.util;

public class ValidChecker {
	public static boolean idValid (String id) {
		return id != null && id.matches("[0-9]{1,8}");
	}
	public static boolean pwValid (String pw) {
		return pw != null && pw.matches("[0-9a-zA-Z_]{6,32}");
	}
	public static boolean nameValid (String name) {
		return name != null &&
				name.matches("[\\sa-zA-Z\\p{InCJKUnifiedIdeographs}\\p{InHiragana}\\p{InKatakana}]{1,20}");
	}
	public static boolean deptNoValid (String deptNo) {
		return deptNo != null && deptNo.matches("[0-9]{1,4}");
	}
}

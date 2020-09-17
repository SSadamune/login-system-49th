package com.loginsystem.REST.util;

import java.util.regex.Pattern;

public class Validation {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		int userid = 1001;
		int deptno = 0001;
		String userId = String.valueOf(userid);
		String userPw = "12345abcde";
		String userName = "tanaka";
		String DeptNo = String.valueOf(deptno);

		//ユーザIDのバリデーションチェック
		if (!Pattern.matches("[0-9]{1,8}", userId)) {
			System.out.println("Parameter userId invalid. Must be less than 8 digits.");
		} else {
			System.out.println("success.");
		}

		//パスワードのバリデーションチェック
		if (!Pattern.matches("[0-9a-zA-Z]{10,32}", userPw)) {
			System.out.println("Parameter usePw invalid. Must be more than 10 and less than 32 characters.");
		} else {
			System.out.println("success.");
		}

		//氏名のバリデーションチェック
		if (!Pattern.matches(".{1,16}", userName)) {
			System.out.println("Parameter useName invalid.");
		} else {
			System.out.println("success.");
		}

		//部署番号のバリデーションチェック
		if (!Pattern.matches("[0-9]{1,4}", DeptNo)) {
			System.out.println("Parameter DeptNo invalid. Must be less than 4 digits.");
		} else {
			System.out.println("success.");
		}

	}

}

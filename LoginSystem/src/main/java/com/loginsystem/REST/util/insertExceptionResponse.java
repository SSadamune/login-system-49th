package com.loginsystem.REST.util;

import java.sql.SQLException;

public class insertExceptionResponse {
	private int statusCode;
	private String errorMessage;

	public int getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public insertExceptionResponse(SQLException ex) {
		if (ex.getSQLState().equals("23503")) {
			// FOREIGN KEY VIOLATION
			statusCode = 400;
			errorMessage = JsonResponse.message("parameter_invalid", "[dept_no] invalid. Check the dept list");

		} else if (ex.getSQLState().equals("23505")) {
			// UNIQUE VIOLATION
			statusCode = 400;
			errorMessage = JsonResponse.message("parameter_invalid", "[id] already exsits");

		} else {
			statusCode = 500;
			errorMessage = JsonResponse.message("SQL_exception", "unexpected SQL Exception: " + ex.getSQLState());
		}

		// about sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
	}

}

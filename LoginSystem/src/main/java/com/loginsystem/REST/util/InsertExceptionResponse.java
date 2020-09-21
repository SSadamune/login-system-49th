package com.loginsystem.REST.util;

import java.sql.SQLException;

public class InsertExceptionResponse {
	private int statusCode;
	private String errorMessage;

	public int getStatusCode() {
		return this.statusCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	// response-body when exception occurs at [POST /api/v1.0/users]
	public InsertExceptionResponse(SQLException ex) {
		if (ex.getSQLState().equals("23503")) {
			// FOREIGN KEY VIOLATION
			statusCode = 400;
			this.errorMessage = JsonResponse.statusData("parameter_invalid", "[dept_no] invalid. Check the dept list");

		} else if (ex.getSQLState().equals("23505")) {
			// UNIQUE VIOLATION
			statusCode = 400;
			this.errorMessage = JsonResponse.statusData("parameter_invalid", "[id] already exsits");

		} else {
			statusCode = 500;
			this.errorMessage = JsonResponse.statusData("sql_exception",
			        "{\"sql_state\": \"" + ex.getSQLState() +
			        "\", \"error_message\": \"" + ex.getMessage() + "\"}");
		}

		// about sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
	}

}

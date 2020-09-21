package com.loginsystem.REST.util;

import java.sql.SQLException;

/*
 *  the response for the case any sql exception occured
 *  according to the current api and sql state, generate the statusCode and jsonBody
 *  about sql state: www.postgresql.org/docs/8.4/errcodes-appendix.html
 */
public class SqlExceptionResponse {
    private int statusCode;
    private String jsonBody;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getErrorMessage() {
        return this.jsonBody;
    }

    public SqlExceptionResponse(String apiName, SQLException ex) {
        switch (apiName) {
        case "post users": byPostUsers(ex);
        case "get users": byGetUsers(ex);
        }
    }

    // if exception occurs at [POST /api/v1.0/users]
    private void byPostUsers(SQLException ex) {
        if (ex.getSQLState().equals("23503")) {
            // FOREIGN KEY VIOLATION
            statusCode = 400;
            this.jsonBody = JsonString.responseBody("parameter_invalid", "[dept_no] invalid. Check the dept list");

        } else if (ex.getSQLState().equals("23505")) {
            // UNIQUE VIOLATION
            statusCode = 400;
            this.jsonBody = JsonString.responseBody("parameter_invalid", "requested [id] already exsits");

        } else {
            statusCode = 500;
            this.jsonBody = JsonString.responseBody("sql_exception",
                    "{\"sql_state\": \"" + ex.getSQLState() +
                    "\", \"error_message\": \"" + ex.getMessage() + "\"}");
        }

    }

    // if exception occurs at [Get /api/v1.0/users/{user_id}]
    private void byGetUsers(SQLException ex) {
        if (ex.getSQLState().equals("24000")) {
            // FOREIGN KEY VIOLATION
            statusCode = 404;
            this.jsonBody = JsonString.responseBody("not_found", "requested [id] not found");

        } else {
            statusCode = 500;
            this.jsonBody = JsonString.responseBody("sql_exception",
                    "{\"sql_state\": \"" + ex.getSQLState() +
                    "\", \"error_message\": \"" + ex.getMessage() + "\"}");
        }

    }

}

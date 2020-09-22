package com.loginsystem.REST.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.loginsystem.REST.database.UserInfo;
import com.loginsystem.REST.util.JsonString;
import com.loginsystem.REST.util.PostReader;
import com.loginsystem.REST.util.ValidChecker;

/**
 * Servlet implementation class Login
 */
@WebServlet("/api/v1.0/password")
public class Password extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Password() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Guide users to use the post method
        response.setStatus(400);
        response.getWriter().write(JsonString.responseBody("method_invalid",
                "use POST method to check a [id][pw] pair"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* TODO
         * Check whether the ID password pair matches
         * content-type of post body: application/json
         * such as {"id":1009,"pw":"testpassword"}
         */
        response.setContentType("application/json;charset=UTF-8");

        // get Userinfo Object from request
        UserInfo checkUser = new UserInfo();
        try {
            checkUser = new Gson().fromJson(PostReader.toJsonStr(request), UserInfo.class);

        } catch (Exception e) {
            // case data-type invalid, such as id = "apple" (should be integer)
            response.setStatus(400);
            response.getWriter().write(JsonString.responseBody("parameter_invalid",
                    "[(int)id] and [(str)pw] are required"));
            return ;
        }

        // confirm the validation
        ValidChecker vc = new ValidChecker();
        if (!(vc.isIdPwValid(checkUser))) {
            // case any parameter invalid, such as id = 1234567890 (too long)
            response.setStatus(400);
            response.getWriter().write(JsonString.responseBody("parameter_invalid", vc.getMessage()));
            return ;
        }

        // check the password correct or not
        try {
            int checkResult = checkUser.authStatus();

            switch (checkResult) {
            case 200:
                response.setStatus(200);
                response.getWriter().write(JsonString.responseBody("OK", "password correct"));
                break;
            case 401:
                response.setStatus(401);
                response.getWriter().write(JsonString.responseBody("Auth_failured", "password incorrect"));
                break;
            case 404:
                response.setStatus(404);
                response.getWriter().write(JsonString.responseBody("not_found", "[id] not found"));
                break;
            }

        } catch (SQLException ex) {
            // unexpected sql exception
            response.setStatus(500);
            response.getWriter().write(JsonString.responseBody("sql_exception",
                    JsonString.unexpectedSqlException(ex.getSQLState(), ex.getLocalizedMessage())));

        }

    }

}

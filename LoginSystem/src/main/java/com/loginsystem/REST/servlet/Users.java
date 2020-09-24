package com.loginsystem.REST.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.loginsystem.REST.database.UserInfo;
import com.loginsystem.REST.util.JsonString;
import com.loginsystem.REST.util.PostReader;
import com.loginsystem.REST.util.SqlExceptionResponse;
import com.loginsystem.REST.util.ValidChecker;

@WebServlet(value = {"/api/v1.0/users","/api/v1.0/users/*"})
public class Users extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Users() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* TODO
         * response information of user
         * accept URL as "/users?id=1004" or "/users/1004"
         */
        response.setContentType("application/json;charset=UTF-8");

        // Determine the URL pattern as "/users?id=1004" or "/users/1004"
        String urlPath = request.getPathInfo();
        String strUserId = urlPath == null ? request.getParameter("id") : urlPath.substring(1);;

        // check validation of id
        if (!(ValidChecker.idValid(strUserId))) {
            response.setStatus(400);
            response.getWriter().write(JsonString.responseBody("parameter_invalid",
                    "[id] invalid. Must be 8 digits."));
            return ;
        }

        int userId = Integer.parseInt(strUserId);

        // select from DB
        try {
            UserInfo getUser = new UserInfo();
            String jsonUser = getUser.selectFromDb(userId);
            response.setStatus(200);
            response.getWriter().write(JsonString.responseBody("got_it!", jsonUser));

        } catch (SQLException ex) {
            // use class SqlExceptionResponse to generate response body
            SqlExceptionResponse expRsp = new SqlExceptionResponse("get users", ex);
            response.setStatus(expRsp.getStatusCode());
            response.getWriter().write(expRsp.getErrorMessage());

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* TODO
         * insert data into table
         * content-type of post body: application/json
         * such as {"id":1009, "pw":"testpassword", "name":"名前1009", "deptNo":1002}
         */
        response.setContentType("application/json;charset=UTF-8");

        // get Userinfo Object from request
        UserInfo postUser = new UserInfo();
        try {
            postUser = new Gson().fromJson(PostReader.toJsonStr(request), UserInfo.class);

        } catch (Exception e) {
            // case data-type invalid, such as id = "apple" (should be integer)
            response.setStatus(400);
            response.getWriter().write(JsonString.responseBody("parameter_invalid",
                    "[(int)id], [(str)pw], [(str)name], [(int)dept_no] required"));
            return ;
        }

        ValidChecker vc = new ValidChecker();
        // case any parameter invalid, such as id = 1234567890 (too long)
        if (!(vc.isRegisterDataValid(postUser))) {
            response.setStatus(400);
            response.getWriter().write(JsonString.responseBody("parameter_invalid", vc.getMessage()));
            return ;
        }

        // set register date
        postUser.setRgstDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        // insert data to DB
        try {
            postUser.insertIntoDb();
            response.setStatus(201);
            response.getWriter().write(JsonString.responseBody("user_info_created", postUser.toJsonStr()));

        } catch (SQLException ex) {
            // sql exception
            SqlExceptionResponse expRsp = new SqlExceptionResponse("post users", ex);
            response.setStatus(expRsp.getStatusCode());
            response.getWriter().write(expRsp.getErrorMessage());
        }

    }

}

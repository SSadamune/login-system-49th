# RESTful login system

## what's this
* a project exercise of system developping
* a simple RESTful login system without any framework

## three APIs contained
* user login: 
    *  GET /api/v1.0/users?id={user id} or GET /users/{user id}
* password verification (but very simple and naive): 
    *  POST /api/v1.0/password
    *  {"id":..., "pw":...}
* user information acquisition: 
    *  POST /api/v1.0/users
    *  {"id":..., "pw":..., "name":..., "deptNo":...}

## features
* using PostgreSQL to save the data
* validity verification of request parameters
* using Gson to serializate/deserializate JSON
* as RESTful as I can

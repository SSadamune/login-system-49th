# RESTful login system

## background
* project exercise about system developping
* a simple RESTful login system without any framework

## three APIs contained
* user login: 
    *  GET /api/v1.0/users?id={user id} or GET /users/{user id}
* password verification: 
    *  POST /api/v1.0/password
    *  {"id":..., "pw":...}
* user information acquisition: 
    *  POST /api/v1.0/users
    *  {"id":..., "pw":..., "name":..., "deptNo":...}

## features
* used PostgreSQL to save the data
* realized simple validity verification of request

Simple User API
================
A simple api server with an in memory database

Actions
--------
- `GET /users` -- list all users
- `GET /USER/{id}` -- get user details
- `POST /USER/{id}` -- update a user
- `POST /USERS` -- create a new user

Initial Dataset
---------------
```javascript
[{  
    "id":1,
    "firstName":"Jim",
    "lastName":"Henson"
 },
 {  
    "id":2,
    "firstName":"Elon",
    "lastName":"Musk"
 }
]
```
How to run
----------
`mvn spring-boot:run`

then open `http://localhost:8080/users`

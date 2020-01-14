# REST API with cache
## How to check cache
- create a new user
- get user by id
- get user by id - it should be cached
- update user
- get again user by id
- try again get user after 30s

## REST

##### For test purpose you can use a Postman
- ##### GET 
    - retrieves all Users
    - url: http://localhost:8080/api/v1/users
    - response:
    ```
		[
			{
				"id": 1,
				"firstName": "Dan",
				"lastName": "Ski",
				"email": "test3@any.com"
			},
			{
				"id": 2,
				"firstName": "Jans",
				"lastName": "Ski",
				"email": "jans1@any.com"
			}
		]
    ```    
- ##### POST
    - url: http://localhost:8080/api/v1/users
    - payload:
    ```
		{
			"firstName": "Dan",
			"lastName": "Ski",
			"email": "test@any.com"
		}
    ```
- ##### PUT
    - url: http://localhost:8080/api/v1/users
    - payload: 
    ```
		{
			"id": 1,
			"firstName": "Dan",
			"lastName": "Ski",
			"email": "test2@any.com"
		}   
    ```
- ##### GET
    - retrieves single User
    - url: http://localhost:8080/api/v1/users/1
    - response:
    ```
		{
			"id": 1,
			"firstName": "Dan",
			"lastName": "Ski",
			"email": "test2@any.com"
		}
    ```
- ##### DELETE
    - deletes single User by id
    - url: http://localhost:8080/api/v1/users/1
	
## please keep in mind
- run mvn clean install before you run Spring application
- mvn clean install when any mapping will change
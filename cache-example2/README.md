# REST API with cache
## How to check cache
- create a new user
- get user by id
- get user by id - it should be cached
- update user
- get again user by id

## Retrieve all
GET: http://localhost:8080/api/v1/users
## Create new user
POST: http://localhost:8080/api/v1/users
{
	"firstName": "Dan",
	"lastName": "Ski",
	"email": "test@any.com"
}
## Retrieve single user
http://localhost:8080/api/v1/users/1
## update 
http://localhost:8080/api/v1/users
{
	"id": 1,
	"firstName": "Dan",
	"lastName": "Ski",
	"email": "test2@any.com"
}

## please keep in mind
- run mvn clean install at before you run Spring application
- mvn clean install when any mapping will change
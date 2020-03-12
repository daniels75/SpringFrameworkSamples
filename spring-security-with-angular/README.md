## Content
### oauth-resource-server - resource server - not implemented yet
- Available at address: http://localhost:8082/spring-security-oauth-resource

# Simple examples of the auth servers and simple ui with authentication and authorization
## oauth-server - this is main auth server 
1. run from intellij or command line
2. server is available at address: http://localhost:8081/spring-security-oauth-server/

## oauth-jwt-server - this alternative - auth server uses a jwt token
1. run from intellij or command line
2. server is available at address: http://localhost:8081/spring-security-oauth-server/
3. Some helpful links
- https://jwt.io/introduction/
- https://jwt.io/#libraries
- https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html/boot-features-security-oauth2-authorization-server.html#oauth2-boot-authorization-server-password-grant

## simple-ui - this is simple ui
1. Available at address: http://localhost:8090/simple-ui

## simple-angular-app angular application
1. Available at address: http://localhost:8089/

---
  
### Simple authorization server
1. Go to spring-security-with-angular/oauth-server
2. Run: Spring Authenticaiton server -> oauth-server

# Simple authorization with simple UI
1. Run: Spring Authenticaiton server -> oauth-server
2. Auth token is available: http://localhost:8081/spring-security-oauth-server/oauth/token
3. Run UI Part -> simple-ui
4. Open simple UI: http://localhost:8090/simple-ui
5. Authenticate with user: john/123
6. Accept authorization 
7. After all UI part should be authorized 

### Spring security with Angular
#### Add UI part
1. go to spring-security-with-angular/simple-angular-app
2. run: mvn clean install
3. go to spring-security-with-angular/simple-angular-app/src/main/resources/
4. run: npm start or ng serve
5. http://localhost:8089/
#### Add backend with server


### Some helpful links and information

## First of all you need run authentication server:
	spring-security-with-angular/oauth-server
	
   Auth server url: http://localhost:8081/spring-security-oauth-server/
 

## Step1 - retrieve code - via browser or Postman
	### The code will be returned after login with john/123
	http://localhost:8081/spring-security-oauth-server/oauth/authorize?
		client_id=fooClientIdPassword&response_type=code&scope=user_info&redirect_uri=http://localhost:8091/
		
		
	### Response:
		http://localhost:8091/?code=ITIK2f
 
## Step 2 - retrieve token with POSTMAN
	### Request
		 http://localhost:8081/spring-security-oauth-server/oauth/token
		 ```
		 grant_type		 authorization_code
		 code 			 ITIK2f
		 redirect_uri    http://localhost:8091/
		 client_id       fooClientIdPassword   [optional]
		 client_secret   secret
 
		 x-www-form-urlencoded
			here should be: (Authorization base64Encoded(client_id:client_secret)).
				Authorization Basic Zm9vQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=
		```
 
	### Response:
		```
		{
			"access_token": "8c737be0-194b-4af5-b21a-8c424b8d97f4",
			"token_type": "bearer",
			"refresh_token": "f98419c0-cafb-4e2f-8e15-f248a226c744",
			"expires_in": 42832,
			"scope": "user_info"
		}
		```

## Step 3 - with token we have acess to the resources - via POSTMAN
	### Access resource from auth server with retrived access token
	http://localhost:8081/spring-security-oauth-server/user/simple

	GET: http://localhost:8081/spring-security-oauth-server/user/simple?access_token=8c737be0-194b-4af5-b21a-8c424b8d97f4
		or
	GET http://localhost:8081/spring-security-oauth-server/user/simple
	+ Header
		Authorization Bearer 8c737be0-194b-4af5-b21a-8c424b8d97f4


	### Response:
	```
	{
		"id": 1,
		"name": "john",
		"surname": "kowalski"
	}
	```
	
## Step 4 - Revoke token

http://localhost:8081/spring-security-oauth-server/oauth/token/revokeById/94548d45-6b78-4b9e-bc56-5098c4323540
+ Header
Authorization Bearer 94548d45-6b78-4b9e-bc56-5098c4323540

## Step 5 - Tokens

http://localhost:8081/spring-security-oauth-server/tokens
+ Header
Authorization Bearer 94548d45-6b78-4b9e-bc56-5098c4323540

---
## Step - how to to use autentication server + resource server
1. Run authentication server: oauth-jwt-server
2. Run resource server: oauth-resource-server
3. Retrieve token code - via browser or Postman
	#### The token code will returned after login with john/123
		
	http://localhost:8081/spring-security-oauth-server/oauth/authorize?
		client_id=fooClientIdPassword&response_type=code&scope=user_info%20read%20write%20foo&redirect_uri=http://localhost:8091/		
 
#### Retrieve token with POSTMAN
	#### Request
		 http://localhost:8081/spring-security-oauth-server/oauth/token
		 ```
		 grant_type		 authorization_code
		 code 			 ptY8pP
		 redirect_uri    http://localhost:8091/
		 client_id       fooClientIdPassword   [optional]
		 client_secret   secret
 
		 Headers:
		 Content-Type: application/x-www-form-urlencoded
		 Authorization: Basic Zm9vQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=
		 
			here should be: (Authorization base64Encoded(client_id:client_secret)).
				Authorization Basic Zm9vQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=
		```
 
	#### Response:
		```
		{
			"access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODMxOTEyNTYsInVzZXJfbmFtZSI6ImpvaG4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYTE3YWJlMTUtN2JlYy00ZTRiLTkyNDUtZjQ0ZDRlODY4NTU4IiwiY2xpZW50X2lkIjoiZm9vQ2xpZW50SWRQYXNzd29yZCIsInNjb3BlIjpbImZvbyIsInJlYWQiLCJ1c2VyX2luZm8iLCJ3cml0ZSJdfQ.Mtmx83cgUNGnBMDkLpcchdzfGIXs88rKoa-7qUfjgiQ",
			"token_type": "bearer",
			"refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huIiwic2NvcGUiOlsiZm9vIiwicmVhZCIsInVzZXJfaW5mbyIsIndyaXRlIl0sImF0aSI6ImExN2FiZTE1LTdiZWMtNGU0Yi05MjQ1LWY0NGQ0ZTg2ODU1OCIsImV4cCI6MTU4NTc3OTY1NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjVmYTc3ZGY5LWYzMDYtNDAxYS1iMDYxLTcxNTM5NWQyZjdjNCIsImNsaWVudF9pZCI6ImZvb0NsaWVudElkUGFzc3dvcmQifQ.dmB-TNHtKUfE0TyNOcBY88l7bKwkxvQXD-MnxBp-kK8",
			"expires_in": 3599,
			"scope": "foo read user_info write",
			"jti": "a17abe15-7bec-4e4b-9245-f44d4e868558"
		}
		```
#### Retrive resource from the resource server
##### http://localhost:8082/spring-security-oauth-resource/foos/1
with header:
	Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODMxOTEyNTYsInVzZXJfbmFtZSI6ImpvaG4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYTE3YWJlMTUtN2JlYy00ZTRiLTkyNDUtZjQ0ZDRlODY4NTU4IiwiY2xpZW50X2lkIjoiZm9vQ2xpZW50SWRQYXNzd29yZCIsInNjb3BlIjpbImZvbyIsInJlYWQiLCJ1c2VyX2luZm8iLCJ3cml0ZSJdfQ.Mtmx83cgUNGnBMDkLpcchdzfGIXs88rKoa-7qUfjgiQ

#### you can check authorization token with following:
https://jwt.io/

just place eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODMxOTEyNTYsInVzZXJfbmFtZSI6ImpvaG4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYTE3YWJlMTUtN2JlYy00ZTRiLTkyNDUtZjQ0ZDRlODY4NTU4IiwiY2xpZW50X2lkIjoiZm9vQ2xpZW50SWRQYXNzd29yZCIsInNjb3BlIjpbImZvbyIsInJlYWQiLCJ1c2VyX2luZm8iLCJ3cml0ZSJdfQ.Mtmx83cgUNGnBMDkLpcchdzfGIXs88rKoa-7qUfjgiQ
in the Encoded field	

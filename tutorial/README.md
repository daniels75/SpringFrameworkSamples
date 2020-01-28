# Spring tutorial

#### 1. Spring Bean annotations
- spring-bean-annotations

#### 2. Spring Security demo
- spring-security-demo

- ##### GET 
    - retrieves all Todos with user/user123 credential
    - url: http://localhost:8080/api/todo
    - response:
    ```
		[
			{
				"id": 1,
				"title": "First TODO",
				"description": "This is a first TODO"
			},
			{
				"id": 2,
				"title": "Second TODO",
				"description": "This is a second TODO"
			}
		]
    ```    
- ##### GET with user/user123 credential
    - retrieves single Todo
    - url: http://localhost:8080/api/todo/1
    - response:
    ```
		{
			"id": 1,
			"title": "First TODO",
			"description": "This is a first TODO"
		}
    ```
- ##### GET 
    - retrieves Admin info with admin/admin123 credential
    - url: http://localhost:8080/api/admin
    - response:
    ```
		[
			{
				"id": 1,
				"title": "First Admin",
				"description": "admin desc1"
			},
			{
				"id": 2,
				"title": "Second Acmin",
				"description": "admin desc2"
			}
		]
    ```    
- ##### GET with admin/admin123 credential
    - retrieves single Admi info
    - url: http://localhost:8080/api/todo/1
    - response:
    ```
		{
			"id": 1,
			"title": "Admin",
			"description": "Admin account"
		}
    ```	
#### Hints
#####For test purpose you can use a Postman
##### see ExceptionTranslationFilter
##### click Clear in the Postman - since some values can be cached


#### 3. Spring Security JWT
- spring-jwt-demo
- ##### GET 
    - retrieves single user with user/user123 or admin/admin123 credential
    - url: http://localhost:8080/api/user
- ##### GET 
    - retrieves all users with user/user123 or admin/admin123 credential
    - url: http://localhost:8080/api/all	

# Spring tutorial

#### Spring Bean annotations
- spring-bean-annotations

#### Spring Security demo
- spring-security-demo
##### For test purpose you can use a Postman
- ##### GET 
    - retrieves all Todos
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
- ##### GET
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

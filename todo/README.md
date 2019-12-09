# Todo application
 Final goal is craete a Spring Backend server + Angular in the frontend
 
 
1. [v] Create an application
2. [v] Add Todo Entity
3. [v] Add Rest Todo /api/v1/todos
4. [v] Add GET for all Todos REST
5. [v] Add POST REST
6. [v] Add Todo Repository
7. [v] Add fake data for Todo - create a bean
8. [v] Add service for Todo
9. [v] Add service to the Repository
10. [-] First try with external Gui application e.g. Angular
11. [-] Disable CORS
101. [-] Add PUT REST
102. [-] Add DELETE REST
103. [-] Integrate Spring and Angular into one application



## Rest
- GET all todos http://localhost:8080/api/v1/todos
- POST - use Postman with
  - url: http://localhost:8080/api/v1/todos
  - payload:
    {
    	"title": "simple title",
    	"description": "simple description",
    	"completed": false
    }
    

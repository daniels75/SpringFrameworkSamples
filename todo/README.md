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
10. [v] First try with external Gui application e.g. Angular
11. [v] Disable CORS
12. [v] Add PUT REST
13. [v] Add GET REST for single TODO
14. [v] Add DELETE REST
103. [-] Add mapper from that maps Entity/DTO
104. [-] Use mapper in the REST
105. [-] Add error handling for non existent Todos etc.
200. [-] Integrate Spring and Angular into one application



## Rest
##### For test purpose you can use a Postman
- ##### GET 
    - retrieves all Todos
    - url: http://localhost:8080/api/v1/todos
    - response:
    ```
        [
            {
                "id": 1,
                "title": "First TODO",
                "description": "Add real Todo REST",
                "complete": false
            },
            {
                "id": 2,
                "title": "Second TODO",
                "description": "Add service",
                "complete": false
            },
            {
                "id": 3,
                "title": "Third TODO",
                "description": "Add repository",
                "complete": false
            }
        ]
    ```    
- ##### POST
    - url: http://localhost:8080/api/v1/todos
    - payload:
    ```
        {
            "title": "simple title",
            "description": "simple description",
            "completed": false
        }
    ```
- ##### PUT
    - url: http://localhost:8080/api/v1/todos
    - payload: 
    ```
        {
            "id": 2,
            "title": "Updated second todo",
            "description": "This todo has been updated",
            "completed": false
        }    
    ```
- ##### GET
    - retrieves single Todo
    - url: http://localhost:8080/api/v1/todos/3
    - response:
    ```
        {
            "id": 3,
            "title": "Third TODO",
            "description": "Add repository",
            "complete": false
        }
    ```
- ##### DELETE
    - deletes single Todo
    - url: http://localhost:8080/api/v1/todos/3

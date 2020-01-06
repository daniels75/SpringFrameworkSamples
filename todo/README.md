# Todo application
Final goal is creating a Spring Backend server + Angular in the frontend
 
 
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
15. [v] Add DTO for Todo
16. [v] Add mapper library from that maps Entity/DTO (keep in mind to run: mvn clean compile when library and plugin is added)
17. [v] Use mapper in the service
18. [v]  H2 DB should be stored in the user directory
19. [v] Add simple controller, and redirect view
20. [v] Remove @EnableWebMvc anc CORS and add another CORS
21. [v] Redefine project - add backend and frontend project
22. [v] Add frontend project from the angular-cli
23. [v] Add maven plugins for the frontend
24. [v] Remove static folder from api project - to check
25. [v] index.html is taken from the Angular not from the Spring template
26. [v] Remove fake data
27. [v] Changed db config from create -> update
28. [v] Enable again configuration for CORS
29. [v] Create TODO ui/frontend
-  [v] Add Todo class
-  [v] Add TodoService
-  [v] Add methods in the TodoService
-  [v] Add template for the app component
-  [v] Add services for the app component
- [v] Add  todo-list-header component
- [v] Move header logic to the todo-list header component
- [v] Move logic to todo-list component
- [v] Create item component
- [v] create footer component
- [v] Move logic from app component to footer component
- [v] fake json server npm install json-server --save + package.json script  then run: npm run json-server
- [v] Added Api Service
- [v] Add ApiService hadlers to the TodoDataService
- [v] Added css files - these have to be imporved
- [v] Do clean up
- [v] add drag and drop
	ng add @angular/material
- [v] added multi update	
30. [v] Find an idea for new css for Todo frontend
31. [v] Apply a new css for frontend
32. [v] Added single update for all todos - ui + REST
32. [v] Clean up in the code
33. [v] Add nicer css for displayed list of todo
34. [v] Change css style for drag-and-drop
35. [v] Add ordered all todos
35. [-] Do clean up in the ui
36. [-] add warning for empty todo/at least 3 sings for todo
37. [-] Add better validation in the backend
38. [-] Add validation in the ui
39. [-] Change responses to ResponseEntity
101. [-]  Add Cache handling
201. [-] Add error handling for non existent Todos etc.
205. [-] Integrate Spring and Angular into one application
206. [-] Check if we templates can be remove and application can deal only with a ui/static part

### problems
clean dist in the frontend when you cannot see up-to-date ui
### command line
Spring: todo>java -jar api\target\spring-angular-todo-api-0.0.1-SNAPSHOT.jar
	+ change in the frontend/src/environments/environment.ts
	from     apiUrl: 'http://localhost:3000'
	apiUrl: 'http://localhost:8080/api/v1'
UI: npm run json-server + ng serve
+ change in the frontend/src/environments/environment.ts
	     apiUrl: 'http://localhost:3000'


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

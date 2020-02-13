# Simple examples of the auth servers and simple ui with authentication and authorization
## oauth-server - this is main auth server 
1. run from intellij or command line
2. server is available at address: http://localhost:8081/spring-security-oauth-server/

## oauth-jwt-server - this alternative - auth server uses a jwt token
1. run from intellij or command line
2. server is available at address: http://localhost:8081/spring-security-oauth-server/

## simple-ui - this is simple ui
1. Available at address: http://localhost:8090/simple-ui

## simple-angular-app angular application
1. Available at address: http://localhost:8089/



# Simple authorization server
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

# Spring security with Angular
## Add UI part
1. go to spring-security-with-angular/simple-angular-app
2. run: mvn clean install
3. go to spring-security-with-angular/simple-angular-app/src/main/resources/
4. run: npm start or ng serve
5. http://localhost:8089/
## Add backend with server


### Plan
#### Add UI part
1. Generate frontent from angular-cli  [Done]
2. Change configuration - port 8089    [Done]
3. Add components                      [Done]
4. Check application                   [Done]

#### Add backend with server
1. Create very simple auth server
2. Consider using jwt token
3. 

#### Optionally add resource server
1. Add seperate resource server
2. Configure it to work with with auth server

#### Optionally add another UI appliaction

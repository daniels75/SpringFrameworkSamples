# Simple authorization with simple ui
1. Run: Spring Authenticaiton server -> oauth-server
2. Auth token is available: http://localhost:8081/spring-security-oauth-server/oauth/token
3. Run UI Part -> simple-ui
4. Open simple UI: http://localhost:8086/simple-ui
5. Authenticate with user: john/123
6. Accept authorization 
7. After all UI part should be authorized 

# Spring security with angular
## Add UI part
1. go to spring-security-with-angular/simple-angular-app
2. run: mvn clean install
3. go to spring-security-with-angular/simple-angular-app/src/main/resources/
4. run: npm start or ng serve
5. http://localhost:9091/
## Add backend with server




### Plan
#### Add UI part
1. Generate frontent from angular-cli  [Done]
2. Change configuration - port 9091    [Done]
3. Add components                      [Done]
4. Check application                   [Done]

#### Add backend with server
1. Create very simple auth server
2. Consider using jwt token
3. 
#### Optionally add resource server

#### Optionally add another UI appliaction
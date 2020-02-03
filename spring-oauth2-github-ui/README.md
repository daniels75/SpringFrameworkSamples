# Spring boot UI - login via github
- spring-oauth2-github-ui
	- http://localhost:8086/github-ui
	
## Please see here:
- https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/

	
registration:
  foobar:
	client-id: ab5f859e82985968aba1 -- must be from registered app in the github account
	client-secret: 91e6a1d6c6e581a961e06e6ef1fb4ff2fba3a56e -- must be from registered app in the github account
	authorization-grant-type: authorization_code
	## that must correspond to the Authorization callback URL in the github app name
	redirect-uri: http://localhost:8086/github-ui/login/oauth2/code/foobar
provider:
  foobar:
	authorization-uri: https://github.com/login/oauth/authorize
	token-uri: https://github.com/login/oauth/access_token
	userInfoUri: https://api.github.com/user
	## this can be id, login etc. must must match to one of the user account properties
	user-name-attribute: id	
	
	
#### Everything above can be "simplified" as follows, but then you don't see what Spring is doing behind of the scene :)

spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: ab5f859e82985968aba1
            client-secret: 91e6a1d6c6e581a961e06e6ef1fb4ff2fba3a56e	
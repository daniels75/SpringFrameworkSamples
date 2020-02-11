
1. The page isn’t redirecting properly
http://localhost:8090/simple-ui/login/oauth2/code/custom?code=qBQtXl&state=xVBZseJaRdDU4_dcpzIjA2hW-imO9bY-89-eg277BiQ%3D

@EnableResourceServer annotation on WebSecurityConfig is needed

@SpringBootApplication
@EnableResourceServer
public class OauthServerApplication


2. Full authentication is required to access this resourceunauthorized
@Order annotation on WebSecurityConfig is needed
@Order(1)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
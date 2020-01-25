package org.daniels.spring.tutorial.securitydemo.config;

import org.daniels.spring.tutorial.securitydemo.web.CustomAccessDeniedHandler;
import org.daniels.spring.tutorial.securitydemo.web.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig2 extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("admin123")).roles("ADMIN")
                .and()
                .withUser("user").password(encoder().encode("user123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                // this is not needed in fact, since this only for
                // handling errors like 403 when user have no access
                // to the admin resources
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .authorizeRequests()
                /*
                .antMatchers("/api/customer")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                 */
                .antMatchers("/api/customer/**").permitAll()
                .antMatchers("/api/todo/**").authenticated()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .and()
                .httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint);

        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}


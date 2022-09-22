package rs.apps.nn.recipes.config;

import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private DataSource dataSource;
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select user_name, password, enabled from recipes.users where user_name=?")
            .authoritiesByUsernameQuery("select user_name, role from recipes.users where user_name=?")
        ;
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// https://stackoverflow.com/questions/57994953/ajax-request-with-spring-security-gives-403-forbidden
        
        String[] staticResources  =  {
            "/css/**",
            "/img/**",
            "/fonts/**",
            "/scripts/**",
            "/h2-console/**",
            "/images/**",
        };
        
    	http.csrf().disable().cors()
    	.and()
        .headers().frameOptions().disable()
        	.and()
        	.authorizeRequests()
        	.antMatchers(staticResources).permitAll()
        	.anyRequest().authenticated()
        	.and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/recipe/list", true)
            .permitAll()
            // .usernameParameter("email")
            .and()
            .logout().permitAll()
            ;     
    }
}
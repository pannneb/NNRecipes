package rs.apps.nn.recipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import rs.apps.nn.recipes.common.MessageSourceGuessMeImpl;
import rs.apps.nn.recipes.service.MyUserDetailsService;

//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig111  { // extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//                auth
//                    .userDetailsService(userDetailsService)
//                    .passwordEncoder(userDetailsService.getPassEnc());
//    }
//    
//    // roles admin allow to access /admin/**
//    // roles user allow to access /user/**
//    // custom 403 access denied handler
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//					.antMatchers("/", "/home", "/about").permitAll()
//					.antMatchers("/admin/**").hasAnyRole("ADMIN")
//					.antMatchers("/recipes/**").hasAnyRole("ADMIN")
//					.antMatchers("/user/**").hasAnyRole("USER")
//					.anyRequest().authenticated()
//                .and()
//                .formLogin()
//					.loginPage("/login")
//					.permitAll()
//					.and()
//                .logout()
//					.permitAll()
//					.and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//    }
//
//    // create two users, admin and user
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");
//    }
}
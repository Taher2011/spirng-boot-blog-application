package com.techgen.blog.config;


import com.techgen.blog.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // method level security, please check Post Controller's POST,PUT,DELETE API's
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests().
                antMatchers(HttpMethod.GET, "/api/**").permitAll(). // permitting only GET requests with /api/**
                antMatchers("/api/auth/**").permitAll(). // permitting only requests with /api/auth/**
                anyRequest().
                authenticated().
                and().
                httpBasic();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() { //this method used for InMemoryAuthentication
//        UserDetails user = User.builder().username("taher").password(passwordEncoder().encode("1234")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("1234")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

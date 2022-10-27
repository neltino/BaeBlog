package com.example.fashionblog.Config.SecurityConfig;

import com.example.fashionblog.Enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/create-user", "/", "/swagger-ui/**").permitAll()
                .antMatchers("/users/view-all").hasAuthority(Role.ADMIN.name())
                .antMatchers("/users/logout").hasAnyRole(Role.ADMIN.name(), Role.USER.name())

                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }
}

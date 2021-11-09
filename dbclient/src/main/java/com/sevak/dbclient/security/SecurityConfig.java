package com.sevak.dbclient.security;

import com.sevak.dbclient.models.Role;
import com.sevak.dbclient.security.detailes.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/users/registry").permitAll()
                .antMatchers("/users/delete").hasAuthority(Role.ADMIN.name())
                .antMatchers("/users/allUser").hasAuthority(Role.ADMIN.name())
                .antMatchers("/users/update").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, "/users/**").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                //.httpBasic();
                .formLogin()
                .loginPage("/users/login").permitAll()
                .defaultSuccessUrl("/users/success")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/users/login")
                .and()
                .csrf().disable();
    }



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("myUser")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


}

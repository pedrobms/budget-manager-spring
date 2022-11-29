package br.ufsm.csi.budgetmanagerspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.ufsm.csi.budgetmanagerspring.model.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        return new AuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/users").hasAuthority(Role.ADMIN.getValue())
                .antMatchers("/users/{userId}").access("@userSecurity.isAdmin(authentication) OR @userSecurity.hasUserId(request, #userId)")
                .antMatchers("/user/{userId}/transactions").access("@userSecurity.hasUserId(request, #userId)")
                .antMatchers("/user/{userId}/transactions/type/{type}").access("@userSecurity.hasUserId(request, #userId)")
                .antMatchers("/user/{userId}/transactions/category/{categoryId}").access("@userSecurity.hasUserIdinCategory(request, #userId, #categoryId)")
                .antMatchers("/user/{userId}/transactions/{transactionId}").access("@userSecurity.hasUserIdinTransaction(request, #userId, #transactionId)")
                .antMatchers("/user/{userId}/categories").access("@userSecurity.hasUserId(request, #userId)")
                .antMatchers("/user/{userId}/balance/**").access("@userSecurity.hasUserId(request, #userId)")
                .anyRequest().authenticated();
        
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
package com.portal.smarthealth.config;

import com.portal.smarthealth.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simpler API testing (consider enabling for production)
            .authorizeRequests()
                // Allow access to static resources and public pages
                .antMatchers("/css/**", "/js/**", "/images/**", "/", "/index", "/register", "/login", "/health-tips", "/facilities").permitAll()
                .antMatchers("/api/auth/**").permitAll() // Allow registration/login APIs
                .antMatchers("/api/facilities/all").permitAll() // Allow unauthenticated access to view facilities
                .antMatchers("/api/health-tips/**").permitAll() // Allow unauthenticated access to view health tips
                .antMatchers("/api/crowdfunding/projects").permitAll() // Allow unauthenticated access to view projects
                // Protect other pages/APIs
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .loginProcessingUrl("/doLogin") // URL to which login form is submitted
                .defaultSuccessUrl("/index", true) // Redirect after successful login
                .failureUrl("/login?error=true") // Redirect after failed login
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true") // Redirect after logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
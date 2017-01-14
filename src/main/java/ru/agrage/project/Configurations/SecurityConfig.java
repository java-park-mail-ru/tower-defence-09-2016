package ru.agrage.project.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.agrage.project.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ru.agrage.project.Service"})
@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/admin/**").access("hasRole('admin')")
                .antMatchers("/api/user/login/").permitAll()
                .antMatchers("/api/user/registration/").permitAll()
                .antMatchers("/api/user/logout/").access("hasRole('user') or hasRole('admin')")
                .antMatchers("/api/user/rating/").access("hasRole('user') or hasRole('admin')")
                .antMatchers("/api/user/session/").access("hasRole('user') or hasRole('admin')");
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/user/**"
                ).permitAll();
        http
                .headers().disable();
        http
                .sessionManagement();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    // Cors
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://towerdefensepvp.herokuapp.com", "https://91.78.137.174:3000", "https://127.0.0.1:3000");
            }
        };
    }


}

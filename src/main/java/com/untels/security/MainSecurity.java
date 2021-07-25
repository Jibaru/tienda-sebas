package com.untels.security;

import com.untels.security.entrypoint.AuthEntryPoint;
import com.untels.security.filter.SessionFilter;
import com.untels.security.service.UserDetailsServiceImpl;

/*
import com.untels.estadonutricional.security.handler.CustomAccessDeniedHandler;
import com.untels.estadonutricional.security.jwt.JwtEntryPoint;
import com.untels.estadonutricional.security.jwt.JwtTokenFilter;
import com.untels.estadonutricional.security.service.UserDetailsServiceImpl;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthEntryPoint authEntryPoint;

    @Bean
    public SessionFilter filtro() {
        return new SessionFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * @Bean public AccessDeniedHandler accessDeniedHandler() { return new
     * CustomAccessDeniedHandler(); }
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] resources = { "/", "/js/**", "/css/**", "/img/**" };

        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll().antMatchers(resources)
                .permitAll().anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(authEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling();
        // .accessDeniedHandler(accessDeniedHandler());

        http.addFilterBefore(filtro(), UsernamePasswordAuthenticationFilter.class);
    }
}
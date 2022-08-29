package com.jre.hireout.security.config;

import com.jre.hireout.security.filters.AuthenticationFilter;
import com.jre.hireout.security.filters.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/jobs/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/companies/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/company/save/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/contacts/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/contact/save/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/stock-items/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/stock-item/save/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/index.html").permitAll();
        http.authorizeRequests().anyRequest().authenticated(); // TODO: Re-enable when not using console.
        //http.authorizeRequests().anyRequest().permitAll(); // TODO: Remove when not using console
        //http.headers().frameOptions().sameOrigin(); // TODO: Remove when not using console
        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        */

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/token/refresh/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/jobs/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/companies/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/company/save/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/contacts/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/contact/save/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/stock-items/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/stock-item/save/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers("/index.html").permitAll()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManagerBean()))
                .addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

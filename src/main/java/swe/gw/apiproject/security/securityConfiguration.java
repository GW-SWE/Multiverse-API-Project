package swe.gw.apiproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService); //.passwordEncoder(new BCryptPasswordEncoder());

//        auth.inMemoryAuthentication()
//                .withUser("Callum")
//                .password("tests")
//                .roles("User");
//        auth.inMemoryAuthentication()
//                .withUser("siid")
//                .password("blah")
//                .roles("User");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//
//            }
//        });
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/users/all").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/create").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("users/findusername").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
}

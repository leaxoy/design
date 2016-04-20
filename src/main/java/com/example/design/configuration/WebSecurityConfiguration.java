package com.example.design.configuration;

import com.example.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lxh on 4/13/16.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/signin")
                .permitAll().and().logout().logoutUrl("/logout").permitAll();
        http.rememberMe().rememberMeParameter("rememberMe");

        http.setSharedObject(SecurityContextRepository.class, new SecurityContextRepository() {
            private HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();

            @Override
            public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
                System.out.println(3);
                SecurityContext context = this.repository.loadContext(requestResponseHolder);
                if (context != null && context.getAuthentication() != null) {
                    String username = context.getAuthentication().getPrincipal().toString();
                    String[] roles = userService.getRoles(username);
                    context.getAuthentication().getAuthorities();
                    com.example.design.model.User user = userService.getByName(username).get(0);
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(username, user.getPassword(),
                                    convertStringArrayToAuthorities(roles));
                    context.setAuthentication(token);
                }
                return context;
            }

            @Override
            public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
                System.out.println(4);
                this.repository.saveContext(context, request, response);
            }

            @Override
            public boolean containsContext(HttpServletRequest request) {
                System.out.println(5);
                return this.repository.containsContext(request);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/info", "/error", "/company", "/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                System.out.println(2);
                String username = authentication.getName();
                String password = authentication.getCredentials().toString();
                if (userService.validateUser(username, password)) {
                    String[] roles = userService.getRoles(username);
                    return new UsernamePasswordAuthenticationToken(username, password, convertStringArrayToAuthorities(roles));
                }
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                System.out.println(6);

                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        });

        auth.userDetailsService(username -> {
            System.out.println(1);
            if (userService.hasUser(username)) {
                com.example.design.model.User user = userService.getByName(username).get(0);
                return new User(username, user.getPassword(),
                        convertStringArrayToAuthorities(userService.getRoles(username)));
            }
            return null;
        });
    }

    private Collection<? extends GrantedAuthority> convertStringArrayToAuthorities(String[] roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (String role : roles) {
            list.add(new SimpleGrantedAuthority(role));
        }
        return list;
    }
}

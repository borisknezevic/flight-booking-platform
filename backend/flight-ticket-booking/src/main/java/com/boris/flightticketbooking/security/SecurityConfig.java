package com.boris.flightticketbooking.security;

import com.boris.flightticketbooking.security.jwt.AccessTokenEntryPoint;
import com.boris.flightticketbooking.security.jwt.AccessTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityUserService userService;
    @Autowired
    private AccessTokenEntryPoint accessTokenEntryPoint;

    @Bean
    public AccessTokenFilter accessTokenFilter() {
        return new AccessTokenFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/flights").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/airlines").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/airlines/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/airports").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/airports/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/v1/tickets").hasAnyAuthority("USER")
                    .antMatchers(HttpMethod.GET,"/api/v1/tickets/**").hasAnyAuthority("USER")
                    .antMatchers(HttpMethod.POST,"/api/v1/tickets").hasAnyAuthority("USER")
                    .antMatchers(HttpMethod.POST,"/api/v1/tickets/**").hasAnyAuthority("USER")
                    .antMatchers(HttpMethod.POST,"/api/v1/flights").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST,"/api/v1/flights/**").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/v1/airlines").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/v1/airlines/**").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/v1/airports").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/v1/airports/**").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/api/v1/flights").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/airlines").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/airlines/**").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/airports").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/airports/**").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/api/v1/flights/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated();
        http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource()
//    {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}


//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private SecurityUserService userService;
//    @Autowired
//    private AccessTokenEntryPoint accessTokenEntryPoint;
//
//    @Bean
//    public AccessTokenFilter accessTokenFilter() {
//        return new AccessTokenFilter();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails user1 = User.withUsername("user1")
////                .password(passwordEncoder().encode("user1Pass"))
////                .roles("USER")
////                .build();
////        UserDetails user2 = User.withUsername("user2")
////                .password(passwordEncoder().encode("user2Pass"))
////                .roles("USER")
////                .build();
////        UserDetails admin = User.withUsername("admin")
////                .password(passwordEncoder().encode("adminPass"))
////                .roles("ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(user1, user2, admin);
////    }
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        // http builder configurations for authorize requests and form login (see below)
////        http.csrf()
////                .disable()
////                .authorizeRequests()
////                .antMatchers("/login").permitAll()
////                .antMatchers("/api/v1/airlines").permitAll()
////                .antMatchers("/api/v1/airports/**").permitAll()
////                .antMatchers("/api/v1/tickets/**").permitAll()
////                .antMatchers( "/api/v1/flights").permitAll()     //create new flight
////                .antMatchers(HttpMethod.DELETE, "/api/v1/flights").permitAll()    //delete flight
////                .antMatchers(HttpMethod.GET, "/api/v1/flights").permitAll()      //get flights
////                .antMatchers("/api/v1/users/**").hasAnyAuthority("ADMIN","USER")
////                .antMatchers("/api/v1/registration").permitAll()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .httpBasic()
////                .and()
////                .formLogin()
////                .and()
////                .logout()
////                .permitAll()
////                .deleteCookies("JSESSIONID");
////        return http.build();
////    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////

//
//
//}

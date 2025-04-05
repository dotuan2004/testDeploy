package com.example.demo.security;

import com.example.demo.filter.JwtFilter;
import com.example.demo.presentation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, EndPoints.PUBLIC_GET_ENDPOINS).permitAll()
                        .requestMatchers(HttpMethod.POST, EndPoints.PUBLIC_POST_ENDPOINS).permitAll()
                        .requestMatchers(HttpMethod.POST, EndPoints.ADMIN_POST_ENDPOINS).hasAuthority("ADMIN")
        );

        // Cấu hình CORS
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.addAllowedOrigin(EndPoints.front_end_host); // frontend domain
            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            corsConfig.addAllowedHeader("*");
            corsConfig.setAllowCredentials(true);  // Cho phép cookies và credentials
            return corsConfig;
        }));

        // Thêm filter JWT vào chuỗi bảo mật
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Tắt CSRF nếu dùng JWT
        http.csrf(csrf -> csrf.disable());

        // Cấu hình HTTP Basic nếu cần (ví dụ cho các API không sử dụng JWT)
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

//package com.cr1stal423.userservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ProjectSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());
//
//        httpSecurity.authorizeHttpRequests(requestConfiguration -> requestConfiguration
//                .requestMatchers("/users/getUserProfile/**","/users/getUserAddresses/**",
//                        "/users/updateUserAddress/**","/users/updateUserProfile/**",
//                        "/users/deleteUserAddress/**","/users/deleteUserProfile/**").authenticated()
//                .requestMatchers("/users/create").permitAll()
//                .requestMatchers("/users/**","/users/**/**/**","/users/**/**").hasRole("ADMIN")
//        );
//
//
//        httpSecurity.sessionManagement(smc -> smc
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        httpSecurity.oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer ->
//                jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
//
//        return httpSecurity.build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//}

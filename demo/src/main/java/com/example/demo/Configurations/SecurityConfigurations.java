package com.example.demo.Configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
public class SecurityConfigurations {




//    @Bean
//    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//        //.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
//        ; // or .httpBasic()
//
//        return httpSecurity.build();
//    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
////        AuthenticationManager manager =  configuration.getAuthenticationManager();
////        System.out.println(
////                "$$$$$$$$$$$$$$$$$$$$$$$AUTHENTICATION MANAGER = $$$$$$$$$$$$$$$$$$$$$$$$$$$$$4" +
////                        manager.getClass().getName()
////        );
////        return manager;
////    }
//
////    @Bean
////    public JwtAuthenticationFilter authFilter(){
////        return new JwtAuthenticationFilter();
////    }
//
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}

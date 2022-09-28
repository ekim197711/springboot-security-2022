package com.example.springbootsecurity2022.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurationBasicAuth {
    //No More WebSecurityConfigurerAdapter

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
                .username("mike")
                .password(encoder().encode("important"))
                .roles("CAPTAIN", "CREW")
                .build();
        UserDetails user2 = User.builder()
                .username("henrik")
                .password(encoder().encode("important"))
                .roles("CREW")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .cors().and()
                    .authorizeHttpRequests((authz) -> authz
                            .antMatchers("/navigation/**").hasRole("CAPTAIN")
                            .antMatchers("/cantina/**").hasRole("CREW")
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());
            return http.build();
    }
}

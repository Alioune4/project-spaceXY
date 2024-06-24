package fr.epita.discover.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(configurer -> configurer
                        .authenticationEntryPoint(new BasicAuthenticationEntryPoint()
                        {
                            {
                                setRealmName("MyApplication");
                            }
                        })
                )

                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/error").permitAll().requestMatchers("/api/technicians/shuttles/**","/api/technicians/revisions/**").hasRole("Technicien")
                        .requestMatchers("/api/planners/flights/**").hasRole("Planificateur")
                        .requestMatchers("/api/passengers/**").hasRole("Voyageur")
                        .anyRequest().authenticated())

                .build();


    }

    @Bean
    public UserDetailsService users(JdbcTemplate jdbcTemplate) {
        List<UserDetails> userDetailsList = jdbcTemplate.query("SELECT username, password, role FROM users",
                (rs, rowNum) -> {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String encodedPassword = passwordEncoder().encode(password);
                    String role = rs.getString("role");

                    jdbcTemplate.update("UPDATE users SET password = ? WHERE username = ?", encodedPassword, username);

                    return User.builder()
                            .username(username)
                            .password(encodedPassword)
                            .roles(role)
                            .build();
                });

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
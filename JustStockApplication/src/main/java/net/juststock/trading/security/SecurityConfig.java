package net.juststock.trading.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .cors(cors -> {}) // Enable CORS using bean below
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No sessions
                .authorizeHttpRequests(auth -> auth
                		 .requestMatchers(HttpMethod.POST, "/api/auth/send", "/api/auth/verify",
                                 "/api/otp/send",  "/api/otp/verify").permitAll()
                        .requestMatchers("/api/auth/**").permitAll() // Allow OTP endpoints
                        .requestMatchers("/api/sms/**").permitAll() // Allow SMS send endpoint alias
                        .requestMatchers("/sms/**").permitAll() // Allow Twilio SMS test endpoint
                        .requestMatchers("/api/v*/auth/**").permitAll()
                        .requestMatchers("/api/v*/otp/send").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/send").permitAll() // Explicit JSON route
                        .requestMatchers(HttpMethod.POST, "/api/otp/send").permitAll() // Explicit JSON route
                        .requestMatchers(HttpMethod.POST, "/api/otp/send/**").permitAll() // Explicit JSON route
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow CORS preflight
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll() // Allow user registration
                        
                        .anyRequest().authenticated() // Secure everything else
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable basic auth
                .formLogin(formLogin -> formLogin.disable()) // Disable login form
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Inject JWT filter
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

package backend.ecommerce.ecommerceapi.config.security;

import backend.ecommerce.ecommerceapi.config.security.jwt.JwtAuthenticationEntryPoint;
import backend.ecommerce.ecommerceapi.config.security.jwt.JwtAuthenticationFilter;
import backend.ecommerce.ecommerceapi.entity.role.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AuthenticationProvider authProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                                 JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                 AuthenticationProvider authProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)

                .authorizeHttpRequests(auth ->
                    auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/2fa/otp/**").permitAll()
                        .requestMatchers("/api/booking/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/user/get-user-roles/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/user/get-user/**").hasAuthority(ERole.ADMIN.name())
                        .requestMatchers("/api/user/get-user-bookings/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/user/set-user-role/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                    .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

package com.djblancoh.product;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests( auth ->{
                    auth.requestMatchers(HttpMethod.GET, "/api/products").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/products/session").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                    .successHandler(successHandler())
                .and()
                .sessionManagement()
                    //- ALWAYS: Crea una sesion siempre y cuando no exista niguna
                    // - IF_REQUIRED: Crea una nueva sesion solo si es necesario (es mas estricto a la hora de crear la sesion)
                    // - NEVER: Nunca crea una sesion ( si ya existe una sesion, la usara )
                    // - STATELESS: No crea sesion, trabaja sin session
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .invalidSessionUrl("/login")
                    .maximumSessions(1)
                    .expiredUrl("/login")
                    .sessionRegistry(sessionRegistry())
                .and()
                .sessionFixation()
                /***
                 * migrateSession(): Cuando se hace un ataque de migracion de sesion, se crea otro id de session
                 * newSession(): Crea una nueva session pero no copia los datos
                 * none(): desactiva la proteccion de fijacion de sesion
                 */
                    .migrateSession()
                .and()
                // Autenticacion basica
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            response.sendRedirect("/api/products/session");
        });
    }
}

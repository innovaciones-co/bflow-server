package co.innovaciones.bflow_server.config

import co.innovaciones.bflow_server.model.ErrorResponse
import co.innovaciones.bflow_server.service.JwtUserDetailsService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class JwtSecurityConfig(
    private val jwtRequestFilter: JwtRequestFilter,
    private val objectMapper: ObjectMapper,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUserDetailsService: JwtUserDetailsService
) {

    @Bean
    fun jwtAuthenticationManager(): AuthenticationManager {
        val jwtAuthenticationManager = DaoAuthenticationProvider(passwordEncoder)
        jwtAuthenticationManager.setUserDetailsService(jwtUserDetailsService)
        return ProviderManager(jwtAuthenticationManager)
    }

    @Bean
    @Throws(Exception::class)
    fun jwtFilterChain(http: HttpSecurity): SecurityFilterChain =
        http.cors(Customizer.withDefaults())
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authorize -> authorize
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/**").permitAll() //.authenticated()
                .anyRequest().permitAll()
            }
            .authenticationManager(jwtAuthenticationManager())
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { exception -> exception
                .authenticationEntryPoint { _, response, _ ->
                    val errorResponse = ErrorResponse()
                    errorResponse.exception = "Unauthorized"
                    errorResponse.httpStatus = HttpServletResponse.SC_UNAUTHORIZED
                    response.contentType = MediaType.APPLICATION_JSON_VALUE
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.outputStream.println(objectMapper.writeValueAsString(errorResponse))
                }}
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

}

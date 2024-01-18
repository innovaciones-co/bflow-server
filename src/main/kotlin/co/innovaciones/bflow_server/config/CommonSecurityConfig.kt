package co.innovaciones.bflow_server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AnonymousAuthenticationProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class CommonSecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // please note: existing hashes must contain {bcrypt} prefix
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    @Primary
    fun noopAuthenticationManager(): AuthenticationManager =
        ProviderManager(AnonymousAuthenticationProvider("noop"))

}
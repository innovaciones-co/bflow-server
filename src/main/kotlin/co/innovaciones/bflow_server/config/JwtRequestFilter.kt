package co.innovaciones.bflow_server.config

import co.innovaciones.bflow_server.model.JwtUserDetails
import co.innovaciones.bflow_server.service.JwtTokenService
import co.innovaciones.bflow_server.service.JwtUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


/**
 * Filter for authorizing requests based on the "Authorization: Bearer ..." header. When
 * a valid JWT has been found, load the user details from the database and set the
 * authenticated principal for the duration of this request.
 */
@Component
class JwtRequestFilter(
    private val jwtUserDetailsService: JwtUserDetailsService,
    private val jwtTokenService: JwtTokenService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        // look for Bearer auth header
        val header: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }

        val token: String = header.substring(7)
        val username: String? = jwtTokenService.validateTokenAndGetUsername(token)
        if (username == null) {
            // validation failed or token expired
            chain.doFilter(request, response)
            return
        }

        val userDetails: JwtUserDetails
        try {
            userDetails = jwtUserDetailsService.loadUserByUsername(username)
        } catch (userNotFoundEx: UsernameNotFoundException) {
            // user not found
            chain.doFilter(request, response)
            return
        }

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.authorities)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        // set user details on spring security context
        SecurityContextHolder.getContext().authentication = authentication

        // continue with authenticated user
        chain.doFilter(request, response)
    }

}
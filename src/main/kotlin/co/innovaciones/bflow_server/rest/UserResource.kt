package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.*
import co.innovaciones.bflow_server.service.JwtTokenService
import co.innovaciones.bflow_server.service.JwtUserDetailsService
import co.innovaciones.bflow_server.service.UserService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.OffsetDateTime
import java.util.*


@RestController
@RequestMapping(
    value = ["/api/users"], produces = [MediaType.APPLICATION_JSON_VALUE]
)
class UserResource(
    @Qualifier("jwtAuthenticationManager") private val jwtAuthenticationManager: AuthenticationManager,
    private val jwtUserDetailsService: JwtUserDetailsService,
    private val jwtTokenService: JwtTokenService,
    private val userService: UserService,
) {

    @SecurityRequirement(name = "bearer-jwt")
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> = ResponseEntity.ok(userService.findAll())

    @SecurityRequirement(name = "bearer-jwt")
    @GetMapping("/{id}")
    fun getUser(@PathVariable(name = "id") id: Long): ResponseEntity<UserDTO> = ResponseEntity.ok(userService.get(id))

    @SecurityRequirement(name = "bearer-jwt")
    @GetMapping("/username/{username}")
    fun getUser(@PathVariable(name = "username") username: String): ResponseEntity<UserDTO> =
        ResponseEntity.ok(userService.get(username))

    @SecurityRequirement(name = "bearer-jwt")
    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createUser(@RequestBody @Valid userDTO: UserDTO): ResponseEntity<Long> {
        val createdId = userService.create(userDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @SecurityRequirement(name = "bearer-jwt")
    @PutMapping("/{id}")
    fun updateUser(@PathVariable(name = "id") id: Long, @RequestBody @Valid userDTO: UserDTO): ResponseEntity<Long> {
        userService.update(id, userDTO)
        return ResponseEntity.ok(id)
    }

    @SecurityRequirement(name = "bearer-jwt")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteUser(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        userService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid authenticationRequestDTO: AuthenticationRequestDTO): AuthenticationResponseDTO {
        try {
            jwtAuthenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    authenticationRequestDTO.username, authenticationRequestDTO.password
                )
            )
        } catch (ex: BadCredentialsException) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        val userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequestDTO.username!!)
        val authenticationResponseDTO = AuthenticationResponseDTO()
        authenticationResponseDTO.accessToken = jwtTokenService.generateToken(userDetails)
        return authenticationResponseDTO
    }

    @PostMapping("/recover-password")
    fun recoverPassword(@RequestBody @Valid recoveryDTO: RecoveryDTO): ResponseEntity<Long> {
        val username = recoveryDTO.username!!
        val userDTO = userService.get(username)
        val token = UUID.randomUUID().toString()
        userDTO.recoveryToken = token
        userDTO.tokenExpirationDate = OffsetDateTime.now().plusMinutes(15)
        userService.update(userDTO.id!!, userDTO)
        userService.passwrodNotifyEmail(userDTO)
        return ResponseEntity.ok(userDTO.id!!)
    }

    @PostMapping("/create-new-password")
    fun newPassword(@RequestBody @Valid newPassDTO: NewPassDTO): ResponseEntity<Void> {
        val userDTO: UserDTO = userService.getByToken(newPassDTO.token!!)
        if (userDTO.tokenExpirationDate == null || userDTO.tokenExpirationDate!!.isBefore(OffsetDateTime.now())) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is Expired")
        }

        userDTO.password = newPassDTO.password
        userDTO.tokenExpirationDate = null
        userDTO.recoveryToken = null
        userService.update(userDTO.id!!, userDTO)
        return ResponseEntity.noContent().build()
    }

}

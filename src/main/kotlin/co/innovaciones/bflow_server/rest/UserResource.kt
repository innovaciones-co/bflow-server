package co.innovaciones.bflow_server.rest

import co.innovaciones.bflow_server.model.UserDTO
import co.innovaciones.bflow_server.service.UserService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class UserResource(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> = ResponseEntity.ok(userService.findAll())



    @GetMapping("/{id}")
    fun getUser(@PathVariable(name = "id") id: Long): ResponseEntity<UserDTO> =
            ResponseEntity.ok(userService.get(id))




    @PostMapping("")
    @ApiResponse(responseCode = "201")
    fun createUser(@RequestBody @Valid userDTO: UserDTO,bindingResult: BindingResult): ResponseEntity<Any> {

        if (bindingResult.hasErrors()) {
            // Manejo de errores de validación
            val errors = bindingResult.allErrors.map { it.defaultMessage }.joinToString()
            return ResponseEntity.badRequest().body(errors)
        }

        val createdId = userService.create(userDTO)

        return ResponseEntity(createdId, HttpStatus.CREATED)
    }



    @PutMapping("/{id}")
    fun updateUser(@PathVariable(name = "id") id: Long, @RequestBody @Valid userDTO: UserDTO):
            ResponseEntity<Long> {
        userService.update(id, userDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteUser(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        userService.delete(id)
        return ResponseEntity.noContent().build()
    }




}

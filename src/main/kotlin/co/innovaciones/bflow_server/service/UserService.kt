package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.User
import co.innovaciones.bflow_server.model.UserDTO
import co.innovaciones.bflow_server.repos.UserRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService,
) {
    @Value("\${sendinblue.template-password-change}")
    private val templatePasswordChange: Long? = null

    @Value("\${sendinblue.template-user-created}")
    private val templateUserCreated: Long? = null


    @Value("\${sendinblue.action-url}")
    private val clientUrl: String = "http://localhost"

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun findAll(): List<UserDTO> {
        val users = userRepository.findAll(Sort.by("id"))
        return users.stream()
            .map { user -> mapToDTO(user, UserDTO()) }
            .toList()
    }

    fun `get`(id: Long): UserDTO = userRepository.findById(id)
        .map { user -> mapToDTO(user, UserDTO()) }
        .orElseThrow { NotFoundException() }

    fun `get`(username: String): UserDTO = userRepository.findByUsernameIgnoreCase(username)
        .map { user -> mapToDTO(user, UserDTO()) }
        .orElseThrow { NotFoundException("The username is not registered") }

    fun getByToken(token: String): UserDTO = userRepository.findByRecoveryToken(token)
        .let { user ->
            if (user != null) mapToDTO(
                user,
                UserDTO()
            ) else throw (NotFoundException("The token is not longer valid"))
        }


    fun create(userDTO: UserDTO): Long {
        val user = User()
        mapToEntity(userDTO, user)
        return userRepository.save(user).id!!
    }

    fun update(id: Long, userDTO: UserDTO) {
        val user = userRepository.findById(id)
            .orElseThrow { NotFoundException() }
        mapToEntity(userDTO, user)
        userRepository.save(user)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    private fun mapToDTO(user: User, userDTO: UserDTO): UserDTO {
        userDTO.id = user.id
        userDTO.firstName = user.firstName
        userDTO.lastName = user.lastName
        userDTO.username = user.username
        userDTO.password = user.password
        userDTO.email = user.email
        userDTO.recoveryToken = user.recoveryToken
        userDTO.tokenExpirationDate = user.tokenExpirationDate
        userDTO.role = user.role
        return userDTO
    }

    private fun mapToEntity(userDTO: UserDTO, user: User): User {
        user.firstName = userDTO.firstName
        user.lastName = userDTO.lastName
        user.username = userDTO.username
        user.password =
            if (userDTO.password == user.password) user.password else passwordEncoder.encode(userDTO.password)
        user.email = userDTO.email
        user.recoveryToken = userDTO.recoveryToken
        user.tokenExpirationDate = userDTO.tokenExpirationDate
        user.role = userDTO.role
        return user
    }

    fun usernameExists(username: String?): Boolean =
        userRepository.existsByUsernameIgnoreCase(username)

    fun emailExists(email: String?): Boolean = userRepository.existsByEmailIgnoreCase(email)

    fun notifyPasswordChangeRequest(userDTO: UserDTO) {
        logger.info("Sending password notification for user {}...", userDTO.username)

        val toEmail = userDTO.email ?: return

        val params = mapOf(
            "token" to "${userDTO.recoveryToken}",
            "changePassURL" to "${clientUrl}/recover-pass?token=${userDTO.recoveryToken}"
        )

        val notificationFactory = NotificationFactory()

        val builder =
            notificationFactory.createNotificationBuilder("email", emailService) as EmailNotificationBuilder

        if (templatePasswordChange == null) {
            logger.error("Template was not configured")
            return
        }

        val notification =
            builder.withTemplate(templatePasswordChange).withParams(params).withSubject("Password update requested")
                .withRecipients(toEmail).build()
        notification.send()
    }

    fun notifyUserCreated(userDTO: UserDTO) {
        logger.info("Sending user notification for user {}...", userDTO.username)
        val toEmail = userDTO.email ?: return
        val params = mapOf(
            "username" to "${userDTO.username}",
            "name" to "${userDTO.firstName}",
            "email" to "${userDTO.firstName}"
        )
        val notificationFactory = NotificationFactory()
        val builder =
            notificationFactory.createNotificationBuilder("email", emailService) as EmailNotificationBuilder
        if (templateUserCreated == null) {
            logger.error("User created template was not configured")
            return
        }
        val notification = builder
            .withTemplate(templateUserCreated)
            .withParams(params)
            .withSubject("Your BFlow account has been created")
            .withRecipients(toEmail)
            .build()

        notification.send()
        logger.info("User notification sent to {}", toEmail)
    }
}

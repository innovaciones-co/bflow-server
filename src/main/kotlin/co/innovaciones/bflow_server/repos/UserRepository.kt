package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long> {

    fun existsByUsernameIgnoreCase(username: String?): Boolean

    fun existsByEmailIgnoreCase(email: String?): Boolean

}

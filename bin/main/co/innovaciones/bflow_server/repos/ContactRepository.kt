package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Contact
import org.springframework.data.jpa.repository.JpaRepository


interface ContactRepository : JpaRepository<Contact, Long> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

}

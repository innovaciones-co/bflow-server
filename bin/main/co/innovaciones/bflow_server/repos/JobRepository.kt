package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface JobRepository : JpaRepository<Job, Long> {

    fun findFirstByClient(contact: Contact): Job?

    fun findFirstByUser(user: User): Job?

    fun existsByJobNumberIgnoreCase(jobNumber: String?): Boolean

}

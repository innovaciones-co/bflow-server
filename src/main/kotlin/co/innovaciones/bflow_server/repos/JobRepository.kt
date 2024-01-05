package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Job
import org.springframework.data.jpa.repository.JpaRepository


interface JobRepository : JpaRepository<Job, Long> {

    fun existsByJobNumberIgnoreCase(jobNumber: String?): Boolean

}

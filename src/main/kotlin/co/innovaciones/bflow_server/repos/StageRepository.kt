package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Stage
import org.springframework.data.jpa.repository.JpaRepository


interface StageRepository : JpaRepository<Stage, Long> {

    fun existsByNameIgnoreCase(name: String?): Boolean

}

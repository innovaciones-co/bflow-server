package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Template
import org.springframework.data.jpa.repository.JpaRepository


interface TemplateRepository : JpaRepository<Template, Long> {

    fun existsByNameIgnoreCase(name: String?): Boolean

}

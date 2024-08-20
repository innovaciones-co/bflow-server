package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Template
import co.innovaciones.bflow_server.model.TemplateType
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository


interface TemplateRepository : JpaRepository<Template, Long> {

    fun findByType(type: TemplateType, sort: Sort) : List<Template>

    fun existsByNameIgnoreCase(name: String?): Boolean

}

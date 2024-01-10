package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.File
import org.springframework.data.jpa.repository.JpaRepository


interface FileRepository : JpaRepository<File, Long> {

    fun existsByUriIgnoreCase(uri: String?): Boolean

}

package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Category
import org.springframework.data.jpa.repository.JpaRepository


interface CategoryRepository : JpaRepository<Category, Long>

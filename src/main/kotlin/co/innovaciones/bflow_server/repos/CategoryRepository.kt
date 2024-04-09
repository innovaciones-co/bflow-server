package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Category
import co.innovaciones.bflow_server.domain.Contact
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository


interface CategoryRepository : JpaRepository<Category, Long> {

    fun findAllByIdIn(ids: List<Long>, sort: Sort?): List<Category>

    fun findFirstByContact(contact: Contact): Category?

    fun findFirstByParentCategoryAndIdNot(category: Category, id: Long?): Category?

    fun existsByContactId(id: Long?): Boolean

    fun existsByParentCategoryId(id: Long?): Boolean

}

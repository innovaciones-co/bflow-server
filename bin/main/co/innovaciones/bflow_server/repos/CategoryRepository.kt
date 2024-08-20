package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Category
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository


interface CategoryRepository : JpaRepository<Category, Long> {

    fun findAllByIdIn(ids: List<Long>, sort: Sort?): List<Category>

    fun findFirstByParentCategoryAndIdNot(category: Category, id: Long?): Category?

    fun existsByParentCategoryId(id: Long?): Boolean

    fun existsByTradeCode(tradeCode: Int?): Boolean

    fun existsByNameIgnoreCase(name: String?): Boolean
}

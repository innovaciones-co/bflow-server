package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.OrderItem
import org.springframework.data.jpa.repository.JpaRepository


interface OrderItemRepository : JpaRepository<OrderItem, Long>

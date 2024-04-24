package co.innovaciones.bflow_server.controller


import co.innovaciones.bflow_server.model.OrderStatus
import co.innovaciones.bflow_server.service.CategoryService
import co.innovaciones.bflow_server.service.JobService
import co.innovaciones.bflow_server.service.PurchaseOrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/orders")
class PurchaseOrderController(
    private val purchaseOrderService: PurchaseOrderService,
    private val jobService: JobService,
    private val categoryService: CategoryService,
) {
    @ModelAttribute
    fun prepareContext(model: Model) {
        model.addAttribute("statusValues", OrderStatus.entries.toTypedArray())
    }

    @GetMapping("/{id}")
    fun view(@PathVariable id : Long, model: Model): String {
        val purchaseOrder = purchaseOrderService.get(id)
        val job = jobService.get(purchaseOrder.job!!)
        val categoriesIds = purchaseOrder.orderItems!!.map { item -> item.category!! }.toList()
        val categories = categoryService.findAllById( categoriesIds )
        val total = purchaseOrder.orderItems!!.map { item -> if(item.price != null) item.price!! else 0.0 }.toList().sum()
        model.addAttribute("order", purchaseOrder)
        model.addAttribute("job", job)
        model.addAttribute("categories", categories)
        model.addAttribute("total", total)

        return "purchaseOrder/view"
    }
}
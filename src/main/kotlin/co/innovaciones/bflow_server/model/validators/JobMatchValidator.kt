package co.innovaciones.bflow_server.model.validators

import co.innovaciones.bflow_server.model.TaskWriteDTO
import co.innovaciones.bflow_server.service.PurchaseOrderService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class JobMatchValidator @Autowired constructor(
    private val purchaseOrderService: PurchaseOrderService
) : ConstraintValidator<JobMatch, TaskWriteDTO> {

    override fun isValid(taskWriteDTO: TaskWriteDTO, context: ConstraintValidatorContext): Boolean {
        val purchaseOrderId = taskWriteDTO.purchaseOrder ?: return true
        val purchaseOrder = purchaseOrderService.get(purchaseOrderId)
        return purchaseOrder.job == taskWriteDTO.job
    }
}

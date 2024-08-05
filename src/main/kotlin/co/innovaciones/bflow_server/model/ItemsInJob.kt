package co.innovaciones.bflow_server.model


import co.innovaciones.bflow_server.service.ItemService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import org.springframework.web.servlet.HandlerMapping


/**
 * Validate that the email value isn't taken yet.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ItemsInJobValidator::class])
annotation class ItemsInJob(
    val message: String = "Initial items must not have a purchase order and item.job must match order.job",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class ItemsInJobValidator(
    private val itemService: ItemService
) : ConstraintValidator<ItemsInJob, CreatePurchaseOrderDTO> {

    override fun isValid(createPurchaseOrderDTO: CreatePurchaseOrderDTO?, cvContext: ConstraintValidatorContext): Boolean {
        return createPurchaseOrderDTO?.items?.all {
            val item = itemService.get(it.id!!)
            item.purchaseOrder == null && item.job == createPurchaseOrderDTO.job
        } ?: true
    }
}

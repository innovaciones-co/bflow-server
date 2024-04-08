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
 * Validate that the id value isn't taken yet.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ItemSupplierUniqueValidator::class])
annotation class ItemSupplierUnique(
    val message: String = "{Exists.item.supplier}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class ItemSupplierUniqueValidator(
    private val itemService: ItemService,
    private val request: HttpServletRequest
) : ConstraintValidator<ItemSupplierUnique, Long> {

    override fun isValid(`value`: Long?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
                (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value == itemService.get(currentId.toLong()).supplier) {
            // value hasn't changed
            return true
        }
        return !itemService.supplierExists(value)
    }

}

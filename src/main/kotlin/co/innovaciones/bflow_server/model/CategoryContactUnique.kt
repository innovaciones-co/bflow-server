package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.service.CategoryService
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
@Constraint(validatedBy = [CategoryContactUniqueValidator::class])
annotation class CategoryContactUnique(
    val message: String = "{Exists.category.contact}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class CategoryContactUniqueValidator(
    private val categoryService: CategoryService,
    private val request: HttpServletRequest
) : ConstraintValidator<CategoryContactUnique, Long> {

    override fun isValid(`value`: Long?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
                (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value == categoryService.get(currentId.toLong()).contact) {
            // value hasn't changed
            return true
        }
        return !categoryService.contactExists(value)
    }

}

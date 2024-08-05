package co.innovaciones.bflow_server.model.validators

import co.innovaciones.bflow_server.service.CategoryService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
import org.springframework.web.servlet.HandlerMapping


/**
 * Validate that the tradeCode value isn't taken yet.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [CategoryTradeCodeUniqueValidator::class])
annotation class CategoryTradeCodeUnique(
    val message: String = "{Exists.category.tradeCode}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class CategoryTradeCodeUniqueValidator(
    private val categoryService: CategoryService,
    private val request: HttpServletRequest
) : ConstraintValidator<CategoryTradeCodeUnique, Int> {

    override fun isValid(`value`: Int?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
            (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                    Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value == categoryService.get(currentId.toLong()).tradeCode) {
            // value hasn't changed
            return true
        }
        return !categoryService.tradeCodeExists(value)
    }

}
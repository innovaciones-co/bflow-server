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
 * Validate that the name value isn't taken yet.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [CategoryNameUniqueValidator::class])
annotation class CategoryNameUnique(
    val message: String = "{Exists.category.name}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class CategoryNameUniqueValidator(
    private val categoryService: CategoryService,
    private val request: HttpServletRequest
) : ConstraintValidator<CategoryNameUnique, String> {

    override fun isValid(`value`: String?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
            (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                    Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value.equals(categoryService.get(currentId.toLong()).name,
                ignoreCase = true)) {
            // value hasn't changed
            return true
        }
        return !categoryService.nameExists(value)
    }

}

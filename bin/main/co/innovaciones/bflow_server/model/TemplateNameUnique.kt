package co.innovaciones.bflow_server.model

import co.innovaciones.bflow_server.service.TemplateService
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
@Constraint(validatedBy = [TemplateNameUniqueValidator::class])
annotation class TemplateNameUnique(
    val message: String = "{Exists.template.name}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


class TemplateNameUniqueValidator(
    private val templateService: TemplateService,
    private val request: HttpServletRequest
) : ConstraintValidator<TemplateNameUnique, String> {

    override fun isValid(`value`: String?, cvContext: ConstraintValidatorContext): Boolean {
        if (value == null) {
            // no value present
            return true
        }
        @Suppress("unchecked_cast") val pathVariables =
                (request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as
                Map<String, String>)
        val currentId = pathVariables.get("id")
        if (currentId != null && value.equals(templateService.get(currentId.toLong()).name,
                ignoreCase = true)) {
            // value hasn't changed
            return true
        }
        return !templateService.nameExists(value)
    }

}

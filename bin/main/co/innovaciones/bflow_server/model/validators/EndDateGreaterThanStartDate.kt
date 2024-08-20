package co.innovaciones.bflow_server.model.validators

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.CLASS)//AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EndDateGreaterThanStartDateValidator::class])
@MustBeDocumented
annotation class EndDateGreaterThanStartDate(
    val message: String ="Start Date should be sooner than End Date",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)



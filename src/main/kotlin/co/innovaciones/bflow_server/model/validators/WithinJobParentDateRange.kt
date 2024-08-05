package co.innovaciones.bflow_server.model.validators

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [WithinJobParentDateRangeValidator::class])
annotation class WithinJobParentDateRange(
    val message: String = "Must be between parent job dates",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

package co.innovaciones.bflow_server.model.validators

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [WithinParentDateRangeValidator::class])
annotation class WithinParentDateRange(
    val message: String = "must be between parent task dates",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
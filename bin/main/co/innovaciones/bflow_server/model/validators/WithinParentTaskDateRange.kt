package co.innovaciones.bflow_server.model.validators

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [WithinParentTaskDateRangeValidator::class])
annotation class WithinParentTaskDateRange(
    val message: String = "Parent task date updated",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

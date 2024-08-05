package co.innovaciones.bflow_server.model.validators


import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [JobMatchValidator::class])
annotation class JobMatch(
    val message: String = "Job ID of the purchase order must match the job of the task",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

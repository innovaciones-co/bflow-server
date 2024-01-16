package co.innovaciones.bflow_server.model.validators


import co.innovaciones.bflow_server.service.JobService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UniqueJobNumberValidator(private val jobService: JobService) : ConstraintValidator<UniqueJobNumber, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        // Implement logic to check if jobNumber exists
        return value?.let { !jobService.jobNumberExists(it) } ?: true
    }
}

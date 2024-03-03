package co.innovaciones.bflow_server.model.validators

import co.innovaciones.bflow_server.model.JobDTO
import co.innovaciones.bflow_server.model.TaskDTO
import co.innovaciones.bflow_server.service.JobService
import co.innovaciones.bflow_server.service.TaskService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class WithinJobParentDateRangeValidator(
    @Autowired private val jobService: JobService
) : ConstraintValidator<WithinJobParentDateRange, TaskDTO> {
    override fun isValid(taskDTO: TaskDTO?, context: ConstraintValidatorContext?): Boolean {
        val parentJobId = taskDTO?.job

        if (parentJobId != null) {
            val jobToCheck = jobService.get(parentJobId)
            val startDateIsValid = taskDTO.startDate!!.isAfter(jobToCheck.plannedStartDate)
            val endDateIsValid =
                taskDTO.endDate!!.isBefore(jobToCheck.plannedEndDate) || taskDTO.endDate!!.isEqual(jobToCheck.plannedEndDate)

            return startDateIsValid && endDateIsValid
        }

        return true
    }
}
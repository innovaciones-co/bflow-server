package co.innovaciones.bflow_server.model.validators

import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.TaskDTO
import co.innovaciones.bflow_server.service.TaskService
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.ConstraintValidator

class EndDateGreaterThanStartDateValidator : ConstraintValidator<EndDateGreaterThanStartDate, TaskDTO> {
    override fun isValid(task:TaskDTO, context:ConstraintValidatorContext):Boolean{
        return if (task.startDate != null) task.startDate!!.isBefore(task.endDate) else true
    }

}
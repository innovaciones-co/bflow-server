package co.innovaciones.bflow_server.model.validators

import co.innovaciones.bflow_server.model.TaskDTO
import co.innovaciones.bflow_server.service.TaskService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class WithinParentTaskDateRangeValidator(
    @Autowired private val taskService: TaskService
) : ConstraintValidator<WithinParentTaskDateRange, TaskDTO> {
    override fun isValid(taskDTO: TaskDTO?, context: ConstraintValidatorContext?): Boolean {
        val parentTaskId = taskDTO?.parentTask

        if (parentTaskId != null) {
            val parentTask = taskService.get(parentTaskId)
            val startDateIsValid = taskDTO.startDate!!.isAfter(parentTask.startDate)
            val endDateIsValid =
                taskDTO.endDate!!.isBefore(parentTask.endDate) || taskDTO.endDate!!.isEqual(parentTask.endDate)

            if (startDateIsValid == false) {
                parentTask.startDate = taskDTO.startDate
                //taskService.update(parentTaskId, parentTask)
            }
            if (endDateIsValid == false) {
                parentTask.endDate = taskDTO.endDate
                //taskService.update(parentTaskId, parentTask)
            }

            return startDateIsValid && endDateIsValid
        }

        return true
    }
}
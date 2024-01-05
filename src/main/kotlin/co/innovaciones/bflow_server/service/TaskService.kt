package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.TaskDTO
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.StageRepository
import co.innovaciones.bflow_server.repos.TaskRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val stageRepository: StageRepository,
    private val contactRepository: ContactRepository
) {

    fun findAll(): List<TaskDTO> {
        val tasks = taskRepository.findAll(Sort.by("id"))
        return tasks.stream()
                .map { task -> mapToDTO(task, TaskDTO()) }
                .toList()
    }

    fun `get`(id: Long): TaskDTO = taskRepository.findById(id)
            .map { task -> mapToDTO(task, TaskDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(taskDTO: TaskDTO): Long {
        val task = Task()
        mapToEntity(taskDTO, task)
        return taskRepository.save(task).id!!
    }

    fun update(id: Long, taskDTO: TaskDTO) {
        val task = taskRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(taskDTO, task)
        taskRepository.save(task)
    }

    fun delete(id: Long) {
        taskRepository.deleteById(id)
    }

    private fun mapToDTO(task: Task, taskDTO: TaskDTO): TaskDTO {
        taskDTO.id = task.id
        taskDTO.name = task.name
        taskDTO.startDate = task.startDate
        taskDTO.endDate = task.endDate
        taskDTO.progress = task.progress
        taskDTO.status = task.status
        taskDTO.activity = task.activity?.id
        taskDTO.parentTask = task.parentTask?.id
        taskDTO.supplier = task.supplier?.id
        return taskDTO
    }

    private fun mapToEntity(taskDTO: TaskDTO, task: Task): Task {
        task.name = taskDTO.name
        task.startDate = taskDTO.startDate
        task.endDate = taskDTO.endDate
        task.progress = taskDTO.progress
        task.status = taskDTO.status
        val activity = if (taskDTO.activity == null) null else
                stageRepository.findById(taskDTO.activity!!)
                .orElseThrow { NotFoundException("activity not found") }
        task.activity = activity
        val parentTask = if (taskDTO.parentTask == null) null else
                taskRepository.findById(taskDTO.parentTask!!)
                .orElseThrow { NotFoundException("parentTask not found") }
        task.parentTask = parentTask
        val supplier = if (taskDTO.supplier == null) null else
                contactRepository.findById(taskDTO.supplier!!)
                .orElseThrow { NotFoundException("supplier not found") }
        task.supplier = supplier
        return task
    }

}

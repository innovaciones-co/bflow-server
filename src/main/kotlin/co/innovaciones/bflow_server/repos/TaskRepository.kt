package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.domain.File
import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.TaskStage
import co.innovaciones.bflow_server.model.TaskStatus
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate


interface TaskRepository : JpaRepository<Task, Long> {

    fun findFirstByParentTaskAndIdNot(task: Task, id: Long?): Task?

    fun findFirstBySupplier(contact: Contact): Task?

    fun findAllByAttachments(`file`: File): List<Task>?

    fun findAllByJob(job: Job, sort: Sort?): List<Task>

    fun findByIdIn(ids: List<Long>): List<Task>

    fun findAllByJobIdAndStage(jobId: Long, stage: TaskStage): List<Task>

    fun findAllByJobIdAndStageAndOrderGreaterThan(jobId: Long, stage: TaskStage, order: Int): List<Task>

    fun findAllByJobAndStatusNotIn(job: Job?, listOf: List<TaskStatus>, by: Sort): List<Task>

    fun findAllByJobAndEndDateBeforeAndStatusNotIn(job: Job, endDate: LocalDate, status: List<TaskStatus>, sort: Sort): List<Task>

}

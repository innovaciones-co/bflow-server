package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.domain.File
import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.Task
import co.innovaciones.bflow_server.model.TaskStage
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository


interface TaskRepository : JpaRepository<Task, Long> {

    fun findFirstByParentTaskAndIdNot(task: Task, id: Long?): Task?

    fun findFirstBySupplier(contact: Contact): Task?

    fun findFirstByAttachments(attachments: MutableSet<File>): Task?

    fun findFirstByJob(job: Job): Task?

    fun findAllByAttachments(attachments: MutableSet<File>): List<Task>

    fun findAllByJob(job: Job, sort: Sort?): List<Task>

    fun findByIdIn(ids: List<Long>): List<Task>

    fun findAllByJobIdAndStage(jobId: Long, stage: TaskStage): List<Task>

    fun findAllByJobIdAndStageAndOrderGreaterThan(jobId: Long, stage: TaskStage, order: Int): List<Task>

}

package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.File
import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.Task
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository


interface TaskRepository : JpaRepository<Task, Long> {

    fun findAllByAttachments(taskFile: File) : List<Task>
    fun findAllByJob(job: Job, sort: Sort?): List<Task>

    fun findByIdIn(ids: List<Long>): List<Task>
}

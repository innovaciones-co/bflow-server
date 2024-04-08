package co.innovaciones.bflow_server.repos

import co.innovaciones.bflow_server.domain.Job
import co.innovaciones.bflow_server.domain.Note
import org.springframework.data.jpa.repository.JpaRepository


interface NoteRepository : JpaRepository<Note, Long> {

    fun findFirstByJob(job: Job): Note?

}

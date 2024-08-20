package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Note
import co.innovaciones.bflow_server.model.NoteDTO
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.NoteRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val jobRepository: JobRepository
) {

    fun findAll(): List<NoteDTO> {
        val notes = noteRepository.findAll(Sort.by("id"))
        return notes.stream()
                .map { note -> mapToDTO(note, NoteDTO()) }
                .toList()
    }

    fun `get`(id: Long): NoteDTO = noteRepository.findById(id)
            .map { note -> mapToDTO(note, NoteDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(noteDTO: NoteDTO): Long {
        val note = Note()
        mapToEntity(noteDTO, note)
        return noteRepository.save(note).id!!
    }

    fun update(id: Long, noteDTO: NoteDTO) {
        val note = noteRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(noteDTO, note)
        noteRepository.save(note)
    }

    fun delete(id: Long) {
        noteRepository.deleteById(id)
    }

    private fun mapToDTO(note: Note, noteDTO: NoteDTO): NoteDTO {
        noteDTO.id = note.id
        noteDTO.body = note.body
        noteDTO.job = note.job?.id
        return noteDTO
    }

    private fun mapToEntity(noteDTO: NoteDTO, note: Note): Note {
        note.body = noteDTO.body
        val job = if (noteDTO.job == null) null else jobRepository.findById(noteDTO.job!!)
                .orElseThrow { NotFoundException("job not found") }
        note.job = job
        return note
    }

}

package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Stage
import co.innovaciones.bflow_server.model.StageDTO
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.StageRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class StageService(
    private val stageRepository: StageRepository,
    private val jobRepository: JobRepository
) {

    fun findAll(): List<StageDTO> {
        val stages = stageRepository.findAll(Sort.by("id"))
        return stages.stream()
                .map { stage -> mapToDTO(stage, StageDTO()) }
                .toList()
    }

    fun `get`(id: Long): StageDTO = stageRepository.findById(id)
            .map { stage -> mapToDTO(stage, StageDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(stageDTO: StageDTO): Long {
        val stage = Stage()
        mapToEntity(stageDTO, stage)
        return stageRepository.save(stage).id!!
    }

    fun update(id: Long, stageDTO: StageDTO) {
        val stage = stageRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(stageDTO, stage)
        stageRepository.save(stage)
    }

    fun delete(id: Long) {
        stageRepository.deleteById(id)
    }

    private fun mapToDTO(stage: Stage, stageDTO: StageDTO): StageDTO {
        stageDTO.id = stage.id
        stageDTO.name = stage.name
        stageDTO.job = stage.job?.id
        return stageDTO
    }

    private fun mapToEntity(stageDTO: StageDTO, stage: Stage): Stage {
        stage.name = stageDTO.name
        val job = if (stageDTO.job == null) null else jobRepository.findById(stageDTO.job!!)
                .orElseThrow { NotFoundException("job not found") }
        stage.job = job
        return stage
    }

    fun nameExists(name: String?): Boolean = stageRepository.existsByNameIgnoreCase(name)

}

package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Template
import co.innovaciones.bflow_server.model.TemplateDTO
import co.innovaciones.bflow_server.repos.TemplateRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class TemplateService(
    private val templateRepository: TemplateRepository
) {

    fun findAll(): List<TemplateDTO> {
        val templates = templateRepository.findAll(Sort.by("id"))
        return templates.stream()
                .map { template -> mapToDTO(template, TemplateDTO()) }
                .toList()
    }

    fun `get`(id: Long): TemplateDTO = templateRepository.findById(id)
            .map { template -> mapToDTO(template, TemplateDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(templateDTO: TemplateDTO): Long {
        val template = Template()
        mapToEntity(templateDTO, template)
        return templateRepository.save(template).id!!
    }

    fun update(id: Long, templateDTO: TemplateDTO) {
        val template = templateRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(templateDTO, template)
        templateRepository.save(template)
    }

    fun delete(id: Long) {
        templateRepository.deleteById(id)
    }

    private fun mapToDTO(template: Template, templateDTO: TemplateDTO): TemplateDTO {
        templateDTO.id = template.id
        templateDTO.name = template.name
        templateDTO.template = template.template
        return templateDTO
    }

    private fun mapToEntity(templateDTO: TemplateDTO, template: Template): Template {
        template.name = templateDTO.name
        template.template = templateDTO.template
        return template
    }

    fun nameExists(name: String?): Boolean = templateRepository.existsByNameIgnoreCase(name)

}

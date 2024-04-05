package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.model.ContactDTO
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.util.NotFoundException
import org.apache.coyote.BadRequestException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ContactService(
    private val contactRepository: ContactRepository
) {

    fun findAll(): List<ContactDTO> {
        val contacts = contactRepository.findAll(Sort.by("id"))
        return contacts.stream()
                .map { contact -> mapToDTO(contact, ContactDTO()) }
                .toList()
    }

    fun `get`(id: Long): ContactDTO = contactRepository.findById(id)
            .map { contact -> mapToDTO(contact, ContactDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(contactDTO: ContactDTO): Long {
        val contact = Contact()
        mapToEntity(contactDTO, contact)
        return contactRepository.save(contact).id!!
    }

    fun update(id: Long, contactDTO: ContactDTO) {
        val contact = contactRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(contactDTO, contact)
        contactRepository.save(contact)
    }

    fun delete(id: Long) {
        contactRepository.deleteById(id)
    }

    private fun mapToDTO(contact: Contact, contactDTO: ContactDTO): ContactDTO {
        contactDTO.id = contact.id
        contactDTO.name = contact.name
        contactDTO.address = contact.address
        contactDTO.email = contact.email
        contactDTO.type = contact.type
        return contactDTO
    }

    private fun mapToEntity(contactDTO: ContactDTO, contact: Contact): Contact {
        contact.name = contactDTO.name
        contact.address = contactDTO.address
        contact.email = contactDTO.email
        contact.type = contactDTO.type
        return contact
    }

    fun emailExists(email: String?): Boolean = contactRepository.existsByEmailIgnoreCase(email)

}

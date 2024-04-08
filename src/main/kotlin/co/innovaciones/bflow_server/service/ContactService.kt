package co.innovaciones.bflow_server.service

import co.innovaciones.bflow_server.domain.Contact
import co.innovaciones.bflow_server.model.ContactDTO
import co.innovaciones.bflow_server.repos.CategoryRepository
import co.innovaciones.bflow_server.repos.ContactRepository
import co.innovaciones.bflow_server.repos.ItemRepository
import co.innovaciones.bflow_server.repos.JobRepository
import co.innovaciones.bflow_server.repos.TaskRepository
import co.innovaciones.bflow_server.util.NotFoundException
import co.innovaciones.bflow_server.util.ReferencedWarning
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ContactService(
    private val contactRepository: ContactRepository,
    private val jobRepository: JobRepository,
    private val categoryRepository: CategoryRepository,
    private val taskRepository: TaskRepository,
    private val itemRepository: ItemRepository
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
        contactDTO.idNumber = contact.idNumber
        contactDTO.address = contact.address
        contactDTO.phone = contact.phone
        contactDTO.email = contact.email
        contactDTO.type = contact.type
        contactDTO.accountNumber = contact.accountNumber
        contactDTO.accountHolderName = contact.accountHolderName
        contactDTO.bankName = contact.bankName
        contactDTO.taxNumber = contact.taxNumber
        contactDTO.details = contact.details
        return contactDTO
    }

    private fun mapToEntity(contactDTO: ContactDTO, contact: Contact): Contact {
        contact.name = contactDTO.name
        contact.idNumber = contactDTO.idNumber
        contact.address = contactDTO.address
        contact.phone = contactDTO.phone
        contact.email = contactDTO.email
        contact.type = contactDTO.type
        contact.accountNumber = contactDTO.accountNumber
        contact.accountHolderName = contactDTO.accountHolderName
        contact.bankName = contactDTO.bankName
        contact.taxNumber = contactDTO.taxNumber
        contact.details = contactDTO.details
        return contact
    }

    fun emailExists(email: String?): Boolean = contactRepository.existsByEmailIgnoreCase(email)

    fun getReferencedWarning(id: Long): ReferencedWarning? {
        val referencedWarning = ReferencedWarning()
        val contact = contactRepository.findById(id)
                .orElseThrow { NotFoundException() }
        val clientJob = jobRepository.findFirstByClient(contact)
        if (clientJob != null) {
            referencedWarning.key = "contact.job.client.referenced"
            referencedWarning.addParam(clientJob.id)
            return referencedWarning
        }
        val contactCategory = categoryRepository.findFirstByContact(contact)
        if (contactCategory != null) {
            referencedWarning.key = "contact.category.contact.referenced"
            referencedWarning.addParam(contactCategory.id)
            return referencedWarning
        }
        val supplierTask = taskRepository.findFirstBySupplier(contact)
        if (supplierTask != null) {
            referencedWarning.key = "contact.task.supplier.referenced"
            referencedWarning.addParam(supplierTask.id)
            return referencedWarning
        }
        val supplierItem = itemRepository.findFirstBySupplier(contact)
        if (supplierItem != null) {
            referencedWarning.key = "contact.item.supplier.referenced"
            referencedWarning.addParam(supplierItem.id)
            return referencedWarning
        }
        return null
    }

}

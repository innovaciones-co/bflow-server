package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.ContactType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Contacts")
@EntityListeners(AuditingEntityListener::class)
class Contact {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "contact_primary_sequence",
        sequenceName = "contact_primary_sequence",
        allocationSize = 1,
        initialValue = 10100
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "contact_primary_sequence"
    )
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column
    var idNumber: String? = null

    @Column(nullable = false)
    var address: String? = null

    @Column(nullable = false)
    var phone: String? = null

    @Column(
        nullable = false,
        unique = true
    )
    var email: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: ContactType? = null

    @Column
    var accountNumber: String? = null

    @Column
    var accountHolderName: String? = null

    @Column
    var bankName: String? = null

    @Column
    var taxNumber: String? = null

    @Column(columnDefinition = "text")
    var details: String? = null

    @CreatedDate
    @Column(
        nullable = false,
        updatable = false
    )
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    @Column(nullable = false)
    var lastUpdated: OffsetDateTime? = null

}

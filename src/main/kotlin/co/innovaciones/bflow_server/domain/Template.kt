package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.TemplateType
import jakarta.persistence.*
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Templates")
@EntityListeners(AuditingEntityListener::class)
class Template {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "template_primary_sequence",
        sequenceName = "template_primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "template_primary_sequence"
    )
    var id: Long? = null

    @Column(
        nullable = false,
        unique = false
    )
    @Enumerated(EnumType.STRING)
    var type: TemplateType? = null

    @Column(
        nullable = false,
        unique = true
    )
    var name: String? = null

    @Column(
        nullable = false,
        columnDefinition = "text"
    )
    var template: String? = null

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

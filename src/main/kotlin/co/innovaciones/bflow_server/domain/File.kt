package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.FileCategory
import co.innovaciones.bflow_server.model.FileTag
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Files")
@EntityListeners(AuditingEntityListener::class)
class File {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "file_primary_sequence",
        sequenceName = "file_primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "file_primary_sequence"
    )
    var id: Long? = null

    @Column(
        nullable = false,
        unique = true
    )
    var uuid: String = ""

    @Column(nullable = false)
    var bucket: String = ""

    @Column(nullable = false)
    var name: String? = null

    @Column
    var type: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var category: FileCategory? = null

    @Column
    @Enumerated(EnumType.STRING)
    var tag: FileTag? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    var job: Job? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    var task: Task? = null

    @CreatedDate
    @Column(
        nullable = false,
    )
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    @Column(nullable = false)
    var lastUpdated: OffsetDateTime? = null

}

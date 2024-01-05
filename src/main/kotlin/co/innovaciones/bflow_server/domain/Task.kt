package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.TaskStatus
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
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Tasks")
@EntityListeners(AuditingEntityListener::class)
class Task {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var startDate: LocalDate? = null

    @Column
    var endDate: LocalDate? = null

    @Column(nullable = false)
    var progress: Double? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: TaskStatus? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    var activity: Stage? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "parent_task_id",
        unique = true
    )
    var parentTask: Task? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    var supplier: Contact? = null

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

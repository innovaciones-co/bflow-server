package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.TaskStatus
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.OffsetDateTime


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
        name = "task_primary_sequence",
        sequenceName = "task_primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "task_primary_sequence"
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "parent_task_id",
        unique = false
    )
    var parentTask: Task? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    var supplier: Contact? = null

    @ManyToMany
    @JoinTable(
        name = "TaskAttachments",
        joinColumns = [
            JoinColumn(name = "taskId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "fileId")
        ]
    )
    var attachments: MutableSet<File>? = null

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

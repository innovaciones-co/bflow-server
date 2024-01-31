package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.TaskStage
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
        nullable = false, updatable = false
    )
    @SequenceGenerator(
        name = "task_primary_sequence", sequenceName = "task_primary_sequence", allocationSize = 1, initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, generator = "task_primary_sequence"
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
        set(value) {
            if (value != null && value !in 0.0..100.0) {
                throw IllegalArgumentException("Progress must be between 0 and 100")
            }

            if (value != field) {
                field = value
                updateStatus()
            }
        }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: TaskStatus? = null
        set(value) {
            if (field != value) {
                field = value
                updateCallDate(value)
            }
        }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var stage: TaskStage? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "parent_task_id", unique = false
    )
    var parentTask: Task? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    var supplier: Contact? = null

    @ManyToMany
    @JoinTable(
        name = "task_attachments",
        joinColumns = [JoinColumn(name = "task_id")],
        inverseJoinColumns = [JoinColumn(name = "file_id")]
    )
    var attachments: MutableSet<File>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "job_id", nullable = false
    )
    var job: Job? = null

    @Column
    var description: String? = null

    @CreatedDate
    @Column
    var callDate: OffsetDateTime? = null

    @CreatedDate
    @Column(
        nullable = false, updatable = false
    )
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    @Column(nullable = false)
    var lastUpdated: OffsetDateTime? = null

    private fun updateStatus() {
        status = when {
            progress == 0.0 -> TaskStatus.CREATED
            progress == 100.0 -> TaskStatus.COMPLETED
            progress!! < 100.0 -> TaskStatus.IN_PROGRESS
            else -> status
        }
    }

    private fun updateCallDate(value: TaskStatus?) {
        callDate = when {
            value == TaskStatus.SENT -> OffsetDateTime.now()
            else -> callDate
        }
    }

}

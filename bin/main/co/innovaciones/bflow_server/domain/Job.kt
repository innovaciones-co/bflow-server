package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.BuildingType
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
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Jobs")
@EntityListeners(AuditingEntityListener::class)
class Job {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "job_primary_sequence",
        sequenceName = "job_primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "job_primary_sequence"
    )
    var id: Long? = null

    @Column(
        nullable = false,
        unique = true
    )
    var jobNumber: String? = null

    @Column
    var name: String? = null

    @Column
    var plannedStartDate: LocalDate? = null

    @Column
    var plannedEndDate: LocalDate? = null

    @Column(nullable = false)
    var address: String? = null

    @Column(columnDefinition = "text")
    var description: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var buildingType: BuildingType? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "client_id",
        nullable = false
    )
    var client: Contact? = null

    @OneToMany(mappedBy = "job")
    var purchaseOrders: MutableSet<PurchaseOrder>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "supervisor_id",
        nullable = false
    )
    var supervisor: User? = null

    @OneToMany(mappedBy = "job")
    var files: MutableSet<File>? = null

    @OneToMany(mappedBy = "job")
    var notes: MutableSet<Note>? = null

    @OneToMany(mappedBy = "job")
    var tasks: MutableSet<Task>? = null

    @OneToMany(mappedBy = "job")
    var materials: MutableSet<Item>? = null

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

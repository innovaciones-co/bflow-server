package co.innovaciones.bflow_server.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
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
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
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

    @Column
    var contract: String? = null

    @Column(columnDefinition = "text")
    var description: String? = null

    @Column(nullable = false)
    var buildingType: String? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "client_id",
        nullable = false,
        unique = true
    )
    var client: Contact? = null

    @OneToMany(mappedBy = "job")
    var activities: MutableSet<Stage>? = null

    @OneToMany(mappedBy = "job")
    var purchaseOrders: MutableSet<PurchaseOrder>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    var user: User? = null

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

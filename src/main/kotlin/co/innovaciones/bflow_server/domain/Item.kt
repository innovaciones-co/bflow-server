package co.innovaciones.bflow_server.domain

import co.innovaciones.bflow_server.model.Units
import jakarta.persistence.*
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Items")
@EntityListeners(AuditingEntityListener::class)
class Item {

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

    @Column(columnDefinition = "text")
    var description: String? = null

    @Column(nullable = false)
    var unitPrice: Double = 0.0

    @Column(columnDefinition = "double precision default 0")
    var vat: Double = 0.0

    @Column(columnDefinition = "double precision default 0")
    var price: Double = 0.0
        get() {
            return unitPrice * units * (1 + vat)
        }

    @Column(nullable = false)
    var units: Long = 0

    @Column
    @Enumerated(EnumType.STRING)
    var measure: Units? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    var purchaseOrder: PurchaseOrder? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "supplier_id",
        nullable = false,
    )
    var supplier: Contact? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_id",
        nullable = false
    )
    var category: Category? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    var job: Job? = null

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

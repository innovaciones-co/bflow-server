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
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
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
    var unitPrice: Double? = null

    @Column
    var vat: Double? = null

    @Column
    var price: Double? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    var purchaseOrder: PurchaseOrder? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "supplier_id",
        nullable = false,
        unique = true
    )
    var supplier: Contact? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_id",
        nullable = false,
        unique = true
    )
    var category: Category? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
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

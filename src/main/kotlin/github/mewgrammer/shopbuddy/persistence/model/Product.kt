package github.mewgrammer.shopbuddy.persistence.model

import github.mewgrammer.shopbuddy.persistence.audit.AuditableEntity
import javax.persistence.*


@Entity
@Table(
    name = "product",
    indexes = [
        Index(columnList = "name", unique = true, name = "idx_product_name")
    ]
)
class Product(
    @Column
    var name: String,

    @Column
    var description: String? = null,

    @Column
    var price: Double,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    var shoppingListItems: MutableList<ShoppingListItem>? = null,

    @ManyToMany
    @JoinTable(
        name = "product_category_jt",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: MutableList<ProductCategory>? = null

) : AuditableEntity()
package github.mewgrammer.shopbuddy.persistence.model

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
    var description: String,

    @Column
    var price: Double,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    val shoppingListItems: MutableList<ShoppingListItem>,

    @ManyToMany
    @JoinTable(
        name = "product_category_jt",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<ProductCategory> = emptyList()

) : BaseEntity()
package github.mewgrammer.shopbuddy.persistence.model

import javax.persistence.*


@Entity
@Table(name = "product_category", indexes = [
    Index(columnList = "name", unique = true, name = "idx_product_category_name")
])
class ProductCategory(
    @Column
    var name: String,

    @ManyToMany(mappedBy = "categories")
    val products: MutableList<Product>? = null
) : BaseEntity()
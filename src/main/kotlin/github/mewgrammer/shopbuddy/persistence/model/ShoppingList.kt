package github.mewgrammer.shopbuddy.persistence.model

import github.mewgrammer.shopbuddy.persistence.audit.AuditableEntity
import javax.persistence.*


@Entity
@Table(
    name = "shopping_list",
    indexes = [
        Index(columnList = "name", unique = true, name = "idx_shopping_list_name")
    ]
)
class ShoppingList(
    @Column
    var name: String,

    @Column
    var description: String? = null,

    @OneToMany(mappedBy = "shoppingList", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    val items: MutableList<ShoppingListItem> = mutableListOf()
) : AuditableEntity()
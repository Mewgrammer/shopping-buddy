package github.mewgrammer.shopbuddy.persistence.model

import github.mewgrammer.shopbuddy.persistence.audit.AuditableEntity
import javax.persistence.*

@Entity
@Table(name = "shopping_list_item")
class ShoppingListItem(
    @Column
    var quantity: Int,

    @ManyToOne
    @JoinColumn(name = "productId")
    var product: Product? = null,

    @ManyToOne
    @JoinColumn(name = "shoppingListId")
    var shoppingList: ShoppingList? = null
) : AuditableEntity()
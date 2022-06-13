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
    val product: Product?,

    @ManyToOne
    @JoinColumn(name = "shoppingListId")
    val shoppingList: ShoppingList
) : AuditableEntity()
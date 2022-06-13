package github.mewgrammer.shopbuddy.persistence.repository

import github.mewgrammer.shopbuddy.persistence.model.ShoppingListItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ShoppingListItemRepository : JpaRepository<ShoppingListItem, UUID> {
}
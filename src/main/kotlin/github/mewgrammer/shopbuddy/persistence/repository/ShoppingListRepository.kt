package github.mewgrammer.shopbuddy.persistence.repository

import github.mewgrammer.shopbuddy.persistence.model.ShoppingList
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ShoppingListRepository : JpaRepository<ShoppingList, UUID> {
}
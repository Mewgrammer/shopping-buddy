package github.mewgrammer.shopbuddy.api.model

import java.time.Instant
import java.util.UUID

data class ShoppingListDto(
    val id: UUID,
    val name: String,
    val totalValue: Long,
    val items: List<ShoppingListItemDto>?,
    val createdAt: Instant?,
)
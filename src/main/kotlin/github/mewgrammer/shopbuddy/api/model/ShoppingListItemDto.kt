package github.mewgrammer.shopbuddy.api.model

import java.time.Instant
import java.util.UUID

data class ShoppingListItemDto(
    val id: UUID,
    val quantity: Int,
    val product: ProductDto,
    val createdAt: Instant?,
)
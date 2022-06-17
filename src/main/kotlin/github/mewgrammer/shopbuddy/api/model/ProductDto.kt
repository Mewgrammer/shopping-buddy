package github.mewgrammer.shopbuddy.api.model

import java.time.Instant
import java.util.UUID

data class ProductDto(
    val id: UUID,
    val name: String,
    val price: Double,
    val categories: List<String>?,
    val createdAt: Instant?,
)
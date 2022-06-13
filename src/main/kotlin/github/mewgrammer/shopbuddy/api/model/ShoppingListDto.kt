package github.mewgrammer.shopbuddy.api.model

data class ShoppingListDto(
    val name: String,
    val totalValue: Long,
    val items: List<ShoppingListItemDto>
)
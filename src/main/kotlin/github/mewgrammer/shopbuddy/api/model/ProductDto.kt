package github.mewgrammer.shopbuddy.api.model

data class ProductDto(
    val name: String,
    val price: Double,
    val categories: List<String>
)
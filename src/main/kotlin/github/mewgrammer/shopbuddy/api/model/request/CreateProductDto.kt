package github.mewgrammer.shopbuddy.api.model.request

data class CreateProductDto(
    val name: String,
    val description: String,
    val price: Double,
    val categories: List<String>
)
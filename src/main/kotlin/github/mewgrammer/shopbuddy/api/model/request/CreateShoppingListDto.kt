package github.mewgrammer.shopbuddy.api.model.request

import javax.validation.constraints.NotEmpty

data class CreateShoppingListDto(
    @NotEmpty
    val name: String,
    val description: String,
    val items: List<CreateShoppingListItemDto>? = null)
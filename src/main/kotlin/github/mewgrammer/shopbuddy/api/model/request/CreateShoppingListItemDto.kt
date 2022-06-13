package github.mewgrammer.shopbuddy.api.model.request

import java.util.UUID
import javax.validation.constraints.Positive

data class CreateShoppingListItemDto(
    @Positive
    val quantity: Int,
    val productId: UUID
)
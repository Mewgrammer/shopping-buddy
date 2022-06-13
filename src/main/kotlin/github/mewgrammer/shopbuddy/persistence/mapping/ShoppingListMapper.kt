package github.mewgrammer.shopbuddy.persistence.mapping

import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListDto
import github.mewgrammer.shopbuddy.persistence.model.ShoppingList
import github.mewgrammer.shopbuddy.persistence.model.ShoppingListItem
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(uses = [ShoppingListItemMapper::class])
interface ShoppingListMapper {
    @Mapping(source = "items", target = "totalValue", qualifiedByName = ["totalValue"])
    fun toDto(shoppingList: ShoppingList): ShoppingListDto
    fun toEntity(dto: CreateShoppingListDto): ShoppingList

    companion object {
        @JvmStatic
        @Named("totalValue")
        fun calculateTotalValue(items: List<ShoppingListItem>?): Double {
            return items?.sumOf { it.quantity * (it.product?.price ?: 0.0) } ?: 0.0
        }
    }
}
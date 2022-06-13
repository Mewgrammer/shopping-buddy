package github.mewgrammer.shopbuddy.persistence.mapping

import github.mewgrammer.shopbuddy.api.model.ShoppingListItemDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListItemDto
import github.mewgrammer.shopbuddy.persistence.model.ShoppingListItem
import github.mewgrammer.shopbuddy.service.ProductService
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.beans.factory.annotation.Autowired

@Mapper(uses = [ProductMapper::class, ProductResolver::class])
interface ShoppingListItemMapper {
    fun toDto(shoppingList: ShoppingListItem): ShoppingListItemDto

    @Mapping(source = "productId", target = "product", qualifiedByName = ["productResolver", "productById"])
    fun toEntity(dto: CreateShoppingListItemDto): ShoppingListItem

}
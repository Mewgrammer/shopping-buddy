package github.mewgrammer.shopbuddy.api.graphql

import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.ShoppingListItemDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListItemDto
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListItemMapper
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListMapper
import github.mewgrammer.shopbuddy.service.ShoppingListService
import org.springframework.data.web.ProjectedPayload
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class ItemGqlController(
    private val service: ShoppingListService,
    private val itemMapper: ShoppingListItemMapper,
    private val listMapper: ShoppingListMapper
) {
    @QueryMapping(name = "itemsOfList")
    fun getAll(@Argument listId: UUID): List<ShoppingListItemDto> {
        return service.getAllItems(listId).map { itemMapper.toDto(it) }
    }

    @MutationMapping(name = "addItem")
    fun addToList(projection: ShoppingListItemProjection): ShoppingListDto {
        val updatedList = service.addItem(
            projection.listId,
            itemMapper.toEntity(CreateShoppingListItemDto(projection.quantity, projection.productId))
        )
        return listMapper.toDto(updatedList)
    }

    @MutationMapping(name = "removeItem")
    fun removeItem(@Argument listId: UUID, @Argument itemId: UUID): Boolean {
        val updatedList = service.removeItems(listId, itemId)
        return true
    }
}

@ProjectedPayload
interface ShoppingListItemProjection {
    val listId: UUID
    val quantity: Int
    val productId: UUID
}
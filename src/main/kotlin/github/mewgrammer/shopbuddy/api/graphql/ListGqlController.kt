package github.mewgrammer.shopbuddy.api.graphql

import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListDto
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListMapper
import github.mewgrammer.shopbuddy.service.ShoppingListService
import org.springframework.data.web.ProjectedPayload
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class ListGqlController(
    private val service: ShoppingListService,
    private val mapper: ShoppingListMapper
) {
    @QueryMapping(name = "lists")
    fun getAll(): List<ShoppingListDto> {
        return service.getAllLists().map { mapper.toDto(it) }
    }

    @QueryMapping(name = "listById")
    fun getById(@Argument id: UUID): ShoppingListDto {
        return mapper.toDto(service.getListById(id))
    }

    @MutationMapping(name = "createList")
    fun create(projection: ShoppingListProjection): ShoppingListDto {
        val created = service.createList(mapper.toEntity(CreateShoppingListDto(projection.name, projection.description)))
        return mapper.toDto(created)
    }

    @MutationMapping(name = "deleteList")
    fun deleteById(@Argument id: UUID) {
        service.deleteListById(id)
    }
}

@ProjectedPayload
interface ShoppingListProjection {
    val name: String
    val description: String
}
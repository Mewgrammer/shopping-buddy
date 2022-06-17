package github.mewgrammer.shopbuddy.api.controller

import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.ShoppingListItemDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListItemDto
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListItemMapper
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListMapper
import github.mewgrammer.shopbuddy.service.ShoppingListService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/list/{listId}/item")
class ItemController(
    private val service: ShoppingListService,
    private val itemMapper: ShoppingListItemMapper,
    private val listMapper: ShoppingListMapper
) {
    @GetMapping
    fun getAll(@PathVariable listId: UUID): List<ShoppingListItemDto> {
        return service.getAllItems(listId).map { itemMapper.toDto(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addToList(@PathVariable listId: UUID, @RequestBody @Valid dto: CreateShoppingListItemDto): ShoppingListDto {
        val updatedList = service.addItem(listId, itemMapper.toEntity(dto))
        return listMapper.toDto(updatedList)
    }

    @DeleteMapping("{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable listId: UUID, @PathVariable itemId: UUID) {
        service.removeItems(listId, itemId)
    }
}
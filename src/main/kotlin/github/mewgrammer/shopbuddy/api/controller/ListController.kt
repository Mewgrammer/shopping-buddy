package github.mewgrammer.shopbuddy.api.controller

import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListDto
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListMapper
import github.mewgrammer.shopbuddy.service.ShoppingListService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("api/list")
class ListController(
    private val service: ShoppingListService,
    private val mapper: ShoppingListMapper
) {
    @GetMapping
    fun getAll(): List<ShoppingListDto> {
        return service.getAllLists().map { mapper.toDto(it) }
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: UUID): ShoppingListDto {
        return mapper.toDto(service.getListById(id))
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: CreateShoppingListDto): ShoppingListDto {
        val created = service.createList(mapper.toEntity(dto))
        return mapper.toDto(created)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: UUID) {
        service.deleteListById(id)
    }

}
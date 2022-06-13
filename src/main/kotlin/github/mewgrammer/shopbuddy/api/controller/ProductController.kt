package github.mewgrammer.shopbuddy.api.controller

import github.mewgrammer.shopbuddy.api.model.ProductDto
import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.request.CreateProductDto
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListDto
import github.mewgrammer.shopbuddy.persistence.mapping.ProductMapper
import github.mewgrammer.shopbuddy.persistence.mapping.ShoppingListMapper
import github.mewgrammer.shopbuddy.service.ProductService
import github.mewgrammer.shopbuddy.service.ShoppingListService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/api/product")
class ProductController(
    private val service: ProductService,
    private val mapper: ProductMapper
) {
    @GetMapping
    fun getAll(): List<ProductDto> {
        return service.getAll().map { mapper.toDto(it) }
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: UUID): ProductDto {
        return mapper.toDto(service.getById(id))
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: CreateProductDto): ProductDto {
        val created = service.create(mapper.toEntity(dto))
        return mapper.toDto(created)
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: UUID) {
        service.deleteById(id)
    }

}
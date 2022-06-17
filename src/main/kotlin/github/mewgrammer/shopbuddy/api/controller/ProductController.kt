package github.mewgrammer.shopbuddy.api.controller

import github.mewgrammer.shopbuddy.api.model.ProductDto
import github.mewgrammer.shopbuddy.api.model.request.CreateProductDto
import github.mewgrammer.shopbuddy.persistence.mapping.ProductMapper
import github.mewgrammer.shopbuddy.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/product")
class ProductController(
    private val service: ProductService,
    private val mapper: ProductMapper
) {
    @GetMapping
    fun getAll(@RequestParam category: String?): List<ProductDto> {
        val results = if (category == null) service.getAll() else service.getByCategory(category);
        return results.map { mapper.toDto(it) }
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: UUID): ProductDto {
        return mapper.toDto(service.getById(id))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid dto: CreateProductDto): ProductDto {
        val created = service.create(mapper.toEntity(dto))
        return mapper.toDto(created)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: UUID) {
        service.deleteById(id)
    }

}
package github.mewgrammer.shopbuddy.api.graphql

import github.mewgrammer.shopbuddy.api.model.ProductDto
import github.mewgrammer.shopbuddy.api.model.request.CreateProductDto
import github.mewgrammer.shopbuddy.persistence.mapping.ProductMapper
import github.mewgrammer.shopbuddy.service.ProductService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*
import javax.validation.Valid

@Controller
class ProductGqlController(
    private val service: ProductService,
    private val mapper: ProductMapper
) {
    @QueryMapping(name = "products")
    fun getAll(): List<ProductDto> {
        return service.getAll().map { mapper.toDto(it) }
    }

    @QueryMapping(name = "productsByCategory")
    fun getByCategory(@Argument category: String): List<ProductDto> {
        return service.getByCategory(category).map { mapper.toDto(it) }
    }

    @QueryMapping(name = "productById")
    fun getById(@PathVariable id: UUID): ProductDto {
        return mapper.toDto(service.getById(id))
    }

    @MutationMapping(name = "createProduct")
    fun create(@RequestBody @Valid dto: CreateProductDto): ProductDto {
        val created = service.create(mapper.toEntity(dto))
        return mapper.toDto(created)
    }

    @MutationMapping(name = "deleteProduct")
    fun deleteById(@PathVariable id: UUID) {
        service.deleteById(id)
    }

}
package github.mewgrammer.shopbuddy.persistence.mapping

import github.mewgrammer.shopbuddy.persistence.model.Product
import github.mewgrammer.shopbuddy.persistence.model.ProductCategory
import github.mewgrammer.shopbuddy.service.ProductCategoryService
import github.mewgrammer.shopbuddy.service.ProductService
import org.mapstruct.Named
import org.springframework.stereotype.Component
import java.util.*

@Component
@Named("productResolver")
class ProductResolver(
    private val productService: ProductService,
    private val categoryService: ProductCategoryService
) {

    @Named("productById")
    fun getById(id: UUID): Product {
        return productService.getById(id)
    }

    @Named("categoryByName")
    fun getById(name: String): ProductCategory {
        return categoryService.getByName(name)
    }

    @Named("categoriesByName")
    fun getById(categoryNames: List<String>): List<ProductCategory> {
        return categoryService.getByNames(*categoryNames.toTypedArray())
    }
}
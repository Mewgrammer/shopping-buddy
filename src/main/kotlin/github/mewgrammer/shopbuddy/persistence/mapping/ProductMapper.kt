package github.mewgrammer.shopbuddy.persistence.mapping

import github.mewgrammer.shopbuddy.api.model.ProductDto
import github.mewgrammer.shopbuddy.api.model.ShoppingListDto
import github.mewgrammer.shopbuddy.api.model.request.CreateProductDto
import github.mewgrammer.shopbuddy.persistence.model.Product
import github.mewgrammer.shopbuddy.persistence.model.ProductCategory
import github.mewgrammer.shopbuddy.persistence.model.ShoppingList
import github.mewgrammer.shopbuddy.persistence.model.ShoppingListItem
import github.mewgrammer.shopbuddy.service.ProductCategoryService
import github.mewgrammer.shopbuddy.service.ProductService
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.beans.factory.annotation.Autowired

@Mapper(uses = [ProductResolver::class])
interface ProductMapper {
    @Mapping(source = "categories", target = "categories", qualifiedByName = ["productCategoryNames"])
    fun toDto(product: Product): ProductDto

    @Mapping(source = "categories", target = "categories", qualifiedByName = ["productResolver", "categoryByName"])
    fun toEntity(dto: CreateProductDto): Product

    companion object {
        @JvmStatic
        @Named("productCategoryNames")
        fun getProductCategoryNames(categories: List<ProductCategory>?): List<String> {
            return categories?.map { it.name } ?: emptyList()
        }
    }
}
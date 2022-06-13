package github.mewgrammer.shopbuddy.service

import github.mewgrammer.shopbuddy.api.model.ProductDto
import github.mewgrammer.shopbuddy.persistence.model.Product
import github.mewgrammer.shopbuddy.persistence.repository.ProductCategoryRepository
import github.mewgrammer.shopbuddy.persistence.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    private val repository: ProductRepository,
    private val categoryRepository: ProductCategoryRepository
) {
    fun getById(id: UUID) = repository.findById(id).orElseThrow()

    fun getAll() = repository.findAll()

    fun create(product: Product): Product = repository.save(product)

    fun deleteById(id: UUID) = repository.deleteById(id)
    fun getByCategory(name: String): List<Product> {
        return categoryRepository.findByName(name)?.products ?: throw NoSuchElementException()
    }

}
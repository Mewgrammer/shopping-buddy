package github.mewgrammer.shopbuddy.service

import github.mewgrammer.shopbuddy.persistence.model.ProductCategory
import github.mewgrammer.shopbuddy.persistence.repository.ProductCategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductCategoryService(
    private val repository: ProductCategoryRepository
) {
    fun getById(id: UUID) = repository.findById(id).orElseThrow()

    fun getByName(name: String) = repository.findByName(name) ?: throw NoSuchElementException()

    fun getByNames(vararg names: String) = repository.findByNameIn(names.asList())

    fun getAll() = repository.findAll()

    fun create(product: ProductCategory): ProductCategory = repository.save(product)

    fun deleteById(id: UUID) = repository.deleteById(id)

}
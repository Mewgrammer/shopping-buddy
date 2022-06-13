package github.mewgrammer.shopbuddy.persistence.repository

import github.mewgrammer.shopbuddy.persistence.model.ProductCategory
import github.mewgrammer.shopbuddy.persistence.model.ShoppingList
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductCategoryRepository : JpaRepository<ProductCategory, UUID> {
    fun findByName(name: String): ProductCategory?

    fun findByNameIn(names: List<String>): List<ProductCategory>
}
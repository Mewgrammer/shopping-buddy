package github.mewgrammer.shopbuddy.helper

import github.mewgrammer.shopbuddy.persistence.model.Product
import github.mewgrammer.shopbuddy.persistence.model.ProductCategory
import github.mewgrammer.shopbuddy.persistence.model.ShoppingList
import github.mewgrammer.shopbuddy.persistence.model.ShoppingListItem

class Builder {
    companion object {
        inline fun shoppingList(builder: ShoppingList.() -> Unit): ShoppingList {
            val shoppingList = ShoppingList("generated", "generated")
            shoppingList.builder()
            return shoppingList
        }

        inline fun shoppingListItem(builder: ShoppingListItem.() -> Unit): ShoppingListItem {
            val item = ShoppingListItem(1)
            item.builder()
            return item
        }

        inline fun product(builder: Product.() -> Unit): Product {
            val product = Product("generated", "generated", 0.0)
            product.builder()
            return product
        }

        inline fun category(builder: ProductCategory.() -> Unit): ProductCategory {
            val shoppingList = ProductCategory("generated")
            shoppingList.builder()
            return shoppingList
        }
    }
}
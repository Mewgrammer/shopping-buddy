package github.mewgrammer.shopbuddy.service

import github.mewgrammer.shopbuddy.persistence.model.ShoppingList
import github.mewgrammer.shopbuddy.persistence.model.ShoppingListItem
import github.mewgrammer.shopbuddy.persistence.repository.ShoppingListItemRepository
import github.mewgrammer.shopbuddy.persistence.repository.ShoppingListRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ShoppingListService(
    private val listRepository: ShoppingListRepository,
    private val itemRepository: ShoppingListItemRepository
) {
    fun getListById(id: UUID) = listRepository.findById(id).orElseThrow()

    fun getAllLists() = listRepository.findAll()

    fun createList(shoppingList: ShoppingList): ShoppingList = listRepository.save(shoppingList)

    fun deleteListById(id: UUID) = listRepository.deleteById(id)

    fun getAllItems(listId: UUID) = getListById(listId).items

    fun getItemById(id: UUID) = itemRepository.findById(id).orElseThrow()

    fun addItem(listId: UUID, item: ShoppingListItem): ShoppingList {
        val list = getListById(listId)
        item.shoppingList = list
        val createdItem = itemRepository.save(item)
        list.items.add(createdItem)
        return listRepository.save(list)
    }

    fun removeItems(listId: UUID, vararg itemIds: UUID): ShoppingList {
        val list = getListById(listId)
        list.items.removeAll { itemIds.contains(it.id) }
        return listRepository.save(list)
    }

    fun deleteItemById(id: UUID) = itemRepository.deleteById(id)

}
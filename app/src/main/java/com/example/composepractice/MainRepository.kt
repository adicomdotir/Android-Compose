package com.example.composepractice

class MainRepository {
    private val items = mutableListOf<Item>()
    private var itemIndex = 0

    init {
        items.add(Item("Item 1", false))
        items.add(Item("Item 2", false))
        items.add(Item("Item 3", false))
        itemIndex = 3
    }

    fun getItem(): MutableList<Item> = items.toMutableList()

    fun addItem() {
        itemIndex += 1
        items.add(Item("Item $itemIndex", false))
    }

    fun deleteItem(idx: Int) {
        items.removeAt(idx)
    }

    fun checkedItem(value: Boolean, index: Int) {
        items[index] = items[index].copy(checked = value)
    }
}

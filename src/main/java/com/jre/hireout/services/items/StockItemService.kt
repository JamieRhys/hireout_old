package com.jre.hireout.services.items

import com.jre.hireout.database.entities.items.StockItem

interface StockItemService {
    fun saveStockItem(stockItem: StockItem): StockItem

    fun getStockItem(name: String): StockItem

    fun getStockItems(): List<StockItem>
}
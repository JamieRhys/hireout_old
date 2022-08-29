package com.jre.hireout.database.repository.items

import com.jre.hireout.database.entities.items.StockItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockItemRepository : JpaRepository<StockItem, Long> {
    fun findStockItemByName(name: String): StockItem
}
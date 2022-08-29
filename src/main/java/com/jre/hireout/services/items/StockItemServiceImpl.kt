package com.jre.hireout.services.items

import com.jre.hireout.database.entities.items.StockItem
import com.jre.hireout.database.repository.items.StockItemRepository
import com.jre.hireout.utils.log.logger
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
open class StockItemServiceImpl(
    private val stockItemRepository: StockItemRepository
) : StockItemService {
    private val log = logger<StockItemServiceImpl>()

    override fun saveStockItem(stockItem: StockItem): StockItem {
        log.info("Saving Stock Item ${stockItem.name} to database.")
        return stockItemRepository.save(stockItem)
    }

    override fun getStockItem(name: String): StockItem {
        log.info("Fetching Stock Item $name from database.")
        return stockItemRepository.findStockItemByName(name)
    }

    override fun getStockItems(): List<StockItem> {
        log.info("Fetching all Stock Items from database.")
        return stockItemRepository.findAll()
    }
}
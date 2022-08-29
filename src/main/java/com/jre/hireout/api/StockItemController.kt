package com.jre.hireout.api

import com.jre.hireout.database.entities.items.StockItem
import com.jre.hireout.services.items.StockItemService
import com.jre.hireout.utils.log.logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api")
class StockItemController(
    private val stockItemService: StockItemService
) {
    private val log = logger<StockItemController>()

    @GetMapping("/stock-items")
    fun getStockItems(): ResponseEntity<List<StockItem>> {
        return ResponseEntity.ok().body(stockItemService.getStockItems())
    }

    @PostMapping("/stock-item/save")
    fun saveStockItem(@RequestBody stockItem: StockItem): ResponseEntity<StockItem> {

        val uri: URI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/stock-item/save").toUriString())
        return ResponseEntity.created(uri).body(stockItemService.saveStockItem(stockItem))
    }
}
package com.jre.hireout.database.entities.items

import javax.persistence.*

@Entity
@Table(name = "table_stock_items")
data class StockItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="stock_item_id")
    val id: Long? = null,

    @Column(name = "stock_item_name")
    var name: String = "New Item",

    @Column(name = "stock_item_alt_name")
    var alternativeName: String = "",

    @Column(name = "stock_item_part_number")
    var partNumber: String = "",

    @Column(name = "stock_item_description")
    var description: String = "",

    @Column(name = "stock_item_note")
    var note: String = "",

    @Column(name = "stock_item_replacement_cost")
    var replacementCost: Double = 0.00,

    @Column(name = "stock_item_country_of_origin")
    var countryOfOrigin: String = "",

    @Column(name = "stock_item_location")
    var location: String = "",

    @Column(name = "stock_item_dimensions")
    var dimensions: ItemDimensions = ItemDimensions(),

    @Column(name = "stock_item_pricing")
    var pricing: ItemPricing = ItemPricing(),

    @Column(name = "stock_item_price")
    var itemPrice: ItemPrice = ItemPrice.PRICE_A
) {
    @Embeddable
    data class ItemDimensions(
        var weight: Float = 0.0f,
        var length: Int = 0,
        var width: Int = 0,
        var height: Int = 0
    )

    @Embeddable
    data class ItemPricing(
        var priceA: Double = 0.0,
        var priceB: Double = 0.0,
        var priceC: Double = 0.0,
    )

    @Embeddable
    enum class ItemPrice(name: String) {
        PRICE_A("Price A"),
        PRICE_B("Price B"),
        PRICE_C("Price C"),
    }
}

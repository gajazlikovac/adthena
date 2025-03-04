package pricebasket

case class Item(name: String, price: BigDecimal) {
  require(price >= 0, "Price must be a positive number.")
}

object AvailableItems {
    private val items: Map[String, Item] = Map(
        "Soup" -> Item("Soup", 0.65),
        "Bread" -> Item("Bread", 0.80),
        "Milk" -> Item("Milk", 1.30),
        "Apples" -> Item("Apples", 1.00)
    )

    def getItem(name: String): Option[Item] = items.get(name)
}
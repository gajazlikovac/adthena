package pricebasket

object PriceBasket {
  
  def calculateTotal(items: List[String]): Unit = {

    if (items.isEmpty) {
      println("Error: No items provided. Usage: PriceBasket item1 item2 ...")
      return
    }

    val invalidItems = items.filter(item => AvailableItems.getItem(item).isEmpty)
    if (invalidItems.nonEmpty) {
      println(s"Error: Invalid items found - ${invalidItems.mkString(", ")}")
      return
    }

    val basketItems = items.flatMap(name => AvailableItems.getItem(name).toList)

    val subtotal = basketItems.map(_.price).sum
    val discounts = Offer.calculateDiscounts(basketItems)
    val total = subtotal - (discounts)._2

    println(f"Subtotal: £$subtotal%.2f")
    discounts._1.foreach(discount => println(discount))
    println(f"Total price: £$total%.2f")
  }

  def main(args: Array[String]): Unit = calculateTotal(args.toList)
}


package pricebasket

object Offer {
    // Discount function type
    type DiscountFunction = List[Item] => (String, BigDecimal)

    private val appleDiscount: DiscountFunction = items => {
        val itemCount = items.groupBy(_.name).map { case (k, v) => (k, v.size) }
        val discountItemPrice = AvailableItems.getItem("Apples").map(_.price).getOrElse(BigDecimal(0))
        val totalDiscount = (itemCount.getOrElse("Apples", 0) * discountItemPrice) * BigDecimal("0.10")

        if (totalDiscount > 0) {
            (s"Apples 10% off: ${formatDiscount(totalDiscount)}", totalDiscount)
        } else {
            ("", 0)
        }
    }

    private val breadAndSoupDiscount: DiscountFunction = items => {
        val itemCount = items.groupBy(_.name).map { case (k, v) => (k, v.size) }
        val soupCount = itemCount.getOrElse("Soup", 0)
        val breadCount = itemCount.getOrElse("Bread", 0)
        val eligibleBreadDiscounts = (soupCount / 2)
        val discountItemPrice = AvailableItems.getItem("Bread").map(_.price).getOrElse(BigDecimal(0))
        val totalDiscount = (eligibleBreadDiscounts.min(breadCount) *discountItemPrice) * BigDecimal("0.50")
        
        if (totalDiscount > 0) {
            (s"Bread 50% off (per 2 Soups): ${formatDiscount(totalDiscount)}", totalDiscount)
        } else {
            ("", 0)
        }
    }

    // List of discount functions
    private val discountFunctions: List[DiscountFunction] = List(
        appleDiscount,
        breadAndSoupDiscount
        //To create new discount functions, add them here, and define above.
    )

    // Helper method to format discount amount (£ or p)
    private def formatDiscount(totalDiscount: BigDecimal): String = {
        if (totalDiscount >= 1) f"£$totalDiscount%.2f"
        else f"${totalDiscount * 100}%.0fp"
    }

    // Main method that calls every discount function and returns the total discount.
    def calculateDiscounts(items: List[Item]): (List[String], BigDecimal) = {
        val discount = discountFunctions.map(f => f(items)._2).sum
        if (discount > 0) {
            val discountTexts = discountFunctions.map(f => f(items)._1).filter(_.nonEmpty)
            (discountTexts, discount)
        } else {
            (List("(No offers available)"), BigDecimal(0))
        }
    }
}



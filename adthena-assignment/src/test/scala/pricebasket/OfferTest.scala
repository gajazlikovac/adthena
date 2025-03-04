package pricebasket

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class OfferSpec extends AnyWordSpec with Matchers {
  "Offer" should {
    "apply 10% discount on Apples" in {
      val items = List(Item("Apples", BigDecimal("1.00")))
      Offer.calculateDiscounts(items)._2 shouldBe 0.10
    }

    "apply 10% discount on all apples (5 apples)" in {
      val items = List.fill(5)(Item("Apples", BigDecimal("1.00")))
      Offer.calculateDiscounts(items)._2 shouldBe 0.50
    }

    "apply half price on Bread when buying 2 Soup" in {
      val items = List(
        Item("Soup", BigDecimal("0.65")), 
        Item("Soup", BigDecimal("0.65")), 
        Item("Bread", BigDecimal("0.80"))
      )
      Offer.calculateDiscounts(items)._2 shouldBe 0.40
    }

    "apply half price on only 1 Bread when buying 2 soups, 3 breads and apple" in {
      val items = List(
        Item("Soup", BigDecimal("0.65")),
        Item("Soup", BigDecimal("0.65")),
        Item("Bread", BigDecimal("0.80")),
        Item("Bread", BigDecimal("0.80")),
        Item("Bread", BigDecimal("0.80")),
        Item("Apples", BigDecimal("1.00"))
      )
      Offer.calculateDiscounts(items)._2 shouldBe 0.50
    }

    "apply half price on 2 breads when buying 4 soups, 3 breads" in {
      val items = List(
      Item("Soup", BigDecimal("0.65")),
      Item("Soup", BigDecimal("0.65")),
      Item("Soup", BigDecimal("0.65")),
      Item("Soup", BigDecimal("0.65")),
      Item("Bread", BigDecimal("0.80")),
      Item("Bread", BigDecimal("0.80")),
      Item("Bread", BigDecimal("0.80"))
      )
      Offer.calculateDiscounts(items)._2 shouldBe 0.80
    }

    "apply no discount when buying only Milk" in {
      val items = List(Item("Milk", BigDecimal("1.30")))
      Offer.calculateDiscounts(items)._2 shouldBe 0.00
    }
  }
}

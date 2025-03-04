package pricebasket

import org.scalatest.funsuite.AnyFunSuite

class OfferSpec extends AnyFunSuite {
  test("10% discount on Apples") {
    val items = List(Item("Apples", BigDecimal("1.00")))
    assert(Offer.calculateDiscount(items) == 0.10)
  }

  test("Buy 2 Soup, get Bread at half price") {
    val items = List(Item("Soup", BigDecimal("0.65")), Item("Soup", BigDecimal("0.65")), Item("Bread", BigDecimal("0.80")))
    assert(Offer.calculateDiscount(items) == 0.40)
  }

  test("No discount when buying only Milk") {
    val items = List(Item("Milk", BigDecimal("1.30")))
    assert(Offer.calculateDiscount(items) == 0.00)
  }
}

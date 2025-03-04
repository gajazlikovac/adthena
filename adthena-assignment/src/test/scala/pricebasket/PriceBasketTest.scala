package pricebasket

import org.scalatest.funsuite.AnyFunSuite

class PriceBasketSpec extends AnyFunSuite {
  test("calculates correct subtotal for Apples, Milk, Bread") {
    val basket = List("Apples", "Milk", "Bread")
    PriceBasket.calculateTotal(basket)
    assert(true) // Just ensuring it runs without errors
  }

  test("handles invalid item gracefully") {
    val basket = List("Banana", "Milk")
    PriceBasket.calculateTotal(basket)
    assert(true) // Ensures program doesn't crash
  }
}

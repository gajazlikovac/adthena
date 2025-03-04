package pricebasket

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PriceBasketSpec extends AnyWordSpec with Matchers {
  "PriceBasket" should {
    "print correct output for empty basket" in {
      val basket = List.empty[String]
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        PriceBasket.calculateTotal(basket)
      }
      output.toString.trim shouldBe "Error: No items provided. Usage: PriceBasket item1 item2 ..."
    }

    "print correct output for valid basket" in {
      val basket = List("Apples", "Milk", "Bread")
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        PriceBasket.calculateTotal(basket)
      }
      output.toString.trim shouldBe """Subtotal: £3,10
                                    |Apples 10% off: 10p
                                    |Total price: £3,00""".stripMargin
    }

    "print correct output for invalid items" in {
      val basket = List("Banana", "Milk")
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        PriceBasket.calculateTotal(basket)
      }
      output.toString.trim shouldBe "Error: Invalid items found - Banana"
    }
  }
}




// "PriceBasket" should {
//     "calculate correct subtotal for Apples, Milk, Bread" in {
//       val basket = List("Apples", "Milk", "Bread")
//       PriceBasket.calculateTotal(basket).toString shouldBe "Subtotal: £3.10\nApples 10% off: 10p\nTotal price: £3.00"
//     }

//     "handle invalid items gracefully" in {
//       val basket = List("Banana", "Milk")
//       PriceBasket.calculateTotal(basket).toString shouldBe "Error: Invalid items found - Banana"
//     }
//   }
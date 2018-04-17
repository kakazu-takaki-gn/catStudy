package kkz.phase2

object Kkz2 {

  def main(args: Array[String]): Unit = {

    //    import cats.Monoid
    //    import cats.instances.string._
    //    println(monoid.Monoid[String].combine("Hi ", "there"))
    //    println(monoid.Monoid[String].empty)

    //    println(Monoid.apply[String].combine("Hi ", "there"))
    //    println(Monoid.apply[String].empty)

    //    import cats.Semigroup
    //    import cats.instances.string._
    //    println(Semigroup[String].combine("Hi ", "there"))

    //    import cats.Monoid
    //    import cats.instances.int._ // for Monoid
    //    println(Monoid[Int].combine(32, 10))

    //    import cats.Monoid
    //    import cats.instances.int._    // for Monoid
    //    import cats.instances.option._ // for Monoid
    //    val a = Option(22)
    //    val b = Option(20)
    //    println(Monoid[Option[Int]].combine(a, b))

    //    import cats.Monoid
    //    import cats.instances.int._
    //    import cats.instances.string._
    //    import cats.syntax.semigroup._
    //    val stringResult = "Hi " |+| "there" |+| Monoid[String].empty // stringResult: String = Hi there
    //    val intResult    = 1 |+| 2 |+| Monoid[Int].empty
    //    println(stringResult)
    //    println(intResult)

    import cats.Monoid
    import cats.instances.int._
    import cats.syntax.semigroup._

    def add1(items: List[Int]): Int =
      items.foldLeft(Monoid[Int].empty)(_ |+| _)

    def add2[A: Monoid](items: List[A]): A =
      items.foldLeft(Monoid[A].empty)(_ |+| _)

    println(add1(List(1, 2, 3, 4)))
    println(add2(List(1, 2, 3, 4)))

    import cats.instances.option._
    println(add2(List(Some(1), None, Some(2), None, Some(3))))
    //    println(add2(List(Some(1), Some(2), Some(3))))

    val o1 = Order(0.2, 2.3)
    val o2 = Order(0.4, 4.3)

    implicit val orderAddMonoid: Monoid[Order] = new Monoid[Order] {
      def combine(a: Order, b: Order): Order = Order(a.totalCost + b.totalCost, a.quantity + b.quantity)
      def empty                              = Order(0, 0)
    }

    println(add2(List(o1, o2)))

  }
}

case class Order(totalCost: Double, quantity: Double)

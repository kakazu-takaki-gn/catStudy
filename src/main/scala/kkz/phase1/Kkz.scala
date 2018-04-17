package kkz.phase1

object Kkz {

  import JsonWriterInstances._
  import JsonSyntax._

  def main(args: Array[String]): Unit = {
    println(Json.toJson(Person("Dave", "dave@example.com")))
    println(Json.toJson(Person("Dave", "dave@example.com"))(personWriter))
    println(Person("Dave", "dave@example.com").toJson)
    println(Person("Dave", "dave@example.com").toJson(personWriter))
    println(implicitly[JsonWriter[String]])
    println(Json.toJson("A string!"))
    println(Json.toJson(Option("A string")))
    println(Json.toJson(Option("A string"))(optionWriter[String]))
    println(Json.toJson(Option("A string"))(optionWriter(stringWriter)))

//    implicit val writer1: JsonWriter[String] = JsonWriterInstances.stringWriter
//    implicit val writer2: JsonWriter[String] = JsonWriterInstances.stringWriter
//    Json.toJson("A string")

    import cats.Show
    import cats.instances.int._
    import cats.instances.string._
    val showInt: Show[Int]       = Show.apply[Int]
    val showString: Show[String] = Show.apply[String]
    println(showInt.show(123))
    println(showString.show("abc"))

    import cats.syntax.show._

    println(123.show)
    println("abc".show)

    import java.util.Date
//    implicit val dateShow: Show[Date] = new Show[Date] {
//      def show(date: Date): String = s"${date.getTime}ms since the epoch."
//    }

    implicit val dateShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch.")

    val showData: Show[Date] = Show.apply[Date]
    val aa                   = new Date
    println(showData.show(new Date))
    println(aa.show)

    implicit val catShow: Show[Cat] = Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat.")

    val cat = Cat("tama", 3, "white")
    println(Show[Cat].show(cat))
    println(cat.show)

    import cats.Eq
    import cats.instances.int._

    val eqInt = Eq[Int]

    println(eqInt.eqv(123, 123))
    println(eqInt.eqv(123, 234))

    import cats.syntax.eq._
    println(123 === 123)
    println(123 =!= 43)

    import cats.instances.int._    // for Eq
    import cats.instances.option._ // for Eq

    println((Some(1): Option[Int]) === (None: Option[Int]))
    println(Option(1) === Option.empty[Int])

    import cats.syntax.option._ // for some and none
    1.some === none[Int]
    1.some =!= none[Int]

    import java.util.Date
    import cats.instances.long._

    implicit val dateEq: Eq[Date] =
      Eq.instance[Date] { (date1, date2) =>
        date1.getTime === date2.getTime
      }
    val x = new Date() // now
    val y = new Date() // a bit later than now
    x === x
    x === y

    implicit val catEq: Eq[Cat] =
      Eq.instance[Cat] { (cat1, cat2) =>
        cat1.age === cat2.age &&
        cat1.color === cat2.color &&
        cat1.name === cat2.name
      }

    val cat1       = Cat("Garfield", 38, "orange and black")
    val cat2       = Cat("Heathcliff", 33, "orange and black")
    val cat3       = Cat("Garfield", 38, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option(cat2)
    val optionCat3 = Option(cat3)

//    catEq(cat1, cat2)

    println(cat1 === cat2)
    println(cat1 === cat3)

    println(optionCat1 === optionCat2)
    println(optionCat1 === optionCat3)
    println(optionCat1 === Option.empty[Cat])

//    sealed trait Shape
//    case class Circle(radius: Double) extends Shape
//    val circles: List[Circle] = ???
//    val shapes: List[Shape]   = circles
//
//    val shape: Shape                                     = ???
//    val circle: Circle                                   = ???
//    val shapeWriter: JsonWriter[Shape]                   = ???
//    val circleWriter: JsonWriter[Circle]                 = ???
//    def format[A](value: A, writer: JsonWriter[A]): Json = writer.write(value)
//
//    sealed trait A
//    final case object B extends A
//    final case object C extends A
  }
}

package cats.sandbox

import cats._
import cats.implicits._
import org.scalatest.{FlatSpec, Matchers}

class VarietyTest extends FlatSpec with Matchers {

  /** Eq */
  """"Hello" =!= "World"""" should "return ?" in {
    "Hello" =!= "World" shouldBe true
  }

  /** Show */
  """Person("John", 31).show""" should "return ?" in {
    case class Person(name: String, age: Int)
    implicit val showPerson: Show[Person] = Show.show(person => person.name)
    val john = Person("John", 31)
    john.show shouldBe "John"
  }

  /** Semigroup */
  """Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6))""" should "return List(?)" in {
    Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6)) shouldBe List(1, 2, 3, 4, 5, 6)
  }

  /** Monoid */
  """Monoid[Map[String, Int]].combineAll(List(Map("a" → 1, "b" → 2), Map("a" → 3)))""" should "return Map(?)" in {
    Monoid[Map[String, Int]]
      .combineAll(List(Map("a" → 1, "b" → 2), Map("a" → 3))) shouldBe Map("a" → 4, "b" → 2)
  }

//  """l.foldMap(i ⇒ (i, i.toString))""" should "return (?, ?)" in {
//    val l = List(1, 2, 3, 4, 5)
//    l.foldMap(i ⇒ (i, i.toString)) shouldBe (15, "12345")
//  }

  /** Functor */
  """Functor[Option].map(Option("Hello"))(_.length)""" should "return ?(?)" in {
    Functor[Option].map(Option("Hello"))(_.length) shouldBe Some(5)
  }

  """Functor[Option].map(None: Option[String])(_.length)""" should "return ?(?)" in {
    Functor[Option].map(None: Option[String])(_.length) shouldBe None
  }

  /*

  """Functor[List].fproduct(source)(_.length).toMap.get("Cats").getOrElse(0)""" should "return ?" in {
    val source = List("Cats", "is", "awesome")
    val product = Functor[List].fproduct(source)(_.length).toMap

    product.get("Cats").getOrElse(0) shouldBe ???
  }

  """Functor[List] compose Functor[Option] map List(Some(1), None, Some(3)) (_ + 1)""" should "return List(?)" in {
    val listOpt = Functor[List] compose Functor[Option]
    listOpt.map(List(Some(1), None, Some(3)))(_ + 1) shouldBe ???
  }

  /** Apply */
  """Apply[Option].map(Some(1))(_ + 2)""" should "return ?(?)" in {
    Apply[Option].map(Some(1))(_ + 2) shouldBe ???
  }

  /** Applicative */
  """(Applicative[List] compose Applicative[Option]).pure(1)""" should "return ?(?)" in {
    (Applicative[List] compose Applicative[Option]).pure(1) shouldBe ???
  }

  /** Monad */
  """Monad[List].flatMap(List(1, 2, 3))(x ⇒ List(x, x))""" should "return List(?)" in {
    Monad[List].flatMap(List(1, 2, 3))(x ⇒ List(x, x)) shouldBe ???
  }
  """Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4))""" should "return List(?)" in {
    Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4)) shouldBe ???
  }

  /** Foldable */
  """Foldable[List].fold(List("a", "b", "c"))""" should "return ?" in {
    Foldable[List].fold(List("a", "b", "c")) shouldBe ???
  }
  """Foldable[List].foldMap(List("a", "b", "c"))(_.length)""" should "return ?" in {
    Foldable[List].foldMap(List("a", "b", "c"))(_.length) shouldBe ???
  }
  """Foldable[List].foldK(List(None, Option("two"), Option("three")))""" should "?(?)" in {
    Foldable[List].foldK(List(None, Option("two"), Option("three"))) shouldBe ???
    // Option.fold = getOrElse
  }

  /** Traverse */
  """List(Option(1), None, Option(3)).traverse(identity)""" should "return ?(?)" in {
    List(Option(1), None, Option(3)).traverse(identity) shouldBe ???
  }
  """List(Option(1), None, Option(3)).sequence_""" should "return ?(?)" in {
    List(Option(1), None, Option(3)).sequence_ shouldBe ???
  }

  /** Identity */
  """(anId: Id[Int] = 42)""" should "return ?" in {
    val anId: Id[Int] = 42
    anId shouldBe ???
  }
  """Comonad[Id].coflatMap(fortytwo)(_ + 1)""" should "return ?" in {
    val fortytwo: Int = 42
    Comonad[Id].coflatMap(fortytwo)(_ + 1) shouldBe ???
  }

  /** Either */
  """left.flatMap(x ⇒ Either.right(x + 1))""" should "return ?(?)" in {
    val left: Either[String, Int] = Either.left("Something went wrong")
    left.flatMap(x ⇒ Either.right(x + 1)) shouldBe ???
  }
  """42.asRight[String]""" should "return ?" in {
    42.asRight[String] shouldBe ???
  }
  */
}

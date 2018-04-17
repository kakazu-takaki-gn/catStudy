package kkz.phase2.monoid

trait Semigroup[A] {
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
  def empty: A
}
object Monoid {

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(a: Boolean, b: Boolean): Boolean = a && b
    def empty                                    = true
  }

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(a: Boolean, b: Boolean): Boolean = a || b
    def empty                                    = false
  }

  implicit val booleanEitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(a: Boolean, b: Boolean): Boolean = (a && !b) || (!a && b)
    def empty                                    = false
  }

  implicit val booleanEXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(a: Boolean, b: Boolean): Boolean = (!a || b) && (a || !b)
    def empty                                    = true
  }

  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    def combine(a: Set[A], b: Set[A]) = a ++ b
    def empty                         = Set.empty[A]
  }

  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

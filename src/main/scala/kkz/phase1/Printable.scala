package kkz.phase1

trait Printable[A] {
  def format(v: A): String
}

object PrintableInstances {
  implicit val stringFormat: Printable[String] = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val intFormat: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = value.toString
  }

  implicit val catFormat: Printable[Cat] = new Printable[Cat] {
    def format(value: Cat): String =
      s"${Printable.format(value.name)} is a ${Printable.format(value.age)} year-old ${Printable.format(value.color)} cat."
  }
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)
  def print[A](value: A)(implicit p: Printable[A]): Unit    = println(format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit w: Printable[A]): String = w.format(value)
    def print(implicit w: Printable[A]): Unit    = println(w.format(value))
  }
}

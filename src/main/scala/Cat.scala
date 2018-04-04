final case class Cat(name: String, age: Int, color: String)

object CatKkz {
  import PrintableInstances._
  import PrintableSyntax._
  def main(args: Array[String]): Unit = {
    Printable.print(Cat("yaki", 10, "red"))
    Cat("kkz", 20, "green").print
  }
}

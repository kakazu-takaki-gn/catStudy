package kkz.phase1

object CatKkz {
  import PrintableInstances._
  import PrintableSyntax._
  def main(args: Array[String]): Unit = {
    Printable.print(Cat("yaki", 10, "red"))
    Cat("kkz", 20, "green").print
  }
}

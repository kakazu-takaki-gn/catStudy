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
  }
}

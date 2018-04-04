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

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    def write(value: String): Json = JsString(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    def write(value: Person): Json =
      JsObject(
        Map(
          "name"  -> JsString(value.name),
          "email" -> JsString(value.email)
        )
      )
  }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    def write(option: Option[A]): Json =
      option match {
        case Some(aValue) => writer.write(aValue)
        case None         => JsNull
      }
  }

//  implicit def optionWriter[A](writer: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
//    def write(option: Option[A]): Json =
//      option match {
//        case Some(aValue) => writer.write(aValue)
//        case None         => JsNull
//      }
//  }

}

trait JsonWriter[A] {
  def write(value: A): Json
}

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }
}

final case class Person(name: String, email: String)

sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String)            extends Json
final case class JsNumber(get: Double)            extends Json
case object JsNull                                extends Json

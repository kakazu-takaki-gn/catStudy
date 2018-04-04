package kkz.phase1

trait JsonWriter[A] {
  def write(value: A): Json
}

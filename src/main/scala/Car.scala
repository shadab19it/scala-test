import scala.io.{Source, StdIn}

object Car {
  def main(args: Array[String]): Unit = {
    val fruit = List("apples", "oranges", "pears")
        print(fruit)
    val content = Source.fromFile(args(0))
    println(content.mkString)
    print("hello " + inp)
    val it = Iterator("a", "number", "of", "words")
    while (it.hasNext){
      println(it.next())
    }
  }
  var inp = ""
  inp = StdIn.readLine("Please enter you name \n")
  var num: Number = 4
}
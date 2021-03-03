object ArrayList {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)
    // print list
    println(list)
    //apply operation
    val result = list.drop(4)
    //print result
    println(result)
  }
}

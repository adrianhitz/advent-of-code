import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Day07 extends AdventIO {
  implicit val s: String = Read("07.txt")

  def main(args: Array[String]): Unit = {
    part1
    // Write("07a.txt", part1.toString)
    // Write("07b.txt", part2.toString)
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): Unit = {
    val reqs: Array[(Char, Char)] = s.split('\n').map(x => (x.charAt(5), x.charAt(36)))
    val map = mutable.Map[Char, Set[Char]]()
    for(req <- reqs) {
      if(!map.contains(req._1)) map(req._1) = Set()
      map(req._1) = map(req._1) + req._2
    }
    for(key <- map.keys.toList.sorted) {
      println(key + ": " + map(key).mkString(", "))
    }
    var start = map.keys.toSet
    for((_, v) <- map) {
      start = start diff v
    }
    var next = start
    val completed = ListBuffer[Char]()
    while(next.nonEmpty) {
      val c = next.min
      completed append c
      if(map.contains(c)) next = next union map(c)
      next = next diff completed.toSet
    }
    println(completed.mkString(""))
    println(completed.length)
  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): Unit = ???
}
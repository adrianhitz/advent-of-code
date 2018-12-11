import scala.collection.mutable.Stack

object Day08 extends AdventIO {
  implicit val s: String = Read("08.txt")

  def main(args: Array[String]): Unit = {
    // Write("08a.txt", part1.toString)
    // Write("08b.txt", part2.toString)
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): Int = {
    val in = s.toCharArray
    var metaDataSum = 0
    case class Node(children: Int, metaData: Int)
    val stack = List[Node](Node(in(0), in(1)))

    // TODO

    metaDataSum
  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): Unit = ???
}
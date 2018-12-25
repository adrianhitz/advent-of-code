package adrianhitz.adventofcode

object Day08 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    // write1(part1.toString)
    // write2(part2.toString)
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

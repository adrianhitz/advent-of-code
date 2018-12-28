package adrianhitz.adventofcode

object Day08 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    // write2(part2.toString)
  }

  def part1(implicit s: String): Int = {
    val tree = s.split(' ').map(_.toInt)
    case class Node(children: Int, metaData: Int)
    var stack = List(Node(tree(0), tree(1)))
    var metaDataSum = 0
    var i = 2
    while(i < tree.length) {
      val head = stack.head
      if(head.children > 0) {
        // Make a new child node, push to stack
        stack = Node(tree(i), tree(i + 1)) :: Node(head.children - 1, head.metaData) :: stack.tail
        i += 2
      } else if(head.metaData > 0) {
        // Read meta data, update or remove node on the stack
        metaDataSum += tree(i)
        if(head.metaData > 1) {
          stack = Node(0, head.metaData - 1) :: stack.tail
        } else {
          stack = stack.tail
        }
        i += 1
      }
    }
    metaDataSum
  }

  def part2(implicit s: String): Unit = ??? // TODO Solve day 08 part 2
}

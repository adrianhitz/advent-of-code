package adrianhitz.adventofcode

object Day12 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    // write1(part1.toString)
    // write2(part2.toString)
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): Unit = {
    val lines: Array[String] = s.split('\n')
    val initial: Array[Char] = lines(0).split(' ').last.toCharArray

    def f(c: Array[Char]): Char = {
      for(rule <- lines.slice(2, lines.length)) {
        if(rule.substring(0, 5).toCharArray.sameElements(c))
          return rule.charAt(9)
      }
      '?'
    }

    var state: Map[Int, Char] = initial.zipWithIndex.map(x => (x._2, x._1)).toMap
    for(i <- 0 until 20) {


      // Apply function
    }

  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): Unit = ???
}

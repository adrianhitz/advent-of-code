package adrianhitz.adventofcode

object Day01 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  // Golf: 30 bytes
  def part1(implicit s: String): Int = s.split('\n').map(_.toInt).sum

  // Golf: 112 bytes
  def part2(implicit s: String): Int = {
    var e = Set[Int]()
    val l = s.split('\n').map(_.toInt)
    var f, i = 0
    while (!e.contains(f)) {
      e = e + f
      f += l(i % l.length)
      i += 1
    }
    f
  }
}

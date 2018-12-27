package adrianhitz.adventofcode

object Day01 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  // 30 bytes
  def part1(implicit s: String): Int = s.split('\n').map(_.toInt).sum

  def part2(implicit s: String): Int = {
    var frequencies = Set[Int]()
    val freqChanges = s.split('\n').map(_.toInt)
    var currentFreq = 0
    var i = 0
    while(!frequencies.contains(currentFreq)) {
      frequencies += currentFreq
      currentFreq += freqChanges(i % freqChanges.length)
      i += 1
    }
    currentFreq
  }

  // 111 bytes, @formatter:off
  def part2golf(implicit s: String): Int = {
    var e=Set[Int]()
    val l=s.split('\n').map(_.toInt)
    var f,i=0
    while(!e.contains(f)){e+=f
    f+=l(i%l.length)
    i+=1}
    f
  }
}

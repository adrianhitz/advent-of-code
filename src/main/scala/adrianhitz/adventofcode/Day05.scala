package adrianhitz.adventofcode

object Day05 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  // Golf: 93 bytes
  def part1(implicit s: String): Int = {
    var t = s
    var l = 99999
    while(t.length < l) {
      l = t.length
      t = t.replaceAll("(\\w)(?!\\1)(?i:\\1)", "")
    }
    l
  }

  // Golf: 202 bytes
  def part2(implicit s: String): Int = {
    var m = 99999
    for(i <- 'A'.toInt to 'Z'.toInt) {
      var t = s.replaceAll("[" + i.toChar + "" + (i + 32).toChar + "]", "")
      var l = 99999
      while(t.length < l) {
        l = t.length
        t = t.replaceAll("(\\w)(?!\\1)(?i:\\1)", "")
      }
      m = Math.min(m, l)
    }
    m
  }
}

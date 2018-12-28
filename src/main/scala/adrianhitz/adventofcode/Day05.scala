package adrianhitz.adventofcode

object Day05 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  def part1(implicit s: String): Int = {
    var polymer = s
    var length = Int.MaxValue
    while(polymer.length < length) {
      length = polymer.length
      polymer = polymer.replaceAll("(\\w)(?!\\1)(?i:\\1)", "")
    }
    length
  }

  def part2(implicit s: String): Int = {
    var shortestPolymerLength = Int.MaxValue
    for(letter <- 'A'.toInt to 'Z'.toInt) {
      var polymer = s.replaceAll("[" + letter.toChar + "" + (letter + 32).toChar + "]", "")
      var length = Int.MaxValue
      while(polymer.length < length) {
        length = polymer.length
        polymer = polymer.replaceAll("(\\w)(?!\\1)(?i:\\1)", "")
      }
      shortestPolymerLength = Math.min(shortestPolymerLength, length)
    }
    shortestPolymerLength
  }

  // @formatter:off
  // 93 bytes
  def part1golf(implicit s: String): Int = {
    var t=s
    var l=99999
    while(t.length<l){l=t.length
    t=t.replaceAll("(\\w)(?!\\1)(?i:\\1)","")}
    l
  }

  // 202 bytes
  def part2golf(implicit s: String): Int = {
    var m=99999
    for(i<-'A'.toInt to 'Z'.toInt){var t=s.replaceAll("["+i.toChar+""+(i+32).toChar+"]","")
    var l=99999
    while(t.length<l){l=t.length
    t=t.replaceAll("(\\w)(?!\\1)(?i:\\1)","")}
    m=Math.min(m,l)}
    m
  }
}

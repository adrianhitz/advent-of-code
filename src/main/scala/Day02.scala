
object Day02 extends AdventIO {

  implicit val s: String = Read("02.txt")

  def main(args: Array[String]): Unit = {
    Write("02a.txt", part1.toString)
    // Write("02b.txt", part2.toString)
  }

  // Golf: 133 bytes
  def part1(implicit s: String): Int = {
    var a, b = 0
    for(l <- s.split('\n')) {
      def f(x: Int) = l.foldLeft(false)((i, c) => if(l.count(_ == c) == x) true else i)
      if(f(2)) a += 1
      if(f(3)) b += 1
    }
    a * b
  }

  // Golf:
  def part2(implicit s: String): Int = ???

}
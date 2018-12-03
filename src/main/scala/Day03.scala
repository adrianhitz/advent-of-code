object Day03 extends AdventIO {
  implicit val s: String = Read("03.txt")

  def main(args: Array[String]): Unit = {
    Write("03a.txt", part1.toString)
    println(part2)
    // Write("03b.txt", part2.toString)
  }

  // Golf: ? bytes
  def part1(implicit s: String): Int = {
    val ls = s.split('\n').map(_.replaceAll("(#[0-9]+ @ )| ", "")
      .replaceAll("[:x]", ",")
      .split(',')
      .map(_.toInt)
    )
    val a = Array.ofDim[Int](1000, 1000)
    for(l: Array[Int] <- ls)
      for(x <- l(0) until l(0) + l(2))
        for(y <- l(1) until l(1) + l(3))
          a(x)(y) += 1
    a.flatten.count(_ >= 2)
  }

  // Golf: ? bytes
  def part2(implicit s: String): String = ???
}
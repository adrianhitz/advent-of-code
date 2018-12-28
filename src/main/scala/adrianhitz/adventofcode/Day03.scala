package adrianhitz.adventofcode

object Day03 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  def part1(implicit s: String): Int = {
    val lines = s.split('\n').map(_.replaceAll("(#[0-9]+ @ )| ", "")
      .replaceAll("[:x]", ",")
      .split(',')
      .map(_.toInt)
    )
    val fabric = Array.ofDim[Int](1000, 1000)
    for(line <- lines)
      for(x <- line(0) until line(0) + line(2))
        for(y <- line(1) until line(1) + line(3))
          fabric(x)(y) += 1
    fabric.flatten.count(_ >= 2)
  }

  def part2(implicit s: String): Int = {
    val lines = s.split('\n').map(_.replaceAll("[ #]", "")
      .replaceAll("[@:x]", ",")
      .split(',')
      .map(_.toInt)
    )
    val fabric = Array.ofDim[Int](1000, 1000)
    for(line <- lines)
      for(x <- line(1) until line(1) + line(3))
        for(y <- line(2) until line(2) + line(4))
          fabric(x)(y) += 1
    fabric.flatten.count(_ >= 2)
    for(line <- lines) {
      val sliced = fabric.slice(line(1), line(1) + line(3)).map(_.slice(line(2), line(2) + line(4)))
      var overlap = false
      for(i <- sliced.flatten) {
        if(i != 1) overlap = true
      }
      if(!overlap) return line(0)
    }
    throw new IllegalArgumentException("No solution")
  }
}

object Day06 extends AdventIO {
  implicit val s: String = Read("06.txt")

  def main(args: Array[String]): Unit = {
    Write("06a.txt", part1.toString)
    // Write("06b.txt", part2.toString)
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): Unit = {
    val points: Array[Array[Int]] = s.split('\n').map(_.split(", ").map(_.toInt))
    val xRange = (points.map(_ (0)).min, points.map(_ (0)).max)
    val yRange = (points.map(_ (1)).min, points.map(_ (1)).max)
    val height = xRange._2 - xRange._1 + 1
    val width = yRange._2 - yRange._1 + 1
    val grid = Array.ofDim[Int](height, width)
    for(x <- 0 to height) for(y <- 0 to width) {
      
    }

  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): Unit = ???
}
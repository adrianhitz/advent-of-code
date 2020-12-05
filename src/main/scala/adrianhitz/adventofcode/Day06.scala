package adrianhitz.adventofcode

object Day06 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    // write2(part2.toString)
  }

  def part1(implicit s: String): Unit = {
    val points: Array[Array[Int]] = s.split('\n').map(_.split(", ").map(_.toInt))
    val xRange = (points.map(_(0)).min, points.map(_(0)).max)
    val yRange = (points.map(_(1)).min, points.map(_(1)).max)
    val height = xRange._2 - xRange._1 + 1
    val width = yRange._2 - yRange._1 + 1
    val grid = Array.ofDim[Int](height, width)
    for(x <- 0 to height) for(y <- 0 to width) {}
    // TODO Solve day 06 part 1
  }

  def part2(implicit s: String): Unit = ??? // TODO Solve day 06 part 2
}

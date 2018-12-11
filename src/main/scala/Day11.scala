object Day11 extends AdventIO {
  implicit val s: String = Read("11.txt")

  def main(args: Array[String]): Unit = {
    Write("11a.txt", part1.toString)
    Write("11b.txt", part2.toString)
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): String = {
    val serial = s.toInt
    val grid = Array.ofDim[Int](301, 301)
    for(x <- 1 to 300) for(y <- 1 to 300) {
      grid(x)(y) = ((((x + 10) * y + serial) * (x + 10)) / 100) % 10 - 5
    }
    var maxSum = 0
    var maxCoords = (0, 0)
    for(x <- 1 to 298) for(y <- 1 to 298) {
      val sum = grid.slice(x, x + 3).flatMap(_.slice(y, y + 3)).sum
      if(sum > maxSum) {
        maxSum = sum
        maxCoords = (x, y)
      }
    }
    maxCoords._1 + "," + maxCoords._2
  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): String = {
    val serial = s.toInt
    val grid = Array.ofDim[Int](301, 301)
    for(x <- 1 to 300) for(y <- 1 to 300) {
      grid(x)(y) = ((((x + 10) * y + serial) * (x + 10)) / 100) % 10 - 5
    }
    val sums = Array.ofDim[Int](301, 301)
    for(x <- 1 to 300) for(y <- 1 to 300) {
      sums(x)(y) = grid(x)(y) + sums(x - 1)(y) + sums(x)(y - 1) - sums(x - 1)(y - 1)
    }
    var maxSum = 0
    var maxCoords = (0, 0)
    var maxSize = 0
    for(n <- 1 to 300) {
      for(x <- 1 to (301 - n)) for(y <- 1 to (301 - n)) {
        val left = sums(x - 1)(y + (n - 1))
        val top = sums(x + (n - 1))(y - 1)
        val topLeft = sums(x - 1)(y - 1)
        val sum = sums(x + (n - 1))(y + (n - 1)) - left - top + topLeft
        if(sum > maxSum) {
          maxSum = sum
          maxCoords = (x, y)
          maxSize = n
        }
      }
    }
    maxCoords._1 + "," + maxCoords._2 + "," + maxSize
  }
}
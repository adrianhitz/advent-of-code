package adrianhitz.adventofcode

object Day02 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  def part1(implicit s: String): Int = {
    var doubleCount, tripleCount = 0
    for(boxId <- s.split('\n')) {

      // True if a letter appears exactly n times in this box ID
      def f(n: Int) = boxId.foldLeft(false)((i, c) => if(boxId.count(_ == c) == n) true else i)

      if(f(2)) doubleCount += 1
      if(f(3)) tripleCount += 1
    }
    doubleCount * tripleCount
  }

  def part2(implicit s: String): String = {
    val boxIds = s.split('\n')
    for(id1 <- boxIds) for(id2 <- boxIds) {
      var commonLetters = ""
      for(i <- 0 to 25) {
        if(id1(i) == id2(i)) commonLetters += id1(i)
      }
      if(commonLetters.length == 25) return commonLetters
    }
    throw new IllegalArgumentException("No solution")
  }

  // @formatter:off
  // 133 bytes
  def part1golf(implicit s: String): Int = {
    var a,b=0
    for(l<-s.split('\n')){def f(n:Int)=l.foldLeft(false)((i,c)=>if(l.count(_==c)==n)true else i)
    if(f(2))a+=1
    if(f(3))b+=1}
    a*b
  }

  // 112 bytes
  def part2golf(implicit s: String): String = {
    val t=s.split('\n')
    for(a<-t)for(b<-t){var s=""
    for(i<-0 to 25)if(a(i)==b(i))s+=a(i)
    if(s.length==25)return s}
    s
  }
}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Day07 extends AdventIO {
  implicit val s: String = Read("07.txt")

  def main(args: Array[String]): Unit = {
    Write("07a.txt", part1)
    // Write("07b.txt", part2.toString)
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): String = {
    val reqList: Array[(Char, Char)] = s.split('\n').map(x => (x.charAt(5), x.charAt(36)))
    val reqs = mutable.Map[Char, Set[Char]]() // Map containing all required steps for a given step
    for(req <- reqList) {
      if(!reqs.contains(req._2)) reqs(req._2) = Set()
      reqs(req._2) = reqs(req._2) + req._1
    }
    var nextSteps = reqs.values.flatten.toSet diff reqs.keys.toSet
    val completed = ListBuffer[Char]()
    while(nextSteps.nonEmpty) {
      val nextStep = nextSteps.min
      completed append nextStep
      nextSteps = nextSteps diff completed.toSet
      completed.foreach(reqs.remove)
      for((k, v) <- reqs) {
        if(v.subsetOf(completed.toSet)) nextSteps += k
      }
    }
    completed.mkString("")
  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): Unit = ???
}
package adrianhitz.adventofcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Day07 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1)
    write2(part2.toString)
  }

  def part1(implicit s: String): String = {
    val reqList: Array[(Char, Char)] = s.split('\n').map(x => (x.charAt(5), x.charAt(36)))

    // Map containing all required previous steps for a given step
    val reqs = mutable.Map[Char, Set[Char]]()

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

  def part2(implicit s: String): Int = {
    val reqList: Array[(Char, Char)] = s.split('\n').map(x => (x.charAt(5), x.charAt(36)))

    // Map containing all required previous steps for a given step
    val reqs = mutable.Map[Char, Set[Char]]()

    for(req <- reqList) {
      if(!reqs.contains(req._2)) reqs(req._2) = Set()
      reqs(req._2) = reqs(req._2) + req._1
    }
    var nextSteps = reqs.values.flatten.toSet diff reqs.keys.toSet
    val completed = ListBuffer[Char]()
    case class Worker(step: Char, started: Int, duration: Int)
    var workers = List[Worker]()
    var timeWorked = -1
    while(nextSteps.nonEmpty || workers.nonEmpty) {
      timeWorked += 1
      // Complete work
      var finished = List[Worker]()
      for(worker <- workers) {
        if(worker.started + worker.duration <= timeWorked) {
          completed.append(worker.step)
          finished :+= worker
        }
        workers = workers diff finished
      }
      completed.foreach(reqs.remove)
      // Find available steps
      for((k, v) <- reqs) {
        if(v.subsetOf(completed.toSet)) nextSteps += k
      }
      nextSteps = nextSteps diff completed.toSet
      // Assign work
      while(nextSteps.nonEmpty && workers.length < 5) {
        val nextStep = nextSteps.min
        workers :+= Worker(nextStep, timeWorked, nextStep - 'A' + 61)
        nextSteps -= nextStep
        reqs.remove(nextStep)
      }
    }
    timeWorked
  }
}

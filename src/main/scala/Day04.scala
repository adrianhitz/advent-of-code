import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import scala.collection.mutable

// TODO This whole thing desperately needs some cleanup
object Day04 extends AdventIO {
  implicit val s: String = Read("04.txt")

  def main(args: Array[String]): Unit = {
    Write("04a.txt", part1.toString)
    Write("04b.txt", part2.toString)
  }

  // Golf: ? bytes TODO
  def part2(implicit s: String): Int = {

    val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val t = s.split('\n').map(_.replace("[", "").split("] "))
      .map(x => (LocalDateTime.parse(x(0), dtf), x(1)))
      .sortWith((a: (LocalDateTime, String), b: (LocalDateTime, String)) => a._1.compareTo(b._1) < 0)
    var guardId = 0
    val m = mutable.Map[Int, Array[Int]]()
    var asleepTime = 0
    for(l <- t) {
      if(l._2.contains("Guard")) {
        guardId = l._2.replaceAll("[A-Za-z #]", "").toInt
      }
      if(l._2.contains("falls asleep")) {
        asleepTime = l._1.getMinute
      }
      if(l._2.contains("wakes up")) {
        if(!m.contains(guardId)) m += ((guardId, new Array[Int](60)))
        for(i <- asleepTime until l._1.getMinute) {
          m(guardId)(i) += 1
        }
      }
    }
    val chosenGuard = m.maxBy(_._2.max)._1
    val chosenMinute = m.maxBy(_._2.max)._2.zipWithIndex.maxBy(_._1)._2
    chosenGuard * chosenMinute
  }

  // Golf: ? bytes TODO
  def part1(implicit s: String): Int = {
    val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val t = s.split('\n').map(_.replace("[", "").split("] "))
      .map(x => (LocalDateTime.parse(x(0), dtf), x(1)))
      .sortWith((a: (LocalDateTime, String), b: (LocalDateTime, String)) => a._1.compareTo(b._1) < 0)
    var guardId = 0
    val m = mutable.Map[Int, Array[Int]]()
    var asleepTime = 0
    for(l <- t) {
      if(l._2.contains("Guard")) {
        guardId = l._2.replaceAll("[A-Za-z #]", "").toInt
      }
      if(l._2.contains("falls asleep")) {
        asleepTime = l._1.getMinute
      }
      if(l._2.contains("wakes up")) {
        if(!m.contains(guardId)) m += ((guardId, new Array[Int](60)))
        for(i <- asleepTime until l._1.getMinute) {
          m(guardId)(i) += 1
        }
      }
    }
    val chosenGuard = m.maxBy(_._2.sum)._1
    val chosenMinute = m.maxBy(_._2.sum)._2.zipWithIndex.maxBy(_._1)._2
    chosenGuard * chosenMinute

  }
}
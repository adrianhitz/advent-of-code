package adrianhitz.adventofcode

object Day12 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    // write2(part2.toString)
  }

  def part1(implicit s: String): Int = {
    val (initialState, rules) = parseInput(s)
    val numGenerations = 20

    var state = initialState
    for(_ <- 0 until numGenerations) {
      val newState = new State()

      val lowerBoundary = state.leftmost - 2
      val upperBoundary = state.rightmost + 2
      for(i <- lowerBoundary to upperBoundary) {
        // find matching rule and update new state
        val in: List[Boolean] = state.get(i-2, i+3)
        val out = rules.filter(_.in == in).head.out
        if(out) newState.add(i)
      }
      state = newState
    }
    state.getIndicesWithPlants.sum
  }

  def part2(implicit s: String): Unit = {
    ???
  }

  def parseInput(s: String): (State, List[Rule]) = {
    val lines: Array[String] = s.split('\n')

    val stateInput: String = lines(0).split(' ').last
    val ruleInput: Array[String] = lines.slice(2, lines.length)

    val initialState: State = State.fromString(stateInput)

    val rules = ruleInput.map(s => {
      val in = s.slice(0, 5).map(_ == '#').toList
      val out = s(9) == '#'
      Rule(in, out)
    }).toList


    (initialState, rules)
  }
}

case class Rule(in: List[Boolean], out: Boolean)

class State(private var set: Set[Int]) {

  def this() = this(Set())

  def leftmost: Int = set.min
  def rightmost: Int = set.max

  // def get(i: Int): Boolean = ???
  def get(from: Int, until: Int): List[Boolean] =
    (from until until).map(set.contains(_)).toList

  def add(i: Int): Unit =
    set += i

  def getIndicesWithPlants: List[Int] =
    set.toList

}

object State {
  def fromString(s: String): State = {
    val set = s.toCharArray
      .zipWithIndex
      .filter(_._1 == '#')
      .map(_._2)
      .toSet
    new State(set)
  }
}

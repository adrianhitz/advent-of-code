package adrianhitz.adventofcode

object Day12 extends AdventIO {
  override def main(args: Array[String]): Unit = {
    write1(part1.toString)
    write2(part2.toString)
  }

  def part1(implicit s: String): Int = {
    val (initialState, rules) = parseInput(s)
    val targetGens = 20

    var state = initialState
    for(_ <- 0 until targetGens) {
      state = State.calculateNextState(state, rules)
    }

    state.getSum
  }

  def part2(implicit s: String): Long = {
    val (initialState, rules) = parseInput(s)
    val targetGens = 50_000_000_000L

    var state = initialState
    var gens = 0
    var previousDelta = 0
    var deltaRepeats = 0

    // Loop until the growth becomes constant over 10 generations
    while(gens < 1000 || deltaRepeats < 10) {
      // Update state & save sums
      val previousSum = state.getSum
      state = State.calculateNextState(state, rules)
      val sum = state.getSum

      // Check whether the delta has changed
      val delta = sum - previousSum
      if(delta == previousDelta) {
        deltaRepeats += 1
      } else {
        deltaRepeats = 0
        previousDelta = delta
      }

      gens += 1
    }

    // Extrapolate
    val remainingGens = targetGens - gens
    val extrapolatedSum = remainingGens * previousDelta
    state.getSum + extrapolatedSum
  }

  def parseInput(s: String): (State, List[Rule]) = {
    val lines: Array[String] = s.split('\n')

    val stateInput: String = lines(0).split(' ').last
    val ruleInput: Array[String] = lines.slice(2, lines.length)

    val initialState: State = State.fromString(stateInput)

    val rules = ruleInput
      .map(s => {
        val in = s.slice(0, 5).map(_ == '#').toList
        val out = s(9) == '#'
        Rule(in, out)
      })
      .toList

    (initialState, rules)
  }
}

case class Rule(in: List[Boolean], out: Boolean)

class State(private var set: Set[Int]) {

  def this() = this(Set())

  def leftmost: Int = set.min
  def rightmost: Int = set.max

  def get(from: Int, until: Int): List[Boolean] =
    (from until until).map(set.contains(_)).toList

  private def add(i: Int): Unit =
    set += i

  def getSum: Int = set.toList.sum
}

object State {
  def fromString(s: String): State = {
    val set = s.toCharArray.zipWithIndex
      .filter(_._1 == '#')
      .map(_._2)
      .toSet
    new State(set)
  }

  def calculateNextState(state: State, rules: List[Rule]): State = {
    val newState = new State()

    for(i <- (state.leftmost - 2) to (state.rightmost + 2)) {
      // find matching rule and update new state
      val in: List[Boolean] = state.get(i - 2, i + 3)
      val out = rules.filter(_.in == in).head.out
      if(out) newState.add(i)
    }
    newState
  }
}

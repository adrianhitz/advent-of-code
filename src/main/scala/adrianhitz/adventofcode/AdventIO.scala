package adrianhitz.adventofcode

import java.io.{File, PrintWriter}

import scala.io.Source

trait AdventIO {
  private val fileName: String = """\d\d""".r.findAllIn(this.getClass.getSimpleName).toList.head
  protected implicit val input: String = read()

  def main(args: Array[String]): Unit

  protected[this] def write1(s: String): Unit = AdventIO.write(fileName + "_1", s)

  protected[this] def write2(s: String): Unit = AdventIO.write(fileName + "_2", s)

  private[this] def read(): String = Source.fromFile(AdventIO.inputDirectory + fileName + ".txt").getLines().mkString("\n")
}

private object AdventIO {
  private val inputDirectory = """.\src\main\resources\aoc_input\"""
  private val outputDirectory = """.\aoc_output\"""

  private def write(fileName: String, content: String): Unit = {
    val directory = new File(AdventIO.outputDirectory)
    if(!directory.exists()) {
      directory.mkdirs()
    }
    new PrintWriter(new File(AdventIO.outputDirectory + fileName + ".txt")) {
      write(content)
      close()
    }
  }
}
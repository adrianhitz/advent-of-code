import java.io.{File, PrintWriter}

import scala.io.Source

trait AdventIO {
  val inputPath = """.\input\"""
  val outputPath = """.\output\"""

  def Read(fileName: String): String = Source.fromFile(inputPath + fileName).getLines().mkString("\n")

  def Write(fileName: String, content: String): Unit = {
    new PrintWriter(new File(outputPath + fileName)) {
      write(content)
      close()
    }
  }

}

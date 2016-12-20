package ru.spbau.mit.extractor

/**
  * Created by Mark on 20.12.2016.
  */
import org.joda.time.DateTime

import scala.reflect.io.Directory
import scala.tools.nsc.io.File

case class TimeSeriesPoint(time: DateTime, views: Int)

object Analyzer {
  type TimeSeries = List[TimeSeriesPoint]
  private val util = Util.instance
  private val dataFolder = Directory(util.conf.getProperty("output.dir", "E:\\Large stuff\\tweets\\data\\window"))

  private def getOccurancesInFile(file: File, word: String): Int = {
    val bufferedSource = scala.io.Source.fromFile(file.path)
    val line = bufferedSource.getLines().filter(_.toLowerCase().startsWith(word.toLowerCase()))
    var res: Int = 0
    if (line.nonEmpty){
      res = line.next().split(',')(1).toInt
    }
    res
  }

  def getPlotFor(word: String, form: DateTime, to: DateTime=DateTime.now, maxPoints: Int = 30): TimeSeries ={
    var a = List[TimeSeriesPoint]()
    if(dataFolder.exists && dataFolder.isDirectory){
      val occurances = dataFolder.dirs.filter(x => (x.name.toLong <= to.getMillis ) && (x.name.toLong <= to.getMillis ))
        .map(dir => (dir.name.toLong, dir.files.filter(_.name.startsWith("part-")).map(getOccurancesInFile(_, word)).sum)).toList.sorted
      var groupSize: Int = 1
      var maxN = occurances.size
      if(occurances.size > maxPoints){
        groupSize = occurances.size / maxPoints
        maxN = groupSize * maxPoints
      }
      a ++= occurances.take(maxN).grouped(groupSize).map(x => TimeSeriesPoint(new DateTime(x.head._1), x.map(_._2).sum))
    }
    a
  }

  def main(args: Array[String]) {
    print(getPlotFor("Trump", DateTime.now(), DateTime.now().minus(1000 * 60 * 10)))
  }
}


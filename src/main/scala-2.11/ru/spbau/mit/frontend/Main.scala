package ru.spbau.mit.frontend

import org.scalajs.dom
import org.scalajs.dom.html.{BR, Canvas, Label}
import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.util.Random

/**
  * Created by edgar on 19.12.16.
  */
object Main extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  @JSExport
  def plot(): Unit = {
    val item1 = jQuery("#item1-value").value()
    val item2 = jQuery("#item2-value").value()

    val series1 = 1 to 100 `zip` Seq.fill(100)(Random.nextInt(100))
    val series2 = 1 to 100 `zip` Seq.fill(100)(Random.nextInt(100))

    val canvas1Label = dom.document.createElement("label").asInstanceOf[Label]
    canvas1Label.textContent = item1.toString

    val canvas1 = dom.document.createElement("canvas").asInstanceOf[Canvas]
    val ctx1 = canvas1.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    dom.document.body.appendChild(dom.document.createElement("br").asInstanceOf[BR])
    dom.document.body.appendChild(canvas1Label)
    dom.document.body.appendChild(dom.document.createElement("br").asInstanceOf[BR])
    dom.document.body.appendChild(canvas1)

    ctx1.beginPath()
    ctx1.moveTo(0,0)
    ctx1.lineTo(300,150)
    ctx1.stroke()

    val canvas2Label = dom.document.createElement("label").asInstanceOf[Label]
    canvas2Label.textContent = item2.toString

    val canvas2 = dom.document.createElement("canvas").asInstanceOf[Canvas]
    val ctx2 = canvas2.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    dom.document.body.appendChild(dom.document.createElement("br").asInstanceOf[BR])
    dom.document.body.appendChild(canvas2Label)
    dom.document.body.appendChild(dom.document.createElement("br").asInstanceOf[BR])
    dom.document.body.appendChild(canvas2)

    ctx2.beginPath()
    ctx2.moveTo(0,150)
    ctx2.lineTo(300,0)
    ctx2.stroke()
  }

  def setupUI(): Unit = {
    jQuery("#fire-plotting-button").click(plot _)
  }
}

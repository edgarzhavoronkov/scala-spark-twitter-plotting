package ru.spbau.mit

import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

/**
  * Created by edgar on 19.12.16.
  */
object Example extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  @JSExport
  def addClickedMessage(): Unit = {
    jQuery("body").append("<p>Click!</p>")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(addClickedMessage _)
    jQuery("body").append("<p>Hello World</p>")
  }
}

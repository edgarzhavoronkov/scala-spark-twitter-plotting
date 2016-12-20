package ru.spbau.mit.frontend.plotting.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.{a, className, div, h1, h3, href, onClick}
import japgolly.scalajs.react.vdom.svg.all._
import paths.mid.Bezier
import ru.spbau.mit.frontend.plotting.animate._




object logo {
  type Points = List[(Double, Double)]
  implicit val interpolate = new Interpolable[Points] {
    def mix(ps: Points, qs: Points, t: Double) =
      ps zip qs map { case ((a, b), (c, d)) => (a + (c - a) * t, b + (d - b) * t) }
  }
  val points: Points = List(
    (0.0, 50.0),
    (50.0, 70.0),
    (100.0, 40.0),
    (150.0, 30.0),
    (200.0, 60.0),
    (250.0, 80.0),
    (300.0, 50.0)
  )

  class Backend($: BackendScope[Unit, Points]) {
    def randomize(ps: Points) = ps map { case (x, y) => (x, y - 25 + 50 * js.Math.random()) }
    def onClick(e: ReactEventI) = $.animateModState(randomize, AnimateOptions(done = goBack))
    def goBack(u: Unit) = $.animateState(points, AnimateOptions(easing = Easing.easeOutElastic))
  }

  val Logo = ReactComponentB[Unit]("Paths.js")
    .initialState(points)
    .backend(new Backend(_))
    .render((_, ps, b) => {
      val line = Bezier(ps)
      val circles = line.path.points.map(p => circle(
        onClick ==> b.onClick,
        r := 5,
        cx := p(0),
        cy := p(1),
        stroke := "red",
        strokeWidth := 2,
        fill := "white"
      ))

      div(id := "logo", className := "col-md-12",
        h1(a(href := "https://github.com/andreaferretti/paths-scala-js", "Paths.scala.js")),
        h3("Generate SVG paths for geometric shapes"),
        svg(width := 800, height := 100,
          g(transform := "translate(100, 0)",
            path(d := line.path.print, stroke := "red", fill := "none"),
            circles
          )
        )
      )
    })
    .build
}
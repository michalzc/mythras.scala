package michalz.foundry.mythras.document.state

import scala.scalajs.js

class StateHolder[State <: js.Any](updatedState: => State):
  var state: Option[State] = None

  def get: State = state.getOrElse {
    val newState = updatedState
    state = Option(newState)
    newState
  }

  def invalidate(): Unit = state = None

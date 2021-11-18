package core.ai.minimax.evaluate

import core.model.Board
import core.model.Move

interface StateEvaluator {

    fun evaluate(board: Board, lastMove: Move): Int

}

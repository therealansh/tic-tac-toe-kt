package core.ai.minimax.findmoves

import core.model.Board
import core.model.Move
import core.model.Player

interface MovesFinder {
    fun getMoves(board: Board, player: Player): List<Move>
}

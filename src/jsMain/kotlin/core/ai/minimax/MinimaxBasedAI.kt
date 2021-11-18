package core.ai.minimax

import core.Constants
import core.Constants.MINIMAX_DEPTH_PROPERTY
import core.ai.AIPlayer
import core.ai.minimax.findmoves.MovesFinder
import core.model.Board
import core.model.CellType
import core.model.Move
import core.model.Player
import core.model.getCorrespondingCellType
import core.model.getRival
import core.winning.WinnerFinder

class MinimaxBasedAI(
    private val movesFinder: MovesFinder,
    private val winnerFinder: WinnerFinder,
    private val minimax: Minimax
) : AIPlayer {


    override fun nextMove(board: Board, player: Player, properties: Map<String, Any>): Move {

        val maxDepth: Int = properties[MINIMAX_DEPTH_PROPERTY] as Int

        val bestMoves: MutableList<Move> = ArrayList()
        var bestValue = Int.MIN_VALUE
        val possibleMoves = movesFinder.getMoves(board, player)

        for (currentMove in possibleMoves) {
            board.updateCellToPossibleValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
            var value: Int
            //                    int value = minimax.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            value = if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                board.updateCellValue(currentMove.y, currentMove.x, getCorrespondingCellType(player))
                return currentMove
            } else {
                minimax.minimax(board, 0, maxDepth, false, Constants.LOSE_STRATEGY_SCORE, Constants.WIN_STRATEGY_SCORE, getRival(player))
            }
            board.updateCellValue(currentMove.y, currentMove.x, CellType.EMPTY)
            if (value > bestValue) {
                bestMoves.clear()
                bestMoves.add(currentMove)
                bestValue = value
            } else if (value == bestValue) {
                bestMoves.add(currentMove)
            }
        }

        val move = bestMoves[(0 until bestMoves.size).random()]
        board.updateCellValue(move.y, move.x, getCorrespondingCellType(player))

        return move
    }


}

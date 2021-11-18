package core.ai.minimax.findmoves.evaluate

import core.model.Board
import core.model.Cell
import core.model.Move
import core.model.Player
import core.model.getCorrespondingCellType
import core.model.getRival
import core.winning.WinnerFinder

class MoveEvaluator(private val winnerFinder: WinnerFinder) {

    fun evaluate(board: Board, lastMove: Move): Int {
        val directions = winnerFinder.getDirections(board, lastMove)

        var score = 0
        for (direction in directions) {
            score += evaluateDirection(direction, lastMove.player)
        }

        return score
    }

    private fun evaluateDirection(direction: List<Cell>, player: Player):Int {
        var score = 0
        for(i in 0..direction.size - 5) {
            var you = 0
            var enemy = 0
            for(j in 0..4) {
                if (direction[i + j].cellType == getCorrespondingCellType(player)) {
                    you++
                } else if(direction[i + j].cellType == getCorrespondingCellType(getRival(player))) {
                    enemy++
                }
            }
            score += evalff(getSeq(you, enemy))
        }
        return score
    }

    private fun evalff(seq: Int): Int {
        return when (seq) {
            0 -> 7
            1 -> 35
            2 -> 800
            3 -> 15000
            4 -> 800000
            5 -> 1000000
            -1 -> 15
            -2 -> 400
            -3 -> 1800
            -4 -> 100000
            17 -> 0
            else ->  throw RuntimeException("unknown seq value to evaluate move: $seq")
        }
    }

    private fun getSeq(y: Int, e: Int): Int {
        if (y + e == 0) {
            return 0;
        }
        if (y != 0 && e == 0) {
            return y
        }
        if (y == 0 && e != 0) {
            return -e
        }
        if (y != 0 && e != 0) {
            return 17
        }
        return 0
    }

}

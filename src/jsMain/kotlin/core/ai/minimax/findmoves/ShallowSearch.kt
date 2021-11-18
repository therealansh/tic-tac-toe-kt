package core.ai.minimax.findmoves

import core.ai.minimax.findmoves.evaluate.MoveEvaluator
import core.model.Board
import core.model.Move
import core.model.Player
import kotlin.math.min

class ShallowSearchMovesFinder(
    private val baseMovesFinder: MovesFinder,
    private val moveEvaluator: MoveEvaluator,
    private val movesNumber: Int
) : MovesFinder {

    override fun getMoves(board: Board, player: Player): List<Move> {
        val moves = baseMovesFinder.getMoves(board, player)
        val map = HashMap<Int, MutableSet<Move>>()
//        val scoreToMovesMap = MultimapBuilder.treeKeys().arrayListValues().build<Int, Move>()
        for (currMove in moves) {
            val currScore = moveEvaluator.evaluate(board, currMove)
//            scoreToMovesMap.put(currScore, currMove)
            map.getOrPut(currScore) { mutableSetOf() }.add(currMove)
        }
        val ratedMovesList = map.keys.toList().flatMap { k:Int? -> map[k]!! }.reversed()
//        val ratedMovesList = scoreToMoves.keys
//            .flatMap { k: Int? -> scoreToMoves[k].stream() }
//            .reversed()
        return ratedMovesList.subList(0, min(movesNumber, ratedMovesList.size))
    }

}

package core.ai

import core.model.Board
import core.model.Move
import core.model.Player

interface AIPlayer {

    /**
     * @param board - describes current game state
     * @param player - whose turn to make a move
     * @param properties - additional properties needed for your algorithm
     */
    fun nextMove(board: Board, player: Player, properties: Map<String, Any>): Move

}

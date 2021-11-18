package core

import core.ai.AIPlayer
import core.ai.combinations.CombinationPatterns
import core.ai.combinations.CombinationsFinder
import core.ai.minimax.Minimax
import core.ai.minimax.MinimaxBasedAI
import core.ai.minimax.evaluate.TrickyStateEvaluator
import core.ai.minimax.findmoves.InRadiusMovesFinder
import core.ai.minimax.findmoves.ShallowSearchMovesFinder
import core.ai.minimax.findmoves.evaluate.MoveEvaluator
import core.winning.WinnerFinder


open class Cfg {

    open fun combinationPatterns(): CombinationPatterns {
        return CombinationPatterns()
    }

    open fun combinationsFinder(): CombinationsFinder {
        return CombinationsFinder(combinationPatterns())
    }

    open fun moveEvaluator(): MoveEvaluator {
        return MoveEvaluator(winnerFinder())
    }

    open fun inRadiusMovesFinder(): InRadiusMovesFinder {
        return InRadiusMovesFinder(Constants.AVAILABILITY_RADIUS)
    }


    open fun shallowSearchMovesFinder(): ShallowSearchMovesFinder {
        return ShallowSearchMovesFinder(inRadiusMovesFinder(), moveEvaluator(), 10)
    }

    open fun winnerFinder(): WinnerFinder {
        return WinnerFinder()
    }

    open fun minimax(): Minimax {
        return Minimax(shallowSearchMovesFinder(), TrickyStateEvaluator(combinationsFinder(), winnerFinder()), winnerFinder())
    }

    open fun aiPlayer(): AIPlayer {
        return MinimaxBasedAI(shallowSearchMovesFinder(), winnerFinder(), minimax())
    }
}

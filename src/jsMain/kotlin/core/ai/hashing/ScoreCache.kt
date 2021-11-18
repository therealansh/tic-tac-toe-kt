package core.ai.hashing

import core.model.HashedBoard

interface ScoreCache {

    fun getScore(board: HashedBoard): Int?
    fun putScore(board: HashedBoard, score: Int)

}

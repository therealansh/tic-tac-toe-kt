package core.ai.hashing

import core.model.Board
import core.model.Cell
import core.model.CellType
import kotlin.math.abs
import kotlin.random.Random

class ZobristHashing(height: Int, width: Int) {

    private val transpositionTable: Array<Array<LongArray>>

    companion object {
        private const val NON_EMPTY_CELL_TYPES_NUMBER = 3
    }

    init {
        transpositionTable = Array(height) { Array(width) { LongArray(NON_EMPTY_CELL_TYPES_NUMBER) } }
        for (i in 0 until height) {
            for (j in 0 until width) {
                for (p in 0 until NON_EMPTY_CELL_TYPES_NUMBER) {
                    transpositionTable[i][j][p] = abs(Random.nextLong())
                }
            }
        }
    }

    fun hash(board: Board): Long {
        var hash: Long = 0
        for (i in 0 until board.height) {
            for (j in 0 until board.width) {
                hash = updateHash(hash, Cell(i, j, board.getCellValue(i, j)))
            }
        }
        return hash
    }

    fun updateHash(currHash: Long, newCell: Cell, previousCell: Cell? = null): Long {
        return when (newCell.cellType) {
            CellType.CROSS -> currHash xor transpositionTable[newCell.y][newCell.x][0]
            CellType.NOUGHT -> currHash xor transpositionTable[newCell.y][newCell.x][1]
            CellType.EMPTY -> return if (previousCell != null) {
                    updateHash(currHash, previousCell)
                } else {
                    return currHash
                }
        }
    }

}

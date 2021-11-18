package core.model

import core.ai.hashing.ZobristHashing

class HashedBoard(height: Int, width: Int) : Board(height, width) {

    private val zobristHashing: ZobristHashing

    var boardHash: Long = 0
        private set

    init {
        this.zobristHashing = ZobristHashing(height, width)
    }

    override fun updateCellToPossibleValue(i: Int, j: Int, cellType: CellType) {
        super.updateCellToPossibleValue(i, j, cellType)

        boardHash = zobristHashing.updateHash(boardHash, Cell(i, j, cellType))
    }

    override fun updateCellValue(i: Int, j: Int, cellType: CellType) {
        super.updateCellValue(i, j, cellType)

        boardHash = zobristHashing.updateHash(boardHash, Cell(i, j, cellType))
    }

}

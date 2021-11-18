package core.model

import core.model.CellType
import core.model.Player

fun getRival(player: Player): Player =
        when (player) {
            Player.CROSSES -> Player.NOUGHTS
            Player.NOUGHTS -> Player.CROSSES
        }

fun getCorrespondingCellType(player: Player): CellType =
        when (player) {
            Player.CROSSES -> CellType.CROSS
            Player.NOUGHTS -> CellType.NOUGHT
        }

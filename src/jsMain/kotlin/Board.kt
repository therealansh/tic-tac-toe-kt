import core.model.Board
import kotlinx.html.style
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import kotlinext.js.js

fun RBuilder.board(board: Board, onClickFunction: (Int, Int) -> (Event) -> Unit) {
    div {
        for (row in 0 until 10) {
            div {
                attrs.style = js {
                    display="flex"
                    flexDirection="row"
                }
                for (column in 0 until 10) {
                    renderSquare(row, column, board, onClickFunction)
                }
            }
        }
    }
}

private fun RBuilder.renderSquare(row: Int, column: Int, board: Board,
                                  onClickFunction: (Int, Int) -> (Event) -> Unit) {
    square(board.getCellValue(row,column), onClickFunction(row, column))
}

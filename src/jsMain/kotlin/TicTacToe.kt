import core.ai.AIPlayer
import core.model.Move
import core.model.Player
import core.Cfg
import core.Constants
import core.model.Board
import core.model.CellType
import core.winning.WinnerFinder
import kotlinx.browser.window
import kotlinx.html.InputType
import kotlinx.html.colorInput
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import kotlinext.js.js


external interface TicTacToeProps:RProps

external interface TicTacToeState:RState{
    var history: MutableList<HistoryState>
    var status: String
    var difficulty:Int
    var gameEnd: Boolean
}

class HistoryState(var board: Board, var nextPlayer: Player = Player.CROSSES)

class TicTacToe(props: TicTacToeProps): RComponent<TicTacToeProps, TicTacToeState>(props){

    override fun componentWillMount() {
        setState {
            val board = Board(10,10)
            history = mutableListOf(HistoryState(board))
            status = "Crosses Turn"
            gameEnd=false
            difficulty=1
        }
    }

    override fun componentWillReceiveProps(nextProps: TicTacToeProps) {
        val board = Board(10,10)
        state.history = mutableListOf(HistoryState(board))
    }

    override fun RBuilder.render() {
        div {
            attrs.style = js {
                display="flex"
                justifyContent="center"
                alignItems="center"
                flexDirection="column"
                height="100vh"
                fontFamily="Poppins"
            }

            h1 {
                attrs.style = js {
                    fontSize="6vh"
                }
                +"Welcome to "
                span {
                    attrs.style = js {
                        color="#26A69A"
                    }
                    +"Tic-Tac-Toe-kt"
                }
            }

            h3 {
                attrs.style = js {
                    fontSize="20px"
                }
                +"10x10 Unbeatable AI Tic-Tac-Toe"
            }


            div{
                attrs.style = js {
                    display="flex"
                    justifyContent="space-evenly"
                    alignItems="center"
                    flexDirection="row"
                    height="100vh"
                    width="100vw"
                }
                //Board UI
                div{
                    board(state.history.last().board){
                            r,c -> {handleClick(r,c)}
                    }
                }

                //Board Settings
                div{
                    attrs.style = js {
                        display="flex"
                        flexDirection="column"
                        justifyContent="space-evenly"
                        alignItems="center"
                        height="25vh"
                    }
                    if(state.gameEnd) {
                        div {
                            +"Winner: ${state.status}"
                            attrs.style = js {
                                fontSize = "32px"
                            }
                        }
                    }
                    div{
                        attrs.style = js {
                            textAlign="center"
                        }
                        h3{
                            +"Difficulty: ${state.difficulty}"
                        }
                        div {
                            attrs.style = js{
                                flexDirection="row"
                                width="100%"
                                justifyContent="space-evenly"
                                display="flex"
                            }
                            button {
                                attrs.style = js {
                                   display="block"
                                   height="50px"
                                    width="50px"
                                    borderRadius="50%"
                                    border="1px solid #26A69A"
                                    background="none"
                                    fontSize="30px"
                                    marginRight="4px"
                                }
                                +"-"
                                attrs.onClickFunction = {decDiff()}
                            }
                            button {
                                attrs.style = js {
                                    display="block"
                                    height="50px"
                                    width="50px"
                                    borderRadius="50%"
                                    border="1px solid #26A69A"
                                    background="none"
                                    backgroundColor="#26A69A"
                                    marginLeft="4px"
                                    fontSize="30px"
                                    color="white"
                                }
                                +"+"
                                attrs.onClickFunction = {incDiff()}
                            }
                        }
                    }
                    button {
                        +"REINIT"
                        attrs.onClickFunction = {resetGame()}
                        attrs.style = js {
                            boxShadow = "0.3em 0.3em 0 0 #26A69A, inset 0.3em 0.3em 0 0 #26A69A"
                            background="none"
                            border="2px solid #26A69A"
                            lineHeight = "1"
                            margin="0.5em"
                            padding="1em 2em"
                            fontSize="18px"
                        }
                    }
                }
            }
        }
    }

    private fun decDiff() {
        if(state.difficulty-1<1) {
            window.alert("Difficulty cannot be less than 1")
            return
        }
        return setState{
            difficulty=state.difficulty-1
        }
    }

    private fun incDiff() {
        if(state.difficulty+1>6) {
            window.alert("Difficulty cannot be more than 6")
            return
        }
        return setState{
            difficulty=state.difficulty+1
        }
    }

    private fun resetGame() {
        setState{
            val board = Board(10,10)
            history = mutableListOf(HistoryState(board))
            status = "Crosses Turn"
            gameEnd=false
        }
        window.alert("Reinitialized board with the specified difficulty.")
    }


    private val winnerFinder: WinnerFinder = WinnerFinder()
    private val aiPlayer: AIPlayer = Cfg().aiPlayer()


    private fun handleClick(row: Int, column: Int) {
        if (!state.gameEnd){
            val current = state.history.last()
            val currentPlayer = current.nextPlayer
            if(current.board.getCellValue(row,column)!=CellType.EMPTY) return
            current.board.updateCellValue(row,column,CellType.CROSS)
            val crossWin = winnerFinder.isMoveLeadToWin(current.board, Move(row, column, Player.CROSSES))
            if (crossWin) {
                setState {
                    val newState = HistoryState(current.board, currentPlayer)
                    history.add(newState)
                    status = "Crosses WIN"
                    gameEnd=true
                }
                return
            }
            val props: MutableMap<String, Any> = HashMap()
            props[Constants.MINIMAX_DEPTH_PROPERTY] = state.difficulty
            val aiMove: Move = aiPlayer.nextMove(current.board, Player.NOUGHTS, props)
            println(aiMove)
            current.board.updateCellValue(aiMove.y, aiMove.x, CellType.NOUGHT)
            val noughtWin = winnerFinder.isMoveLeadToWin(current.board, aiMove)
            if(noughtWin){
                setState {
                    val newState = HistoryState(current.board, currentPlayer)
                    history.add(newState)
                    status = "Noughts WIN"
                    gameEnd=true
                }
                return
            }
            setState {
                val newState = HistoryState(current.board, currentPlayer)
                history.add(newState)
            }
        }
        return
    }
}

fun RBuilder.ticTacToe(){
    child(TicTacToe::class){}
}

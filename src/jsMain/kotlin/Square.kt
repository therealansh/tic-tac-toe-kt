import core.model.CellType
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import org.w3c.dom.events.Event
import react.RBuilder
import react.dom.button
import react.dom.div
import kotlinext.js.js


fun RBuilder.square(player: CellType, onClickFunction: (Event) -> Unit) {
     button {
        attrs.style = js {
            textAlign = "center"
            background="#fff"
            border="1px solid #999"
            fontSize="24px"
            height = "50px"
            width="50px"
            marginTop="-1px"
            marginRight="-1px"
            padding="0"
            outline="none"
        }
        +player.symbol.toString()
        attrs.onClickFunction = onClickFunction
    }
}

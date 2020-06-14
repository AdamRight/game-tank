package tea.game.tank.model

import tea.game.tank.Config
import tea.game.tank.engine.Painter

class Water(override val x: Int, override val y: Int) : View {
    //宽高
    override var width: Int = Config.block
    override var height: Int = Config.block

    //显示行为
    override fun draw() {
        Painter.drawImage("img/water.gif", x, y)
    }
}
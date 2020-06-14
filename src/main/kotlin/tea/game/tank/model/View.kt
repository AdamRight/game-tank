package tea.game.tank.model

interface View {
    //可以定义属性让实现类去实现
    //位置
    val x: Int
    val y: Int

    //宽高
    val width: Int
    val height: Int

    //显示
    fun draw()

    fun checkCollision(x1: Int, y1: Int, w1: Int, h1: Int
                       , x2: Int, y2: Int, w2: Int, h2: Int): Boolean {
        //两个物体的x,y,w,h的比较
        return when {
            y2 + h2 <= y1 -> //如果 阻挡物 在运动物的上方时 ，不碰撞
                false
            y1 + h1 <= y2 -> //如果 阻挡物 在运动物的下方时 ，不碰撞
                false
            x2 + w2 <= x1 -> //如果 阻挡物 在运动物的左方时 ，不碰撞
                false
            else -> x1 + w1 > x2
        }
    }
}
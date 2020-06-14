package tea.game.tank

import tea.game.tank.engine.Painter
import tea.game.tank.engine.Window
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import tea.game.tank.enums.Direction
import tea.game.tank.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow : Window(
    title = "坦克大战1.0"
    , icon = "icon/logo.png"
    , width = Config.gameWidth
    , height = Config.gameHeight
) {

    //线程安全的集合
    private val views = CopyOnWriteArrayList<View>()
    // 晚点创建
    private lateinit var tank: Tank
    //游戏是否结束
    private var gameOver: Boolean = false
    /**
     * 创建
     */
    override fun onCreate() {
        //地图
        val resourceAsStream = javaClass.getResourceAsStream("/map/1.map")
        val reader = BufferedReader(InputStreamReader(resourceAsStream,"utf-8"))
        //读取文件中的行
        val lines = reader.readLines()
        //循环遍历
        var lineNum = 0
        lines.forEach { line ->
            var columnNum = 0
            // it  [空砖空砖空砖空砖空砖空砖空]
            line.toCharArray().forEach { column ->
                when (column) {
                    '砖' -> views.add(Wall(columnNum * Config.block, lineNum * Config.block))
                    '铁' -> views.add(Steel(columnNum * Config.block, lineNum * Config.block))
                    '草' -> views.add(Grass(columnNum * Config.block, lineNum * Config.block))
                    '水' -> views.add(Water(columnNum * Config.block, lineNum * Config.block))
                    //'敌' -> enemyBornLocation.add(Pair(columnNum * Config.block, lineNum * Config.block))
                }
                columnNum++
            }
            lineNum++
        }


        //添加我方的坦克
        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)

        //添加大本营
        //views.add(Camp(Config.gameWidth / 2 - Config.block, Config.gameHeight - 96))
    }

    /**
     * 不停的渲染
     */
    override fun onDisplay() {
        //绘制地图中的元素
        views.forEach {
            it.draw()
        }
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
    }

    /**
     * 按键响应
     */
    override fun onKeyPressed(event: KeyEvent) {
        //用户操作时
        if (!gameOver) {
            when (event.code) {
                KeyCode.W -> {
                    tank.move(Direction.UP)
                }
                KeyCode.S -> {
                    tank.move(Direction.DOWN)
                }
                KeyCode.A -> {
                    tank.move(Direction.LEFT)
                }
                KeyCode.D -> {
                    tank.move(Direction.RIGHT)
                }
                KeyCode.ENTER -> {
                    //发射子弹
                    //val bullet = tank.shot()
                    //交给views管理
                    //views.add(bullet)
                }
            }
        }
    }
}
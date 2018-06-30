package com.snake;

import java.util.LinkedList;
import java.util.Random;

public class Food extends Block {

    public int row;
    public int col;
    private Random rand;
    // 表示食物所在的行列

    public static final int Row = Config.ROW;
    public static final int Column = Config.COL;
    // 从Configure文件中读取的游戏行列

    Food() {
        rand = new Random();
        randomPos();
        // 随机设置这个对象的行列变量
    }

    /**
     * 获取蛇的snakeBody链表，让食物与蛇身不重叠
     *
     * @param snakeBody
     *            表示蛇身体的链表
     * @return 返回这个类实例化的对象本身
     */
    public Food getSnake(LinkedList<Block> snakeBody) {
        while (checkSame(snakeBody))
            randomPos();
        // 如果发现食物的位置和蛇身体重叠，则重新随机食物的位置
        return this;
        // 返回这个对象本身，为创建实例时带来方便
    }

    /**
     * 检查蛇身体链表中是否有一块与当前食物坐标相同
     *
     * @param snakeBody
     *            表示蛇身体的链表
     * @return 如果有重复返回true
     */
    private boolean checkSame(LinkedList<Block> snakeBody) {
        for (Block sp : snakeBody)
            if (sp.row == this.row && sp.col == this.col)
                return true;
        // 循环遍历是否有重复
        return false;
    }

    /**
     * 随机该对象的行和列变量
     */
    private void randomPos() {
        this.row = rand.nextInt(Row);
        this.col = rand.nextInt(Column);
    }
}

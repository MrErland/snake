package com.snake;

public class Block {

    public int col;
    public int row;
    // 位置坐标
    // 设置为public方便调用

    Block(int row, int col) {
        this.col = col;
        this.row = row;
    }

    Block() {
        col = 0;
        row = 0;
    }
}

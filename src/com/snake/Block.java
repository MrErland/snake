package com.snake;

public class Block
{
    private int col;
    private int row;

    Block(int row, int col)
    {
        this.col = col;
        this.row = row;
    }

    Block()
    {
        col = 0;
        row = 0;
    }

    public int getCol()
    {
        return this.col;
    }

    public int getRow()
    {
        return this.row;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }
}

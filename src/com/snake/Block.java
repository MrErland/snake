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

    protected int getCol()
    {
        return this.col;
    }

    protected int getRow()
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

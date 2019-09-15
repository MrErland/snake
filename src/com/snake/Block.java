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

    @Override public boolean equals(Object object)
    {
        if (object == null) return false;
        if (object instanceof Block)
        {
            return ((Block) object).col == this.col && ((Block) object).row == this.row;
        }
        return false;
    }

    @Override public int hashCode()
    {
        return col ^ (col >> 16) + row ^ (row >> 16) + super.hashCode();
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

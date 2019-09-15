package com.snake;

import java.util.LinkedList;
import java.util.Random;

public class Food extends Block
{
    private Random rand = new Random(System.nanoTime());
    private static final int Row = Config.ROW;
    private static final int Col = Config.COL;

    Food()
    {
        randomPos();
    }

    public Food getSnake(LinkedList<Block> snakeBody)
    {
        while (checkSame(snakeBody))
        {
            randomPos();
        }
        return this;
    }

    private boolean checkSame(LinkedList<Block> snakeBody)
    {
        for (Block sp : snakeBody)
        {
            if (sp.getRow() == super.getRow() && sp.getCol() == super.getCol())
            {
                return true;
            }
        }
        return false;
    }

    private void randomPos()
    {
        super.setRow(rand.nextInt(Row));
        super.setCol(rand.nextInt(Col));
    }

    @Override public int getRow()
    {
        return super.getRow();
    }

    @Override public int getCol()
    {
        return super.getCol();
    }
}

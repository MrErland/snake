package com.snake;

import java.util.LinkedList;
import java.util.Random;

public class Food extends Block
{
    private int row;
    private int col;
    private Random rand;
    private static final int Row = Config.ROW;
    private static final int Column = Config.COL;

    Food()
    {
        rand = new Random();
        randomPos();
    }

    public Food getSnake(LinkedList<Block> snakeBody)
    {
        while (checkSame(snakeBody))
            randomPos();
        return this;
    }

    private boolean checkSame(LinkedList<Block> snakeBody)
    {
        for (Block sp : snakeBody)
        {
            if (sp.getRow() == this.row && sp.getCol() == this.col)
            {
                return true;
            }
        }
        return false;
    }

    private void randomPos()
    {
        this.row = rand.nextInt(Row);
        this.col = rand.nextInt(Column);
    }
}

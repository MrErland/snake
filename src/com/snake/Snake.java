package com.snake;

import java.util.LinkedList;

public class Snake
{
    private Direction snakeDir;
    private Direction moveDir;
    private Food food;
    private LinkedList<Block> snakeBody;
    private static final int Row = Config.ROW;
    private static final int Col = Config.COL;

    Snake()
    {
        snakeBody = new LinkedList<Block>();
        reset();
    }

    public Direction getSnakeDir()
    {
        return snakeDir;
    }

    public void setSnakeDir(Direction snakeDir)
    {
        this.snakeDir = snakeDir;
    }

    public LinkedList<Block> getSnakeBody()
    {
        return snakeBody;
    }

    public Food getFood()
    {
        return food;
    }

    public void setFood(Food food)
    {
        this.food = food;
    }

    public void setMoveDir(Direction dir)
    {
        this.moveDir = dir;
    }

    private void reset()
    {
        snakeBody.clear();
        Block beginPos = null;
        setMoveDir(null);
        do {
            beginPos = RandomBlock();
        } while (beginPos.getRow() + 3 > Row || beginPos.getCol() + 3 > Col);

        snakeBody.add(beginPos);
        snakeBody.add(new Block(beginPos.getRow() + 1, beginPos.getCol()));
        snakeBody.add(new Block(beginPos.getRow() + 2, beginPos.getCol()));
        setSnakeDir(Direction.UP);
    }

    private Block RandomBlock()
    {

        int randomRow = (int) (Math.random() * Row);
        int randomCol = (int) (Math.random() * Col);
        return new Block(randomRow, randomCol);
    }

    public void snakeMove()
    {
        int addRow = snakeBody.getFirst().getRow();
        int addCol = snakeBody.getFirst().getCol();

        Direction up = Direction.UP;
        Direction down = Direction.DOWN;
        Direction left = Direction.LEFT;
        Direction right = Direction.RIGHT;

        if ((moveDir != null) && !((snakeDir == up && moveDir == down) || (snakeDir == down && moveDir == up)
                || (snakeDir == left && moveDir == right) || (snakeDir == right && moveDir == left)))
        {
            snakeDir = moveDir;
        }

        switch (snakeDir)
        {
            case UP:
                addRow--;
                break;
            case DOWN:
                addRow++;
                break;
            case LEFT:
                addCol--;
                break;
            case RIGHT:
                addCol++;
                break;
        }

        Block addPos = new Block(addRow, addCol);
        if (!isFood(addPos))
        {
            snakeBody.removeLast();
        }
        else
        {
            setFood(new Food().getSnake(snakeBody));
        }
        if (isCollision(addPos))
        {
            reset();
        }
        else
        {
            snakeBody.addFirst(addPos);
        }
    }

    private boolean isFood(Block addPos)
    {
        if (food.getRow() == addPos.getRow() && food.getCol() == addPos.getCol())
        {
            return true;
        }
        return false;
    }

    private boolean isCollision(Block addPos)
    {
        if (addPos.getRow() < 0 || addPos.getRow() > Row - 1 || addPos.getCol() < 0 || addPos.getCol() > Col - 1)
        {
            return true;
        }
        for (Block sp : snakeBody)
        {
            if ((sp.getRow() == addPos.getRow()) && (sp.getCol() == addPos.getCol()))
            {
                return true;
            }
        }
        return false;
    }
}
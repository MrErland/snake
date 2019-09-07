package com.snake;

import java.util.*;

public abstract class Policy implements AutoMove
{
    public Direction moveTo(Snake snk)
    {
        Block head = snk.getSnakeBody().getFirst();
        Direction dir = snk.getSnakeDir();
        Block goal = snk.getFood();
        List<Block> body = snk.getSnakeBody();

        HashMap<Block, Integer> startPoints = getStartPoints(head, dir);
        return getDirectionFromBlock(coreMethods(startPoints, goal, body), head);
    }

    /*  Notice:
     *  subclass need realize this function.
     *  return the next block move to goal
     *  @param startPoint: the next <possible blocks, default distance>
     *  @param goal: the food
     *  @param forbid: the body of snake
     */
    protected abstract Block coreMethods(HashMap<Block, Integer> startPoints, Block goal, List<Block> forbid);

    private Direction getDirectionFromBlock(Block block, Block head)
    {
        if (block.getCol() - head.getCol() == 1 && block.getRow() == head.getRow())
        {
            return Direction.RIGHT;
        }
        if (block.getCol() - head.getCol() == -1 && block.getRow() == head.getRow())
        {
            return Direction.LEFT;
        }
        if (block.getCol() == head.getCol() && block.getRow() - head.getRow() == 1)
        {
            return Direction.DOWN;
        }
        if (block.getCol() == head.getCol() && block.getRow() - head.getRow() == -1)
        {
            return Direction.UP;
        }
        return null;
    }

    private boolean checkBlock(Block block)
    {
        return block.getRow() >= 0 && block.getRow() < Config.ROW
                && block.getCol() >= 0 && block.getCol() < Config.COL;
    }

    private HashMap<Block, Integer> getStartPoints(Block head, Direction dir)
    {
        HashMap<Block, Integer> startPoint = new HashMap<>();
        Block tmp = null;
        switch (dir)
        {
            case UP:
                tmp = new Block(head.getRow() - 1, head.getCol());
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow(), head.getCol() - 1);
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow(), head.getCol() + 1);
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
            case DOWN:
                tmp = new Block(head.getRow() + 1, head.getCol());
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow(), head.getCol() - 1);
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow(), head.getCol() + 1);
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
            case LEFT:
                tmp = new Block(head.getRow() - 1, head.getCol());
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow() + 1, head.getCol());
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow(), head.getCol() - 1);
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
            case RIGHT:
                tmp = new Block(head.getRow() - 1, head.getCol());
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow() + 1, head.getCol());
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
                tmp = new Block(head.getRow(), head.getCol() + 1);
                if (checkBlock(tmp))
                {
                    startPoint.put(tmp, Integer.MAX_VALUE);
                }
        }
        return startPoint;
    }
}

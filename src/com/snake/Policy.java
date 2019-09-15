package com.snake;

import java.util.*;

public abstract class Policy implements AutoMove
{
    // default distance.
    // it equals MAX_VALUE - 1, because snake's body distance will be set MAX_VALUE.
    private final int MAX_DISTANCE_INIT = Integer.MAX_VALUE - 1;

    public Direction moveTo(Snake snk)
    {
        Block head = snk.getSnakeBody().getFirst();
        Block goal = snk.getFood();
        List<Block> body = snk.getSnakeBody();

        HashMap<Block, Integer> startPoints = getStartPoints(head, snk.getSnakeBody());
        return getDirectionFromBlock(coreMethods(startPoints, goal, body), head);
    }

    /*  Notice:
     *  subclass need realize this function.
     *  return the next block move to goal
     *  @param startPoint: <the next possible blocks, default distance>
     *  @param goal: the food
     *  @param forbid: the body of snake
     */
    protected abstract Block coreMethods(HashMap<Block, Integer> startPoints, Block goal, List<Block> forbid);

    private Direction getDirectionFromBlock(Block block, Block head)
    {
        if (block == null)
        {
            return Enums.random(Direction.class);  // random return a direction
        }
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

    private boolean checkForbid(Block block, List<Block> forbid)
    {
        for (Block b : forbid)
        {
            if (block.equals(b))
            {
                return false;
            }
        }
        return true;
    }

    private HashMap<Block, Integer> getStartPoints(Block head, List<Block> forbid)
    {
        HashMap<Block, Integer> startPoint = new HashMap<>();
        Block tmp;

        tmp = new Block(head.getRow() - 1, head.getCol());
        if (checkBlock(tmp) && checkForbid(tmp, forbid))
        {
            startPoint.put(tmp, MAX_DISTANCE_INIT);
        }
        tmp = new Block(head.getRow() + 1, head.getCol());
        if (checkBlock(tmp) && checkForbid(tmp, forbid))
        {
            startPoint.put(tmp, MAX_DISTANCE_INIT);
        }
        tmp = new Block(head.getRow(), head.getCol() - 1);
        if (checkBlock(tmp) && checkForbid(tmp, forbid))
        {
            startPoint.put(tmp, MAX_DISTANCE_INIT);
        }
        tmp = new Block(head.getRow(), head.getCol() + 1);
        if (checkBlock(tmp) && checkForbid(tmp, forbid))
        {
            startPoint.put(tmp, MAX_DISTANCE_INIT);
        }
        return startPoint;
    }
}

package com.snake;

import java.util.*;

public class BfsPolicy extends Policy
{
    protected Block coreMethods(HashMap<Block, Integer> startPoints, Block goal, List<Block> forbid)
    {
        for (Block block : startPoints.keySet())
        {
            startPoints.put(block, distance(block, goal));
        }
        for (Block block : startPoints.keySet())
        {
            for (Block b : forbid)
            {
                if (block.equals(b))
                {
                    startPoints.put(block, Integer.MAX_VALUE);
                    break;
                }
            }
        }
        ArrayList<Map.Entry<Block, Integer>> distance = new ArrayList(startPoints.entrySet());
        distance.sort(Comparator.comparingInt(Map.Entry::getValue));
        return distance.get(0).getKey();
    }

    private int distance(Block block, Block goal)
    {
        return Math.abs(block.getCol() - goal.getCol()) + Math.abs(block.getRow() - goal.getRow());
    }
}

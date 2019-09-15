package com.snake;

import java.util.*;

public class BfsPolicy extends Policy
{
    protected Block coreMethods(HashMap<Block, Integer> startPoints, Block goal, List<Block> forbid)
    {
        for (Block block : startPoints.keySet())
        {
            startPoints.put(block, bfsDistance(block, goal, forbid));
        }
        ArrayList<Map.Entry<Block, Integer>> distance = new ArrayList(startPoints.entrySet());
        distance.sort(Comparator.comparingInt(Map.Entry::getValue));
        return distance.size() != 0 ? distance.get(0).getKey() : null;
    }

    private int bfsDistance(Block start, Block goal, List<Block> forbid)
    {
        // init map with forbidden points
        boolean [][] mark = new boolean[Config.ROW][Config.COL];
        for (Block block : forbid)
        {
            mark[block.getRow()][block.getCol()] = true;
        }

        Block border = new Block(-1, -1);
        Queue<Block> queue = new LinkedList<>();
        queue.offer(start);
        queue.offer(border);
        mark[start.getRow()][start.getCol()] = true;
        int dis = 0;
        while (!queue.isEmpty())
        {
            if (border.equals(queue.peek()))
            {
                dis += 1;
                queue.poll();
                if (queue.isEmpty())
                {
                    break;
                }
                else
                {
                    queue.offer(border);
                }
            }
            if (goal.equals(queue.peek()))
            {
                return dis;
            }
            ArrayList<Block> next = nearBlocks(queue.poll(), mark);
            queue.addAll(next);
        }
        return Integer.MAX_VALUE;
    }

    private ArrayList<Block> nearBlocks(Block now, boolean[][] mark)
    {
        ArrayList<Block> res = new ArrayList<>();
        if (now.getRow() > 0)
        {
            if (!mark[now.getRow() - 1][now.getCol()])
            {
                res.add(new Block(now.getRow() - 1, now.getCol()));
                mark[now.getRow() - 1][now.getCol()] = true;
            }
        }
        if (now.getRow() < Config.ROW - 1)
        {
            if (!mark[now.getRow() + 1][now.getCol()])
            {
                res.add(new Block(now.getRow() + 1, now.getCol()));
                mark[now.getRow() + 1][now.getCol()] = true;
            }
        }
        if (now.getCol() > 0)
        {
            if (!mark[now.getRow()][now.getCol() - 1])
            {
                res.add(new Block(now.getRow(), now.getCol() - 1));
                mark[now.getRow()][now.getCol() - 1] = true;
            }
        }
        if (now.getCol() < Config.COL - 1)
        {
            if (!mark[now.getRow()][now.getCol() + 1])
            {
                res.add(new Block(now.getRow(), now.getCol() + 1));
                mark[now.getRow()][now.getCol() + 1] = true;
            }
        }
        return res;
    }
}

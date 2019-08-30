package com.snake;

import java.awt.EventQueue;
import java.awt.KeyEventPostProcessor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Draw
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new BoardFrame();
            frame.setTitle("AI Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BoardFrame extends JFrame
{
    private Snake snk;
    public static final int INTERVAL = Config.INTERVAL;

    public BoardFrame()
    {
        snk = new Snake();
        snk.setFood(new Food().getSnake(snk.getSnakeBody()));
        final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        new Thread(() -> {
            while (true)
            {
                BoardComponent bc = new BoardComponent();
                bc.setSnake(snk);
                add(bc);

                MyKeyEventPostProcessor mke = new MyKeyEventPostProcessor();
                mke.setSnk(snk);
                manager.addKeyEventPostProcessor(mke);
                try
                {
                    Thread.sleep(INTERVAL);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                snk.snakeMove();
                pack();
            }
        }).start();
    }
}

class MyKeyEventPostProcessor implements KeyEventPostProcessor
{
    private Snake snk;

    public boolean postProcessKeyEvent(KeyEvent e)
    {
        Direction dir = null;
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                dir = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                dir = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                dir = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                dir = Direction.RIGHT;
                break;
        }

        if (dir != null)
        {
            snk.setMoveDir(dir);
        }
        return true;
    }

    public void setSnk(Snake snk)
    {
        this.snk = snk;
    }
}

class BoardComponent extends JComponent
{
    private static final int Width = Config.WIDTH;
    private static final int Height = Config.HEIGTH;
    private static final int TileWidth = Config.TILE_WIDTH;
    private static final int TileHeight = Config.TILE_HEIGHT;
    private static final int Row = Config.ROW;
    private static final int Column = Config.COL;
    private static final int XOffset = (Width - Column * TileWidth) / 2;
    private static final int YOffset = (Height - Row * TileHeight) / 2;
    private Snake snk;

    public void setSnake(Snake snk)
    {
        this.snk = snk;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        drawDecoration(g);
        drawFill(g);
    }

    private void drawFill(Graphics g)
    {
        g.setColor(Color.BLACK);
        for (Block sp : snk.getSnakeBody())
        {
            g.fillRect(sp.getCol() * TileWidth + XOffset, sp.getRow() * TileHeight + YOffset, TileWidth, TileHeight);
        }
        Food fd = snk.getFood();
        g.setColor(Color.RED);
        g.fillRect(fd.getCol() * TileWidth + XOffset, fd.getRow() * TileHeight + YOffset, TileWidth, TileHeight);
    }

    private void drawDecoration(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.drawRect(XOffset, YOffset, Column * TileWidth, Row * TileHeight);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(Width, Height);
    }
}
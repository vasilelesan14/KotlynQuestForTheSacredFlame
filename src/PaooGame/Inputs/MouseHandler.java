package PaooGame.Inputs;

import PaooGame.GameStates.GameState;
import PaooGame.GameWindow.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener
{
    private GamePanel panel;

    public MouseHandler(GamePanel panel)
    {
        this.panel = panel;
    }

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e)
    {
        switch (GameState.state)
        {
            case MENU:
                panel.getGame().getMenu().mouseClicked(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().mouseClicked(e);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        switch (GameState.state)
        {
            case MENU:
                panel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().mousePressed(e);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        switch (GameState.state)
        {
            case MENU:
                panel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().mouseReleased(e);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    // MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        switch (GameState.state)
        {
            case MENU:
                panel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().mouseMoved(e);
                break;
        }

    }
}

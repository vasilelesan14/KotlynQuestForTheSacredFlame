package PaooGame.Inputs;

import PaooGame.GameStates.GameState;
import PaooGame.GameWindow.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static PaooGame.Utilities.Constants.Directions.*;


public class KeyHandler implements KeyListener
{
    private GamePanel panel;
    public KeyHandler(GamePanel panel)
    {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (GameState.state)
        {
            case MENU:
                panel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

        switch (GameState.state)
        {
            case MENU:
                panel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().keyReleased(e);
        }
    }
}

package PaooGame.UserInterface.Overlays;

import PaooGame.GameStates.GameState;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.UI.Menu.*;

public class GameOverOverlay
{
    private Playing playing;
    public PauseButton homeButton = PauseButton.homeButton;
    public PauseButton restartButton = PauseButton.restartButton;

    public GameOverOverlay(Playing playing)
    {
        this.playing = playing;
    }

    public void draw(Graphics g)
    {
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0,VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT);

        g.drawImage(Assets.game_over_panel,EMPTY_MENU_X ,EMPTY_MENU_Y + 150, EMPTY_MENU_WIDTH, EMPTY_MENU_HEIGHT - 150,null );
        g.drawImage(Assets.game_over_header,(EMPTY_MENU_X-110) ,EMPTY_MENU_Y, 582, 128,null );

        restartButton.draw(g);
        homeButton.draw(g);

    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            playing.resetPlaying();
            playing.getPlayer().setCurrentHealth(100);
            GameState.state = GameState.MENU;
        }
    }

    private boolean isIn(MouseEvent e, PauseButton b)
    {
        return b.getBounds().contains(e.getX(),e.getY());
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        if(isIn(e,homeButton))
            homeButton.setMousePressed(true);
        if(isIn(e,restartButton))
            restartButton.setMousePressed(true);


    }
    public void mouseReleased(MouseEvent e)
    {
        if(isIn(e,homeButton))
        {
            if (homeButton.isMousePressed())
            {
                System.out.println("Buton HOME apasat!");
                GameState.state = GameState.MENU;
                playing.resetPlaying();
                playing.getPlayer().setCurrentHealth(100);
            }
        }
        if(isIn(e,restartButton))
        {
            if (restartButton.isMousePressed())
            {
                System.out.println("Buton NEXT LEVEL apasat!");
                playing.resetPlaying();
                playing.getPlayer().setCurrentHealth(100);
            }
        }
        homeButton.resetBooleans();
        restartButton.resetBooleans();

    }



}

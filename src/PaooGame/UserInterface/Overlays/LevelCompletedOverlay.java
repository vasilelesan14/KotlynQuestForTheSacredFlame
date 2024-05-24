package PaooGame.UserInterface.Overlays;

import PaooGame.GameStates.GameState;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import java.awt.*;
import java.awt.event.MouseEvent;

import static PaooGame.DatabaseManager.InsertGet.SaveIntoDatabase;
import static PaooGame.Entities.Player.GetTorchNumber;
import static PaooGame.Levels.LevelManager.GetLevelIndex;
import static PaooGame.Utilities.Constants.UI.Menu.*;

public class LevelCompletedOverlay
{
    private Playing playing;
    public PauseButton homeButton = PauseButton.homeButton;
    public PauseButton next_levelButton = PauseButton.next_levelButton;
    public PauseButton saveButton = PauseButton.saveButton;

    public LevelCompletedOverlay(Playing playing)
    {
        this.playing = playing;
    }
    public void update()
    {

    }
    public void draw(Graphics g)
    {
        g.drawImage(Assets.level_completed_panel, EMPTY_MENU_X ,EMPTY_MENU_Y + 55, EMPTY_MENU_WIDTH, EMPTY_MENU_HEIGHT - 150,null);
        homeButton.draw(g);
        next_levelButton.draw(g);
        saveButton.draw(g);
    }

    private boolean isIn(MouseEvent e, PauseButton b)
    {
        return b.getBounds().contains(e.getX(),e.getY());
    }

    public void mouseMoved(MouseEvent e)
    {

    }
    public void mouseReleased(MouseEvent e)
    {
        if(isIn(e,homeButton))
        {
            if (homeButton.isMousePressed())
            {
                System.out.println("Buton HOME apasat!");
                GameState.state = GameState.MENU;
            }
        }
        if(isIn(e,next_levelButton))
        {
            if (next_levelButton.isMousePressed())
            {
                System.out.println("Buton NEXT LEVEL apasat!");
                playing.loadNextLevel();
            }
        }
        if(isIn(e,saveButton))
        {
            if (saveButton.isMousePressed())
            {
                System.out.println("Buton SAVE apasat!");
                int levelIndex = GetLevelIndex();
                int currentHealth = playing.getPlayer().getCurrentHealth();
                int torchNumber = GetTorchNumber();
                float xPos = playing.getPlayer().getX();
                float yPos = playing.getPlayer().getY();
                System.out.println(levelIndex);
                SaveIntoDatabase("gamedatabase", "Game_Database",levelIndex, currentHealth, torchNumber, xPos, yPos);


                System.out.println("Joc salvat!");
            }
        }
        homeButton.resetBooleans();
        next_levelButton.resetBooleans();
        saveButton.resetBooleans();
    }
    public void mousePressed(MouseEvent e)
    {
        if(isIn(e,homeButton))
            homeButton.setMousePressed(true);
        if(isIn(e,next_levelButton))
            next_levelButton.setMousePressed(true);
        if(isIn(e,saveButton))
            saveButton.setMousePressed(true);

    }


}

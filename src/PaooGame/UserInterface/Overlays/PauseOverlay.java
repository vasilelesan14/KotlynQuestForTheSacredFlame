package PaooGame.UserInterface.Overlays;

import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import java.awt.*;
import java.awt.event.MouseEvent;

import static PaooGame.DatabaseManager.InsertGet.SaveIntoDatabase;
import static PaooGame.Entities.Player.GetTorchNumber;
import static PaooGame.Levels.LevelManager.GetLevelIndex;
import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;
import static PaooGame.Utilities.Constants.UI.Buttons.*;
import static PaooGame.Utilities.Constants.UI.Menu.*;
import static PaooGame.Utilities.Constants.UI.Menu.EMPTY_MENU_HEIGHT;

public class PauseOverlay
{
    private Playing playing;

    public PauseButton positiveButton = PauseButton.positiveButton;
    public PauseButton negativeButton = PauseButton.negativeButton;
    public PauseButton resumeButton = PauseButton.resumeButton;
    public PauseButton saveButton = PauseButton.saveButton;
    public PauseButton homeButton = PauseButton.homeButton;
    public PauseButton restartButton = PauseButton.restartButton;

    public PauseOverlay(Playing playing)
    {
        this.playing = playing;


    }

    public void update()
    {
    }

    public void draw(Graphics g)
    {
        g.drawImage(Assets.pause_panel,EMPTY_MENU_X,EMPTY_MENU_Y,EMPTY_MENU_WIDTH,EMPTY_MENU_HEIGHT,null);
        g.drawImage(Assets.musicText,VISIBLE_GAME_WIDTH/2-110,EMPTY_MENU_Y+165,BUTTON_WIDTH-70, BUTTON_HEIGHT-20,null);

        resumeButton.draw(g);
        homeButton.draw(g);
        saveButton.draw(g);
        positiveButton.draw(g);
        negativeButton.draw(g);
        restartButton.draw(g);
    }

    private boolean isIn(MouseEvent e, PauseButton b)
    {
        return b.getBounds().contains(e.getX(),e.getY());
    }

    public void mousePressed(MouseEvent e)
    {

        if(isIn(e,resumeButton))
        {
            resumeButton.setMousePressed(true);
        }
        if(isIn(e,homeButton))
        {
            homeButton.setMousePressed(true);
        }
        if(isIn(e,saveButton))
        {
            saveButton.setMousePressed(true);
        }
        if(isIn(e,positiveButton))
        {
            positiveButton.setMousePressed(true);
        }
        if(isIn(e,negativeButton))
        {
            negativeButton.setMousePressed(true);
        }
        if(isIn(e,restartButton))
        {
            restartButton.setMousePressed(true);
        }

    }

    public void mouseReleased(MouseEvent e)
    {

        if(isIn(e,resumeButton))
        {
            if(resumeButton.isMousePressed())
            {
                playing.unpauseGame();
            }

        }
        if(isIn(e,homeButton))
        {
            if(homeButton.isMousePressed())
            {
                homeButton.setGameState();
                playing.unpauseGame();
            }

        }
        if(isIn(e,saveButton))
        {
            if(saveButton.isMousePressed())
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
        if(isIn(e,positiveButton))
            if(positiveButton.isMousePressed())
                System.out.println("Crestere Volum!");


        if(isIn(e,negativeButton))
            if(negativeButton.isMousePressed())
                System.out.println("Scadere Volum!");

        if(isIn(e,restartButton))
        {
            if(restartButton.isMousePressed())
            {
                playing.resetPlaying();
                playing.unpauseGame();
            }

        }

        resetButtons();
    }

    public void mouseMoved(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {

    }
    private void resetButtons()
    {
        positiveButton.resetBooleans();
        negativeButton.resetBooleans();
        resumeButton.resetBooleans();
        saveButton.resetBooleans();
        homeButton.resetBooleans();
    }
}

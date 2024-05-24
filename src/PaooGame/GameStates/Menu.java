package PaooGame.GameStates;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Levels.Level;
import PaooGame.Levels.LevelManager;
import PaooGame.UserInterface.Buttons.Menu.MenuButton;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static PaooGame.DatabaseManager.InsertGet.*;
import static PaooGame.Entities.Player.GetTorchNumber;
import static PaooGame.Entities.Player.SetTorchNumber;
import static PaooGame.Levels.LevelManager.GetLevelIndex;
import static PaooGame.Levels.LevelManager.setLevelIndex;
import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_HEIGHT;
import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;
import static PaooGame.Utilities.Constants.UI.Menu.*;

public class Menu extends State implements StateMethods
{
    public MenuButton playButton = MenuButton.playButton;
    public MenuButton quitButton = MenuButton.quitButton;
    public MenuButton optionButton = MenuButton.optionButton;
    public MenuButton loadGameButton = MenuButton.load_gameButton;

    public Menu(Game game)
    {
        super(game);

    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw(Graphics g)
    {


        g.drawImage(Assets.menu_bck,0,0,VISIBLE_GAME_WIDTH,VISIBLE_GAME_HEIGHT,null);
        g.drawImage(Assets.empty_menu_panel,EMPTY_MENU_X,EMPTY_MENU_Y,EMPTY_MENU_WIDTH,EMPTY_MENU_HEIGHT,null);


        playButton.draw(g);
        quitButton.draw(g);
        optionButton.draw(g);
        loadGameButton.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {


        if(isIn(e, playButton))
        {
            playButton.setMousePressed(true);
            game.getPlaying().resetPlaying();
            game.getPlaying().getPlayer().setCurrentHealth(100);
            //LevelManager.setLevelIndex(0);
        }
        if(isIn(e, quitButton))
        {
            quitButton.setMousePressed(true);
        }
        if(isIn(e, optionButton))
        {
            optionButton.setMousePressed(true);
        }
        if(isIn(e, loadGameButton))
        {
            loadGameButton.setMousePressed(true);
        }


    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

        if(isIn(e,playButton))
        {
            if(playButton.isMousePressed())
            {
                playButton.setGameState();
                game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().GetLevelIndex());
            }
        }
        if(isIn(e,quitButton))
        {
            if(quitButton.isMousePressed())
            {
                quitButton.setGameState();
            }
        }
        if(isIn(e,optionButton))
        {
            if(optionButton.isMousePressed())
            {
                optionButton.setGameState();
            }
        }
        if(isIn(e,loadGameButton))
        {
            if(loadGameButton.isMousePressed())
            {

                int levelIndex = LoadLevelIndex("gamedatabase", "Game_Database");
                int currentHealth = LoadCurrentHealth("gamedatabase", "Game_Database");
                int torchNumber = LoadTorchNumber("gamedatabase", "Game_Database");
                float posX = LoadXPosition("gamedatabase", "Game_Database");
                float posY = LoadXPosition("gamedatabase", "Game_Database");

                if(levelIndex>0)
                    levelIndex--;

                setLevelIndex(levelIndex);
                game.getPlaying().getPlayer().setCurrentHealth(currentHealth);

                game.getPlaying().getPlayer().setX(posX);
                game.getPlaying().getPlayer().setX(posY);

                game.getPlaying().loadNextLevel();

                SetTorchNumber(torchNumber);

                GameState.state = GameState.PLAYING;
                System.out.println(" Jocul incarcat din baza de date!");
            }
        }
        resetButtons();

    }


    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            GameState.state = GameState.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    private void resetButtons()
    {

        playButton.resetBooleans();
        quitButton.resetBooleans();
        loadGameButton.resetBooleans();
        optionButton.resetBooleans();
    }

    public static boolean isIn(MouseEvent e, MenuButton mb)
    {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

}

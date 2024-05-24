package PaooGame.GameStates;

import PaooGame.Entities.EntititesHandler;
import PaooGame.Entities.Player;
import PaooGame.Game;
import PaooGame.Levels.LevelManager;
import PaooGame.Objects.ObjectHandler;
import PaooGame.UserInterface.Overlays.GameOverOverlay;
import PaooGame.UserInterface.Overlays.LevelCompletedOverlay;
import PaooGame.UserInterface.Overlays.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import static PaooGame.Utilities.Constants.GameConstants.*;

public class Playing extends State implements StateMethods
{


    private LevelManager levelManager;
    private ObjectHandler objectManager;
    private EntititesHandler enemyManager;
    private Player player;
    private boolean paused = false;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private boolean levelCompleted = false;
    private int cameraOffset;
    private int leftBorder = (int)(0.2 * VISIBLE_GAME_WIDTH);
    private int rightBorder = (int)(0.8 * VISIBLE_GAME_WIDTH);

    private int maxLvlOffset;
    private boolean gameOver = false;
    private boolean playerDying = false;

    public Playing(Game game)
    {
        super(game);
        initEntities();
        loadStartLevel();
        calculateLevelOffset();
    }

    private void loadStartLevel()
    {
        enemyManager.initEnemies(levelManager.getLevel());
        objectManager.loadObjects(levelManager.getLevel());
    }

    public void loadNextLevel()
    {
        resetPlaying();
        levelManager.changeLevel();
    }

    private void calculateLevelOffset()
    {
        maxLvlOffset = levelManager.getLevel().getLevelOffset();
    }

    private void initEntities()
    {
        player = new Player(80,VISIBLE_GAME_HEIGHT-110, TILE_WIDTH, TILE_HEIGHT, this);
        levelManager = new LevelManager(game);
        enemyManager = new EntititesHandler(this);
        objectManager = new ObjectHandler(this);

        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        levelCompletedOverlay = new LevelCompletedOverlay(this);
    }

    public Player getPlayer()
    {
        return player;
    }

    public void windowFocusLost()
    {
        player.setLeft(false);
        player.setRight(false);
    }

    @Override
    public void update()
    {
        if(paused)
        {
            pauseOverlay.update();
        }
        else if(levelCompleted)
        {
            levelCompletedOverlay.update();
        } else if (gameOver)
        {
//            gameOverOverlay.update();
        } else if (playerDying)
        {
            player.update();
        } else
        {
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getLevel().getLvlData(), player);
            objectManager.update();

            cameraMove();
        }
    }

    private void cameraMove()
    {
        int playerX = (int)player.getHitbox().x;
        int diff = playerX - cameraOffset;

        if(diff > rightBorder)
            cameraOffset += diff - rightBorder;
        else if (diff < leftBorder)
        {
            cameraOffset += diff - leftBorder;
        }

        if(cameraOffset > maxLvlOffset)
            cameraOffset = maxLvlOffset;
        else if (cameraOffset < 0)
        {
            cameraOffset = 0;
        }
    }

    @Override
    public void draw(Graphics g)
    {


        levelManager.draw(g, cameraOffset);
        enemyManager.draw(g,cameraOffset);

        g.setColor(null);
        objectManager.draw(g,cameraOffset);
        levelManager.drawFog(g,cameraOffset);

        player.draw(g, cameraOffset);
        if(paused)
        {
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0,0,VISIBLE_GAME_WIDTH,VISIBLE_GAME_HEIGHT);
            //g.drawImage(Assets.pause_bck, 0,0,VISIBLE_GAME_WIDTH,VISIBLE_GAME_HEIGHT,null);
            pauseOverlay.draw(g);
        }
        else if(gameOver)
        {
            gameOverOverlay.draw(g);
            //player.setCurrentHealth(100);
        }
        else if (levelCompleted)
        {
            levelCompletedOverlay.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(!gameOver)
            if(e.getButton() == MouseEvent.BUTTON1)
                player.setPlayerAttacking(true);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(!gameOver)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                player.setPlayerAttacking(true);

                if (paused)
                    pauseOverlay.mousePressed(e);
                else if (levelCompleted)
                {
                    levelCompletedOverlay.mousePressed(e);
                }
            }
        }
        else
        {
            gameOverOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(!gameOver)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                player.setPlayerAttacking(true);

                if (paused)
                    pauseOverlay.mouseReleased(e);
                else if (levelCompleted)
                {
                    levelCompletedOverlay.mouseReleased(e);
                }
            }
        }
        else
        {
                gameOverOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    public void unpauseGame()
    {
        paused = false;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(gameOver)
            gameOverOverlay.keyPressed(e);
        else
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W:
                    player.setJump(true);
                    break;
                case KeyEvent.VK_A:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_S:
                    //player.setDown(true);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(true);
                    break;
                case KeyEvent.VK_UP:
                    player.setJump(true);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_DOWN:
                    //player.setDown(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setRight(true);
                    break;

                case KeyEvent.VK_SPACE:
                    player.setJump(true);
                    break;

                case KeyEvent.VK_BACK_SPACE:
                    GameState.state = GameState.MENU;
                    break;

                case KeyEvent.VK_P:
                    paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(!gameOver)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W:
                    player.setJump(false);
                    break;
                case KeyEvent.VK_A:
                    player.setLeft(false);
                    break;
                case KeyEvent.VK_S:
                    //player.setDown(false);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(false);
                    break;
                case KeyEvent.VK_UP:
                    player.setJump(false);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setLeft(false);
                    break;
                case KeyEvent.VK_DOWN:
                    //player.setDown(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setRight(false);
                    break;

                case KeyEvent.VK_SPACE:
                    player.setJump(false);
                    break;
            }
        }
    }

    public void resetPlaying()
    {
        gameOver = false;
        paused = false;
        levelCompleted = false;

        player.resetPlayer();
        enemyManager.resetEnemies();
        objectManager.resetAllObjects();
        playerDying = false;

    }

    public void checkEnemyHit(Rectangle2D.Float attackBox)
    {
        enemyManager.checkEnemyHit(attackBox);
    }

    public void setGameOver(boolean b)
    {
        this.gameOver = b;
    }

    public EntititesHandler getEnemyManager()
    {
        return enemyManager;
    }

    public void setMaxLevelOffset(int levelOffset)
    {
        this.maxLvlOffset = levelOffset;
    }

    public void setLevelCompleted(boolean b)
    {
        levelCompleted = b;
    }
    public ObjectHandler getObjectManager()
    {
        return objectManager;
    }

    public void checkTorchTouched(Rectangle2D.Float hitbox)
    {
        objectManager.checkObjectTouched(hitbox );
    }

    public LevelManager getLevelManager()
    {
        return levelManager;
    }

    public void checkSpikeTouched(Player player)
    {
        objectManager.checkSpikesTouched(player);
    }

    public void setPlayerDying(boolean b)
    {
        this.playerDying = b;
    }
}


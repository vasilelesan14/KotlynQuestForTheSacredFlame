package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseButton
{

    public static PauseButton positiveButton            = new PositiveButton(5);
    public static PauseButton negativeButton            = new NegativeButton(6);
    public static PauseButton resumeButton              = new ResumeButton(7);
    public static PauseButton homeButton                = new HomeButton(8);
    public static PauseButton saveButton                = new SaveGameButton(9);
    public static PauseButton restartButton                = new RestartButton(10);
    public static PauseButton next_levelButton                = new NextLevelButton(11);

    protected int x,y,id, width, height;
    private Rectangle bounds;
    protected BufferedImage image;

    public void setGameState()
    {
        GameState.state = state;
    }

    protected GameState state;
    private boolean mouseOver, mousePressed;




    public PauseButton(int x, int y,int width, int height, BufferedImage img, GameState state, int idd)
    {
        this.x = x;
        this.y = y;
        this.image = img;
        this.state = state;
        this.width = width;
        this.height = height;
        this.id = idd;
        InitBounds();
    }

    private void InitBounds()
    {
        bounds = new Rectangle((int)(x-width/2),y,width,height);
    }
    public void update()
    {

    }
    public void draw(Graphics g)
    {
        g.drawImage(image, x - width/2,y, width,height,null);
//        g.setColor(Color.cyan);
//        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }


    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public boolean isMousePressed()
    {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed)
    {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans()
    {
        mouseOver = false;
        mousePressed = false;
    }
}

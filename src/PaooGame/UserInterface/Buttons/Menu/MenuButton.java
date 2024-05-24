package PaooGame.UserInterface.Buttons.Menu;

import PaooGame.GameStates.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Utilities.Constants.UI.Buttons.BUTTON_HEIGHT;
import static PaooGame.Utilities.Constants.UI.Buttons.BUTTON_WIDTH;

public class MenuButton
{

    public static MenuButton playButton     = new PlayButton(0);
    public static MenuButton quitButton     = new QuitButton(1);
    public static MenuButton optionButton  = new OptionsButton(2);
    public static MenuButton load_gameButton = new LoadGameButton(3);



    private int x, y,id,index;
    private boolean mouseOver,mousePressed;

    private Rectangle bounds;
    protected GameState state;
    protected BufferedImage image;
    public MenuButton(int x, int y, BufferedImage img, GameState state, int idd)
    {
        this.x = x;
        this.y = y;
        this.image = img;
        this.state = state;
        this.id = idd;
        InitBounds();
    }

    private void InitBounds()
    {
        bounds = new Rectangle(x - BUTTON_WIDTH/2, y, BUTTON_WIDTH,BUTTON_HEIGHT);
    }

    /// AICI E PROBLEMA ///
    public void draw(Graphics g)
    {
        g.drawImage(image, x - BUTTON_WIDTH/2,y, BUTTON_WIDTH,BUTTON_HEIGHT,null);
//        g.setColor(Color.green);
//        g.drawRect(bounds.x,bounds.y, bounds.width, bounds.height);
    }

    public void update()
    {
    }

    public boolean isMouseOver()
    {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver)
    {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed()
    {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed)
    {
        this.mousePressed = mousePressed;
    }

    public void setGameState()
    {
        GameState.state = state;
    }
    public void resetBooleans()
    {
        mouseOver = false;
        mousePressed = false;
    }
    public Rectangle getBounds()
    {
        return bounds;
    }

    public GameState getState()
    {
        return state;
    }

}

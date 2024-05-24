package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Inputs.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static PaooGame.Utilities.Constants.Directions.*;
import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.PlayerConstants.*;





public class GamePanel extends JPanel
{
    private MouseHandler mouseInputs;
    private KeyHandler keyInputs;
    private Font customFont;


    private Game game;

    public GamePanel(Game game)
    {
        keyInputs = new KeyHandler(this);
        mouseInputs = new MouseHandler(this);
        this.game = game;

        setPanelSize();

        addKeyListener(keyInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize()
    {
        Dimension size = new Dimension(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT);
        setMaximumSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
    }

    // metoda paint Component nu va fi niciodata apelata in mod direct
    // aceasta va fi apelata implicit in momentul rularii proiectului functionand
    // asemanator cu un constructor pentru clasa JPanel
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.Draw(g);

    }
    public void update()
    {

    }

    public void draw(Graphics g)
    {

    }

    public Game getGame()
    {
        return game;
    }
}

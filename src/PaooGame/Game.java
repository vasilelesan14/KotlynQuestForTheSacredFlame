package PaooGame;

import PaooGame.Audio.AudioPlayer;
import PaooGame.Entities.Player;
import PaooGame.GameStates.GameState;
import PaooGame.GameStates.Playing;
import PaooGame.GameStates.Menu;
import PaooGame.GameWindow.GamePanel;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Levels.LevelManager;


import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import static PaooGame.Utilities.Constants.GameConstants.*;

public class Game extends Canvas implements Runnable
{
    private GameWindow wnd;
    private GamePanel panel;

    private String title;

    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    private Graphics g;          /*!< Referinta catre un context grafic.*/



    AudioPlayer audioPlayer;

    private Playing playing;
    private Menu menu;

    public Game(String title, int width, int height) throws UnsupportedAudioFileException, IOException
    {
        panel = new GamePanel(this);
        wnd = new GameWindow(title, width, height);

        runState = false;

        panel.setFocusable(true);
        panel.requestFocus();

        this.title = title;

        Assets.Init();
        InitStates();
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame()
    {

        wnd = new GameWindow(title, VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT);
            /// Este construita fereastra grafica
        wnd.BuildGameWindow(panel);
            /// Se incarca toate elementele grafice (dale)

    }

    private void InitStates() throws UnsupportedAudioFileException, IOException
    {
        menu = new Menu(this);
        playing = new Playing(this);
        audioPlayer = new AudioPlayer();
    }



    @Override
    public void run()
    {
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final int updatesPerSecond  = 100; /*!< Constanta intreaga initializata cu numarul de update-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/
        final double timeUpdate     = 1000000000 / updatesPerSecond;

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;

        long previousTimeUpdate = System.nanoTime();

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            long currentTimeUpdate = System.nanoTime();

            deltaU += (currentTimeUpdate - previousTimeUpdate) / timeUpdate;
            previousTimeUpdate = currentTimeUpdate;

            if(deltaU >= 1)
            {
                Update();
                updates++;
                deltaU --;
            }

            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                panel.repaint();
                /// Actualizeaza pozitiile elementelor
                //Update();
                /// Deseneaza elementele grafica in fereastra.
                //Draw();
                oldTime = curentTime;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000)
            {
                lastCheck = System.currentTimeMillis();
                System.out.println(" FPS: "+frames+" | UPS: "+updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(runState == false)
        {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
            /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    public void Update()
    {


        switch (GameState.state)
        {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case QUIT:
                System.exit(0);
                break;
            case PAUSE:

            case OPTION:
                System.exit(0);
                break;
            default:
                break;
        }

    }

    public void Draw(Graphics g)
    {

        switch (GameState.state)
        {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }


    }
    public void windowFocusLost()
    {
        if(GameState.state == GameState.PLAYING)
        {
            playing.getPlayer().setJump(false);
            playing.getPlayer().setLeft(false);
            playing.getPlayer().setRight(false);

        }

    }

    public Playing getPlaying()
    {
        return playing;
    }
    public Menu getMenu()
    {
        return menu;
    }
    public GamePanel getPanel()
    {
        return panel;
    }
    public AudioPlayer getAudioPlayer()
    {
        return audioPlayer;
    }

}

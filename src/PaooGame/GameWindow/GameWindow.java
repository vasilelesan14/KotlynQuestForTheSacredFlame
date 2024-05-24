package PaooGame.GameWindow;


import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static PaooGame.Utilities.Constants.GameConstants.*;

public class GameWindow
{
    private JFrame  wndFrame;       /*!< fereastra principala a jocului.*/
    private JPanel  wndPanel;
    private String  wndTitle;       /*!< titlul ferestrei.*/
    private int     wndWidth;       /*!< latimea ferestrei in pixeli.*/
    private int     wndHeight;      /*!< inaltimea ferestrei in pixeli.*/
    public GameWindow(String title, int width, int height)
    {
        wndTitle    = title;
        wndWidth    = width;
        wndHeight   = height;
        wndFrame    = null;

    }

    public void BuildGameWindow(GamePanel panel)
    {
        /// Daca fereastra a mai fost construita intr-un apel anterior
        /// se renunta la apel
        if(wndFrame != null)
        {
            return;
        }
        /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
        /// ce apare in bara de titlu
        wndFrame = new JFrame(wndTitle);
        /// Seteaza dimensiunile ferestrei in pixeli
        wndFrame.setSize(wndWidth, wndHeight);
        /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
        /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
        /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
        /// program
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /// Avand in vedere ca dimensiunea ferestrei poate fi modificata
        /// si corespunzator continutul actualizat (aici ma refer la dalele
        /// randate) va recomand sa constrangeti deocamdata jucatorul
        /// sa se joace in fereastra stabilitata de voi. Puteti reveni asupra
        /// urmatorului apel ulterior.
        wndFrame.setResizable(false);
        /// Recomand ca fereastra sa apara in centrul ecranului. Pentru orice
        /// alte pozitie se va apela "wndFrame.setLocation(x, y)" etc.
        wndFrame.setLocationRelativeTo(null);
        /// Pentru a nu pierde focusul ferestrei in momentul in care cursorul este mutat
        wndFrame.addWindowFocusListener(new WindowFocusListener()
        {
            @Override
            public void windowGainedFocus(WindowEvent e)
            {

            }

            @Override
            public void windowLostFocus(WindowEvent e)
            {
                System.out.println(" Cursor iesit din fereastra.");
                panel.getGame().windowFocusLost();
            }
        });

        wndFrame.add(panel);


        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        wndFrame.pack();

        /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
        /// care trebuie setata aceasta proprietate
        wndFrame.setVisible(true);
    }
}

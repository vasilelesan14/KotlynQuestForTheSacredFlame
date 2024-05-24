package PaooGame;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static PaooGame.GameSingleton.getGameInstance;
import static PaooGame.Utilities.Constants.GameConstants.*;

public class Main
{
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException
    {
        Game paooGame = getGameInstance();
        paooGame.StartGame();

    }
}
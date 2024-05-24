package PaooGame;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static PaooGame.Utilities.Constants.GameConstants.*;

public class GameSingleton
{
    private static Game game=null;
    private GameSingleton(){}
    public static Game getGameInstance() throws UnsupportedAudioFileException, IOException
    {
        if(game==null)
        {
            game = new Game("Kotlyn: Quest for the Sacred Flame", VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT);
        }
        return game;
    }
}

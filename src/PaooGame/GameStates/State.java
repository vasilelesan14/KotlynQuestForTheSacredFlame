package PaooGame.GameStates;

import PaooGame.Audio.AudioPlayer;
import PaooGame.Game;

// aceasta va fi superclasa pentru fiecare state a jocului
public class State
{
    protected Game game;
    public State(Game game)
    {
        this.game = game;
    }
    public Game getGame()
    {
        return game;
    }
    public void setGameState(GameState state)
    {
        switch (state)
        {
            case MENU:
                game.getAudioPlayer().playMusic(AudioPlayer.meniu);
                break;
            case PLAYING:
                game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().GetLevelIndex());
        }
        GameState.state = state;
    }

}

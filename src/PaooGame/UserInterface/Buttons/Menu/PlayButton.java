package PaooGame.UserInterface.Buttons.Menu;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Menu.MenuButton;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;

public class PlayButton extends MenuButton
{
    public PlayButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 165, Assets.playButton, GameState.PLAYING, idd);
    }
}

package PaooGame.UserInterface.Buttons.Menu;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;

public class LoadGameButton extends MenuButton
{
    public LoadGameButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 230, Assets.load_gameButton, GameState.PLAYING, idd);
    }
}

package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;
import static PaooGame.Utilities.Constants.UI.Buttons.*;

public class HomeButton extends PauseButton
{
    public HomeButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 360, BUTTON_WIDTH,BUTTON_HEIGHT, Assets.homeButton, GameState.MENU, idd);
    }
}

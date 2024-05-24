package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import static PaooGame.Utilities.Constants.UI.Buttons.ROUND_BUTTON_SIZE;
import static PaooGame.Utilities.Constants.UI.Menu.EMPTY_MENU_X;
import static PaooGame.Utilities.Constants.UI.Menu.EMPTY_MENU_Y;

public class PositiveButton extends PauseButton
{
    public PositiveButton(int idd)
    {
        super(EMPTY_MENU_X + 270, EMPTY_MENU_Y + 155,ROUND_BUTTON_SIZE,ROUND_BUTTON_SIZE, Assets.positiveButton, GameState.PLAYING, idd);
    }
}

package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;
import static PaooGame.Utilities.Constants.UI.Buttons.*;

public class SaveGameButton extends PauseButton
{
    public SaveGameButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 295, BUTTON_WIDTH,BUTTON_HEIGHT, Assets.save_gameButton, GameState.PLAYING, idd);
    }
}

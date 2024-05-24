package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.UI.Buttons.*;

public class RestartButton extends PauseButton
{

    public RestartButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 424, BUTTON_WIDTH,BUTTON_HEIGHT, Assets.restartButton, GameState.PLAYING, idd);
    }
}

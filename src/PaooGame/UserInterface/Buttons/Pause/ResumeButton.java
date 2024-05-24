package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Pause.PauseButton;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;
import static PaooGame.Utilities.Constants.UI.Buttons.*;

public class ResumeButton extends PauseButton
{
    public ResumeButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 165, BUTTON_WIDTH,BUTTON_HEIGHT, Assets.resumeButton, GameState.MENU, idd);
    }
}

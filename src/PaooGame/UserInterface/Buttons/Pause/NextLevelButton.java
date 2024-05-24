package PaooGame.UserInterface.Buttons.Pause;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;
import static PaooGame.Utilities.Constants.UI.Buttons.BUTTON_HEIGHT;
import static PaooGame.Utilities.Constants.UI.Buttons.BUTTON_WIDTH;

public class NextLevelButton extends PauseButton
{
    public NextLevelButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 230, BUTTON_WIDTH,BUTTON_HEIGHT, Assets.next_levelButton, GameState.PLAYING, idd);
    }
}

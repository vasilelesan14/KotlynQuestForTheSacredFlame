package PaooGame.UserInterface.Buttons.Menu;

import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.UserInterface.Buttons.Menu.MenuButton;

import static PaooGame.Utilities.Constants.GameConstants.VISIBLE_GAME_WIDTH;

public class OptionsButton extends MenuButton
{
    public OptionsButton(int idd)
    {
        super(VISIBLE_GAME_WIDTH/2, 295, Assets.optionButton, GameState.PLAYING, idd);
    }
}
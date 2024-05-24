package PaooGame.Objects;

import PaooGame.Graphics.Assets;

import java.awt.*;

import static PaooGame.Utilities.Constants.GameConstants.TILE_HEIGHT;
import static PaooGame.Utilities.Constants.GameConstants.TILE_WIDTH;

public class Torch extends GameObject
{
    private float hoverOffset;
    private int maxHoverOffset=10, hoverDirection = 1;

    public Torch(int x, int y, int type)
    {
        super(x + TILE_WIDTH/2 - 5, y + TILE_HEIGHT/2 - 18, type);
        initHitbox(10,36);
        xDrawOffset = 25;
        yDrawOffset = 18;

    }

    public void update()
    {
        updateAnimationTick();
        updateHover();
    }

    private void updateHover()
    {
        hoverOffset += (0.175f * hoverDirection);
        if(hoverOffset >= maxHoverOffset)
            hoverDirection = -1;
        else if(hoverOffset < 0)
            hoverDirection = 1;

        hitbox.y = y + hoverOffset;

    }

}

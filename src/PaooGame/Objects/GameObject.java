package PaooGame.Objects;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Utilities.Constants.Directions.LEFT;
import static PaooGame.Utilities.Constants.Directions.RIGHT;
import static PaooGame.Utilities.Constants.EnemiesConstants.*;
import static PaooGame.Utilities.Constants.ObjectConstants.*;
import static PaooGame.Utilities.Constants.EnemiesConstants.DEAD;

public class GameObject
{


    protected int x,y, objectType;
    protected Rectangle2D.Float hitbox;
    protected boolean active = true;
    protected int animationTick, animationIndex, animationSpeed = 9;
    protected int xDrawOffset, yDrawOffset;
    public GameObject(int x, int y,int type)
    {
        this.x = x;
        this.y = y;
        this.objectType = type;
    }

    protected void initHitbox(int width, int height)
    {
        hitbox = new Rectangle2D.Float(x,y,width,height);
    }
    public void drawHitbox(Graphics g, int cameraOffset)
    {
        g.setColor(Color.green);
        g.drawRect((int)hitbox.x - cameraOffset, (int)hitbox.y,(int)hitbox.width, (int)hitbox.height);
    }

    protected void updateAnimationTick()
    {
        animationTick++;
        if(animationTick >= animationSpeed)
        {
            animationTick=0;
            animationIndex++;
            if(animationIndex >= GetSpriteNumber(objectType))
            {
                animationIndex = 0;

            }

        }
    }

    public void resetGameObjects()
    {
        animationTick = 0;
        animationIndex = 0;
        active = true;
    }

    public int getObjectType()
    {
        return objectType;
    }

    public Rectangle2D.Float getHitbox()
    {
        return hitbox;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean b)
    {
        this.active = b;
    }

    public int getAnimationIndex()
    {
        return animationIndex;
    }

}

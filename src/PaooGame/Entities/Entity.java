package PaooGame.Entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Entities.Collision.IsSolid;
import static PaooGame.Utilities.Constants.GameConstants.TILE_HEIGHT;
import static PaooGame.Utilities.Constants.GameConstants.TILE_WIDTH;

public abstract class Entity
{
    protected float x, y;               /*< coordonatele unde va fi generata entitatea*/
    protected float width, height;
    protected int animationTick, animationIndex;
    protected int action;
    protected float airSpeed;
    protected boolean inAir = false;

    protected float walkSpeed;
    protected int maxHealth, currentHealth;

    protected Rectangle2D.Float hitbox;
    protected Rectangle2D.Float attackBox;

    public Entity(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    protected void initHitbox(int width, int height)
    {
        hitbox = new Rectangle2D.Float(x,y,width,height);
    }

    protected void updateHitbox()
    {
        hitbox.x = x;
        hitbox.y = y;
    }

    protected void drawHitbox(Graphics g, int cameraOffset)
    {
        g.setColor(Color.green);
        g.drawRect((int)hitbox.x - cameraOffset, (int)hitbox.y,(int)hitbox.width, (int)hitbox.height);
    }

    public int getAnimationIndex()
    {
        return animationIndex;
    }

    public static float GetEntityPosNextToWall(Rectangle2D.Float hitbox, float xSpeed)
    {
        int currentTile = (int)(hitbox.x / TILE_WIDTH);
        if(xSpeed > 0 )
        {
            // La dreapta
            int tileXPos = currentTile * TILE_WIDTH;
            int xOffset = (int)(TILE_WIDTH - hitbox.width);
            return tileXPos + xOffset - 1;
        }
        else {
            // La stanga
            return currentTile * TILE_WIDTH;
        }
    }

    public static float GetEntityPosition(Rectangle2D.Float hitbox, float airSpeed)
    {
        int currentTile = (int)(hitbox.y / TILE_HEIGHT);
        if(airSpeed > 0 )
        {
            // Cadere
            int tileYPos = currentTile * TILE_HEIGHT;
            int yOffset = (int)(TILE_HEIGHT - hitbox.height);
            return tileYPos + yOffset - 1;
        }
        else
        {
            // Sare
            return currentTile * TILE_HEIGHT;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData)
    {
        // Verific tile-urile din stanga jos si dreapta jos
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height+1, levelData))
                if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height+1, levelData))
                    return false;
        return true;
    }

    public Rectangle2D.Float getHitbox()
    {
        return hitbox;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setCurrentHealth(int currentHealth)
    {
        this.currentHealth = currentHealth;
    }
}

package PaooGame.Objects;

import PaooGame.Entities.Player;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.Levels.Level;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.ObjectConstants.TORCH;

/*! \class public class ObjectHandler
    \brief Clasa se ocupa de manipularea obiectelor colectabile si a capcanelor corespunzatoare fiecarui nivel.

 */
public class ObjectHandler
{
    private Playing playing;
    private ArrayList<Torch> torches;
    private ArrayList<Spike> spikes;
    private static boolean areTorchesActive = true;

    public ObjectHandler(Playing playing)
    {
        this.playing = playing;
    }

    public void checkSpikesTouched(Player player)
    {
        for(Spike s: spikes)
            if(s.getHitbox().intersects(player.getHitbox()))
                player.setCurrentHealth(0);
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox)
    {
        for(Torch t: torches)
        {
            if(t.isActive())
                if(hitbox.intersects(t.getHitbox()))
                {
                    t.setActive(false);
                    applyEffectToPlayer(t);
                }
        }
    }

    public void applyEffectToPlayer(Torch t)
    {
        if(t.getObjectType() == TORCH)
            playing.getPlayer().increaseTorchNumber();
    }

    public void update()
    {
        areTorchesActive = false;
        for(Torch t: torches)
            if(t.isActive())
            {
                t.update();
                areTorchesActive = true;
            }
    }
    public void draw(Graphics g, int cameraOffset)
    {
        for(Torch t: torches)
            if(t.isActive())
            {
                g.drawImage(Assets.light,(int) (t.getHitbox().x - cameraOffset - t.xDrawOffset), (int) (t.getHitbox().y - t.yDrawOffset), TILE_WIDTH, TILE_HEIGHT, null);
                g.drawImage(Assets.torch[t.getAnimationIndex()], (int) (t.getHitbox().x - cameraOffset - t.xDrawOffset), (int) (t.getHitbox().y - t.yDrawOffset), TILE_WIDTH, TILE_HEIGHT, null);

                //t.drawHitbox(g,cameraOffset);
            }
        for(Spike s: spikes)
            g.drawImage(Assets.spikes,(int)(s.getHitbox().x - cameraOffset),(int) (s.getHitbox().y - s.yDrawOffset), TILE_WIDTH, TILE_HEIGHT, null );
    }


    public void loadObjects(Level newLevel)
    {
        torches = new ArrayList<>(newLevel.getTorches1());
        spikes = newLevel.getSpikes1();
    }

    public void resetAllObjects()
    {
        loadObjects(playing.getLevelManager().getLevel());
        for(Torch t: torches)
        {
            t.resetGameObjects();
        }
        areTorchesActive = true;

    }

    public static boolean isAnyTorchActive()
    {
        return areTorchesActive;
    }
    public static void setAreTorchesActive(boolean areTorchesActive)
    {
        ObjectHandler.areTorchesActive = areTorchesActive;
    }

}

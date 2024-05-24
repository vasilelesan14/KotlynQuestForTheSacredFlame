package PaooGame.Entities;

import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.Levels.Level;
import PaooGame.Levels.LevelManager;
import PaooGame.Objects.Torch;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static PaooGame.Objects.ObjectHandler.isAnyTorchActive;
import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.PlayerConstants.*;

public class EntititesHandler
{
    private Playing playing;
    private ArrayList<Spirit> spirits;
    private ArrayList<Demon> demons;
    private ArrayList<Lich> lichs;
    private ArrayList<Maze> maze;
    private ArrayList<Kali> kali;
    private ArrayList<Torch> torches;

    public EntititesHandler(Playing playing)
    {
        this.playing = playing;
        spirits = new ArrayList<>();
        demons = new ArrayList<>();
        lichs = new ArrayList<>();
        maze = new ArrayList<>();
        kali = new ArrayList<>();
    }

    public void initEnemies(Level level)
    {
        spirits = level.getSpirits1();
        demons = level.getDemons1();
        lichs = level.getLiches1();
        maze = level.getMaze1();
        kali = level.getKali1();
        torches = level.getTorches();
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox)
    {

        for(Demon d: demons)
        {
            if(d.isActive())
                if(attackBox.intersects(d.hitbox))
                {
                    d.hurt(10);
                    return;
                }
        }

        for(Maze m: maze)
        {
            if(m.isActive())
                if(attackBox.intersects(m.hitbox))
                {
                    m.hurt(10);
                    return;
                }
        }

        for(Kali k: kali)
        {
            if(k.isActive())
                if(attackBox.intersects(k.hitbox))
                {
                    k.hurt(10);
                    return;
                }
        }
        for(Lich l : lichs)
        {
            if(l.isActive())
                if(attackBox.intersects(l.hitbox))
                {
                    l.hurt(10);
                    return;
                }
        }
    }

    public void update(int [][] lvlData,  Player player)
    {

        boolean areActiveLiches = false;
        boolean areActiveDemons = false;
        boolean isMazeActive = false;
        boolean isKaliActive = false;
        for(Spirit s : spirits)
        {
            s.update(lvlData, player);
        }
        for(Demon d : demons)
        {
            if(d.isActive())
            {
                d.update(lvlData, player);
                areActiveDemons = true;
            }

        }

        for(Lich l : lichs)
        {
            if(l.isActive())
            {
                l.update(lvlData, player);
                areActiveLiches = true;
            }
        }
        for(Maze m: maze)
        {
            if(m.isActive())
            {
                m.update(lvlData, player);
                isMazeActive = true;
            }
        }
        for(Kali k: kali)
        {
            if(k.isActive())
            {
                k.update(lvlData, player);
                isKaliActive = true;
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(LevelManager.GetLevelIndex()==0)
        {
            if (!isAnyTorchActive())
                playing.setLevelCompleted(true);
        }
        else if(LevelManager.GetLevelIndex()==1)
        {
            if (!isMazeActive)
                playing.setLevelCompleted(true);
        }
        else if(LevelManager.GetLevelIndex()==2)
        {
            if (!isKaliActive)
                playing.setLevelCompleted(true);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    public void draw(Graphics g, int cameraOffset)
    {
        for(Spirit s : spirits)
        {
            //s.updateAnimationTick();
            g.drawImage(Assets.spiritAnimations[s.getEnemyAction()][s.getAnimationIndex()],(int)(s.getHitbox().x - cameraOffset - X_HITBOX_OFFSET/2),(int)(s.getHitbox().y - 5), (int)s.width, (int)s.height,null);
            //System.out.println("Spirit action: "+s.getEnemyAction()+"\n Spirit animationsIndex: "+s.getAnimationIndex());
            //g.setColor(Color.cyan);
            //s.drawHitbox(g, cameraOffset);
            //g.drawRect((int)s.getHitbox().getX(), (int)s.getHitbox().getY(), (int)s.getHitbox().getWidth(), (int)s.getHitbox().getHeight());
        }
        for(Demon d: demons)
        {
            if(d.isActive())
            {
                g.drawImage(Assets.demonAnimations[d.getEnemyAction()][d.getAnimationIndex()], (int) (d.getHitbox().x - cameraOffset - X_HITBOX_OFFSET), (int) (d.getHitbox().y - Y_HITBOX_OFFSET), TILE_WIDTH, TILE_HEIGHT, null);
                //d.drawHitbox(g,cameraOffset);
                //d.drawHitbox(g, cameraOffset);
            }
        }
        for(Lich l: lichs)
        {
            if(l.isActive())
            {
                g.drawImage(Assets.lichAnimations[l.getEnemyAction()][l.getAnimationIndex()], (int) (l.getHitbox().x - cameraOffset - X_HITBOX_OFFSET), (int) (l.getHitbox().y - Y_HITBOX_OFFSET), TILE_WIDTH, TILE_HEIGHT, null);
                //l.drawHitbox(g,cameraOffset);
            }
        }
        for(Maze m: maze)
            if(m.isActive())
            {
                g.drawImage(Assets.mazeAnimations[m.getEnemyAction()][m.getAnimationIndex()],(int)(m.getHitbox().x - cameraOffset - X_HITBOX_OFFSET),(int)(m.getHitbox().y - 2*PLAYER_HITBOX_HEIGHT+Y_HITBOX_OFFSET), TILE_WIDTH*2, TILE_HEIGHT*2,null);
                //d.drawHitbox(g,cameraOffset);
                //m.drawHitbox(g,cameraOffset);

            }
        for(Kali k: kali)
        {
            if(k.isActive())
            {
                g.drawImage(Assets.kaliAnimations[k.getEnemyAction()][k.getAnimationIndex()], (int) (k.getHitbox().x - cameraOffset - X_HITBOX_OFFSET), (int) (k.getHitbox().y - PLAYER_HITBOX_HEIGHT), TILE_WIDTH+TILE_WIDTH/2, TILE_HEIGHT+TILE_HEIGHT/2, null);
                //k.drawHitbox(g,cameraOffset);
            }
        }
    }

    public void resetEnemies()
    {
        for(Demon d: demons)
            d.resetEnemy();
        for(Maze m: maze)
            m.resetEnemy();
        for(Kali k: kali)
            k.resetEnemy();
        for(Lich l: lichs)
            l.resetEnemy();
    }
}

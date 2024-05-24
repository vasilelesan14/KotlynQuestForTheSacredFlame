package PaooGame.Entities;

import java.awt.geom.Rectangle2D;

import static PaooGame.Utilities.Constants.EnemiesConstants.*;
import static PaooGame.Utilities.Constants.EnemiesConstants.SpiritConstants.*;

public class Spirit extends Enemy
{

    public Spirit(float x, float y)
    {
        super(x, y, SPIRIT_WIDTH, SPIRIT_HEIGHT, SPIRIT);
        initHitbox(HITBOX_WIDTH,HITBOX_HEIGHT);
        initAttackBox();
    }

    public void update(int[][] lvlData, Player player)
    {
        updateMove(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }
    private void updateAttackBox()
    {
        attackBox.x = hitbox.x - attackBoxOffset;
        attackBox.y = hitbox.y;
    }
    private void initAttackBox()
    {
        attackBox = new Rectangle2D.Float(x,y,1,45);
    }

    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player)
    {
        if(attackBox.contains(player.hitbox.x, player.hitbox.y+10))
            player.changeHealth(-GetEnemyDamage(SPIRIT));
        attackChecked = true;
    }
    private void updateMove(int [][]lvlData, Player player)
    {
        if(firstUpdate)
            firstUpdate(lvlData);

        if(inAir)
        {
            updateInAir(lvlData);
        }
        else
        {
            switch (action)
            {

                case MOVE_LEFT:
                    checkPlayerHit(attackBox,player);
                    move(lvlData);
                    break;

                case MOVE_RIGHT:
                    checkPlayerHit(attackBox,player);
                    move(lvlData);
                    break;

            }
        }

    }
}

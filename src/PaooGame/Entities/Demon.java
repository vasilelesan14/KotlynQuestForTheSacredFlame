package PaooGame.Entities;

import java.awt.geom.Rectangle2D;

import static PaooGame.Utilities.Constants.EnemiesConstants.*;
import static PaooGame.Utilities.Constants.EnemiesConstants.ATTACK_LEFT;
import static PaooGame.Utilities.Constants.EnemiesConstants.ATTACK_RIGHT;
import static PaooGame.Utilities.Constants.PlayerConstants.*;
import static PaooGame.Utilities.Constants.GameConstants.*;

/*! \class public class Demon
    \brief Clasa care reprezinta jucatorul in cadrul jocului.

    Implementeaza clasa abstracta Entity.
 */
public class Demon extends Enemy
{
    public Demon(float x, float y)
    {
        super(x, y, TILE_WIDTH, TILE_HEIGHT, DEMON);
        initHitbox((int) (2*PLAYER_HITBOX_WIDTH), (int) PLAYER_HITBOX_HEIGHT - 3);
        animationSpeed = 7;

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
        attackBox = new Rectangle2D.Float(x,y,40,45);
    }

    private void updateMove(int[][] lvlData, Player player)
    {
        if (firstUpdate)
            firstUpdate(lvlData);

        if (inAir)
        {
            updateInAir(lvlData);
        } else
        {
            switch (action)
            {

                case MOVE_LEFT:

                    if (canSeePlayer(lvlData, player))
                    {
                        changeDirectionAfterPlayer(player);
                        if (isPlayerInRangeForAttack(player))
                            setEnemyAction(ATTACK_LEFT);
                    }
                    move(lvlData);
                    break;

                case MOVE_RIGHT:

                    if (canSeePlayer(lvlData, player))
                    {
                        changeDirectionAfterPlayer(player);
                        if (isPlayerInRangeForAttack(player))
                            setEnemyAction(ATTACK_RIGHT);
                    }
                    move(lvlData);
                    break;

                case ATTACK_LEFT:

                    if(animationIndex == 0)
                        attackChecked = false;
                    if(animationIndex==4 && !attackChecked)
                        checkPlayerHit(attackBox,player);

                case ATTACK_RIGHT:

                    if(animationIndex == 0)
                        attackChecked = false;
                    if(animationIndex==4 && !attackChecked)
                        checkPlayerHit(attackBox,player);

            }

        }

    }


}
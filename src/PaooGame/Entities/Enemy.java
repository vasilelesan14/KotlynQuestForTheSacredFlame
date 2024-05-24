package PaooGame.Entities;

import java.awt.geom.Rectangle2D;

import static PaooGame.Entities.Collision.*;
import static PaooGame.Utilities.Constants.Directions.LEFT;
import static PaooGame.Utilities.Constants.Directions.RIGHT;
import static PaooGame.Utilities.Constants.EnemiesConstants.*;
import static PaooGame.Utilities.Constants.GameConstants.TILE_HEIGHT;
import static PaooGame.Utilities.Constants.GameConstants.TILE_WIDTH;
import static PaooGame.Utilities.Constants.PlayerConstants.GRAVITY;

/// nu putem sa creem un inamic fara a sti tipul acestuia, astfel clasa trebuie sa fie abstracta
public abstract class Enemy extends Entity
{
    protected int enemyType;
    protected int tileY;
    protected int  animationSpeed = 18 , walkDir = RIGHT;
    protected boolean firstUpdate = true;
    protected float attackDistance = TILE_WIDTH/2;
    protected int attackBoxOffset=5;
    protected boolean active = true;
    protected boolean attackChecked;

    public Enemy(float x, float y, int width, int height, int enemyType)
    {
        super(x, y, width, height);
        this.enemyType = enemyType;
        this.action = MOVE_RIGHT;

        this.walkSpeed = 1f;

        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    protected void updateAnimationTick()
    {
        animationTick++;
        if(animationTick >= animationSpeed)
        {
            animationTick=0;
            animationIndex++;
            if(animationIndex >= GetSpriteNumber(enemyType,action))
            {
                animationIndex = 0;
                if(action==ATTACK_LEFT || action==ATTACK_RIGHT)
                    switch (walkDir)
                    {
                        case LEFT:
                            action = MOVE_LEFT;
                            break;
                        case RIGHT:
                            action = MOVE_RIGHT;
                            break;
                    }
                if(action == DEAD)
                    active = false;
            }

        }
    }

    protected void changeWalkDirection()
    {
        if(walkDir == LEFT)
        {
            walkDir = RIGHT;
            action = MOVE_RIGHT;
        }
        else
        {
            walkDir = LEFT;
            action = MOVE_LEFT;
        }
    }

    protected boolean canSeePlayer(int [][]lvlData, Player player)
    {
        int playerTileY = (int)(player.getHitbox().y / TILE_HEIGHT);

        /// daca inamicul se afla pe acceasi coordonata y cu player-ul
        if(playerTileY == tileY)
        {
            /// verific daca inamicul este destul de aproape de player
            if (isPlayerInRange(player))
                /// verific daca intre inamic si player nu se afla nimic altceva
                if (IsSightClear(lvlData, hitbox, player.hitbox, tileY))
                    return true;
        }
        return false;
    }

    protected void changeDirectionAfterPlayer(Player player)
    {
        if(player.hitbox.x >= hitbox.x || player.hitbox.x+player.hitbox.width >= hitbox.x)
        {
            walkDir = RIGHT;
            action = MOVE_RIGHT;
        }
        else
        {
            walkDir = LEFT;
            action = MOVE_LEFT;
        }
    }

    protected boolean isPlayerInRangeForAttack(Player player)
    {
        int absValue = Math.abs((int)(player.hitbox.x - hitbox.x ));
        return (absValue <= attackDistance);
    }

    protected boolean isPlayerInRange(Player player)
    {
        int absValue1 = Math.abs((int)(player.hitbox.x - hitbox.x ));
        int absValue2 = Math.abs((int)(player.hitbox.x+player.hitbox.width - hitbox.x+hitbox.width ));

        if(absValue1 <= (5*TILE_WIDTH))
            return true;
        if(absValue2 <= (5*TILE_WIDTH))
            return true;

        return false;

    }

    protected void firstUpdate(int[][] lvlData)
    {
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
        firstUpdate = false;
    }

    protected void updateInAir(int[][] lvlData)
    {
        if(CanMoveHere(hitbox.x, hitbox.y, hitbox.width, hitbox.height, lvlData))
        {
            hitbox.y += airSpeed;
            airSpeed += GRAVITY;
        }
        else
        {
            inAir = false;
            hitbox.y = GetEntityPosition(hitbox,airSpeed);
            tileY = (int)(hitbox.y / TILE_HEIGHT);
        }
    }

    protected void move(int[][] lvlData)
    {
        float xSpeed = 0;
        if(walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        if(CanMoveHere(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
        {
            if(IsFloor(hitbox, xSpeed, lvlData))
            {
                hitbox.x += xSpeed;
            }
            else changeWalkDirection();
        }
        else
            changeWalkDirection();
    }



    public int getEnemyAction()
    {
        return action;

    }
    public boolean isActive()
    {
        return active;
    }

    public void setEnemyAction(int enemyAction)
    {
        this.action = enemyAction;
        animationIndex = 0;
        animationTick = 0;
    }

    public void hurt(int i)
    {
        currentHealth -= i;
        if(currentHealth <= 0)
            setEnemyAction(DEAD);

    }

    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player)
    {
        if(attackBox.intersects(player.hitbox) || attackBox.contains(player.hitbox) || attackBox.contains(player.hitbox.x, player.hitbox.y) || attackBox.contains(player.hitbox.x+ hitbox.width, player.hitbox.y))
            player.changeHealth(-GetEnemyDamage(enemyType));

        attackChecked = true;
    }

    public void resetEnemy()
    {
        hitbox.x = x;
        hitbox.y = y;

        firstUpdate = true;
        currentHealth = maxHealth;
        setEnemyAction(MOVE_RIGHT);
        walkDir = RIGHT;
        active = true;
    }
}

package PaooGame.Entities;

import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Entities.Collision.*;
import static PaooGame.Utilities.Constants.Directions.*;
import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.PlayerConstants.*;
import static PaooGame.Utilities.Constants.UI.BackgroundsConstants.*;

/*! \class public class Player
    \brief Clasa care reprezinta jucatorul in cadrul jocului.

    Implementeaza clasa abstracta Entity.
 */
public class Player extends Entity
{

    private Playing playing;
    private int[][] levelData = Assets.firstLevelMap;

    private int  animationSpeed = 5, playerDirection = -1;
    private boolean playerMoving = false;

    public static float deployX;
    public static float deployY;
    private int healthWidth;
    private static int torchNumber = 0;
    private boolean playerAttacking = false;

    private boolean left= false;
    private boolean right= false;
    private boolean jump = false;

    private boolean attackChecked = false;

    /*! \fn public Player(int x, int y, int width, int height, Playing playing)
        \brief Constructorul de initializare al clasei

        \param x Pozitia x a jucatorului.
        \param y Pozitia y a jucatorului.
        \param playing contextul state-ului de joc in care jucatorul se afla

    */

    public Player(float x, float y, int width, int height, Playing playing)
    {
        super(x, y, width, height);
        this.playing = playing;
        deployX = x;
        deployY = y;

        this.action = MOVING_RIGHT;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;

        this.walkSpeed = PLAYER_SPEED;

        initHitbox((int)( PLAYER_HITBOX_WIDTH), (int)(PLAYER_HITBOX_HEIGHT -3));
        initAttackbox();

        if(!IsEntityOnFloor(hitbox, levelData))
            inAir = true;

    }

    /*! \fn private void initAttackbox()
        \brief Functia de initializare a hitboxului jucatorului

    */

    private void initAttackbox()
    {
        attackBox = new Rectangle2D.Float(x,y,(int)width-40, (int)height-30);
    }

    /*! \fn  public void update()
        \brief Functia de actualizare a starii jucatorului

    */
    public void update()
    {
        updateHealthBar();
        if(currentHealth <= 0)
        {

            if(action!=DEAD)
            {
                action = DEAD;
                animationIndex = 0;
                animationTick = 0;
                playing.setPlayerDying(true);
            }
            else if(animationIndex == GetSpritesNumber(DEAD)-1 && animationTick >= animationSpeed-1)
            {
                playing.setGameOver(true);
            }
            else {
                updateAnimationTick();
            }
            return;
        }


        updateAttackBox();
        updateSpeed();
        moves();

        if(playerMoving)
        {
            chckSpikeTouched();
            checkTorchTouched();
        }

        if(playerAttacking)
        {
            checkAttack();
        }
        updateHitbox();
        updateAnimationTick();

    }

    /*! \fn  private void chckSpikeTouched()
        \brief Fucntia verifica daca jucatorul atinge obiecte de tip Spike(tepusa)

    */
    private void chckSpikeTouched()
    {
        playing.checkSpikeTouched(this);
    }

    /*! \fn   private void checkTorchTouched()
        \brief Functia verifica daca jucatorul atinge obiecte colectabile de tip Torch(torta)

    */
    private void checkTorchTouched()
    {
        playing.checkTorchTouched(hitbox);
    }

    /*! \fn   private void checkAttack()
        \brief Functia verifica daca jucatorul atinge obiecte colectabile de tip Torch(torta)

    */
    private void checkAttack()
    {
        if(attackChecked || animationIndex != 1)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
    }

    private void updateAttackBox()
    {
        if(right)
        {
            attackBox.x = hitbox.x + hitbox.width + 10;
        }
        else if(left)
        {
            attackBox.x = hitbox.x - hitbox.width - 10;
        }
        attackBox.y = hitbox.y + 10;
    }

    private void updateHealthBar()
    {
        healthWidth = (int)((currentHealth / (float)maxHealth) * HEALTH_BAR_WIDTH);
    }

    private void drawPlayerUserInterface(Graphics g)
    {
        g.drawImage(Assets.statusBar, STATUS_BAR_X, STATUS_BAR_Y, STATUS_BAR_WIDTH, STATUS_BAR_HEIGHT,null);
        g.setColor(new Color(79,41,5));

        g.fillRoundRect(HEALTH_BAR_X-10, HEALTH_BAR_Y, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT,16,16);

        if(healthWidth >= 0.5*maxHealth)
            g.setColor(Color.green);
        else
            g.setColor(Color.red);

        g.fillRoundRect(HEALTH_BAR_X-10, HEALTH_BAR_Y, healthWidth, HEALTH_BAR_HEIGHT,16,16);

        g.drawImage(Assets.hearth, STATUS_BAR_X, STATUS_BAR_Y, STATUS_BAR_HEIGHT, STATUS_BAR_HEIGHT,null);


        g.drawImage(Assets.panelScor, STATUS_BAR_X+STATUS_BAR_HEIGHT/2, STATUS_BAR_Y+TILE_HEIGHT, STATUS_BAR_WIDTH/2,STATUS_BAR_HEIGHT,null );
        g.drawImage(Assets.torchImageScor,STATUS_BAR_X,STATUS_BAR_Y+TILE_HEIGHT, STATUS_BAR_HEIGHT,STATUS_BAR_HEIGHT,null );
        g.setFont(new Font("Monospaced", Font.BOLD, 30));
        g.drawString(String.valueOf(currentHealth)+"%", STATUS_BAR_X + STATUS_BAR_WIDTH + 10, 58);
        g.setColor(new Color(57,31,0));
        g.drawString(" "+String.valueOf(torchNumber), STATUS_BAR_X+56, STATUS_BAR_Y+TILE_HEIGHT+TILE_HEIGHT/2+3);
    }

    public void changeHealth(int value)
    {
        currentHealth += value;
        if(currentHealth <= 0)
        {
            currentHealth = 0;
            action = DEAD;
        }
        ///GAME OVER();
        else if (currentHealth >= maxHealth)
        {
            currentHealth = maxHealth;
        }
    }

    public void draw(Graphics g, int cameraOffset)
    {
        g.drawImage(Assets.playerAnimations[action][animationIndex], (int)(hitbox.x - X_HITBOX_OFFSET) - cameraOffset,(int)(hitbox.y - Y_HITBOX_OFFSET),TILE_WIDTH,TILE_HEIGHT,null);
//        drawHitbox(g,cameraOffset);
//        drawAttackHitbox(g,cameraOffset);
        drawPlayerUserInterface(g);
    }

    private void updateAnimationTick()
    {
        animationTick++;
        if(animationTick >= animationSpeed)
        {
            animationTick=0;
            animationIndex++;
            if(animationIndex >= GetSpritesNumber(action))
            {
                animationIndex = 0;
                playerAttacking = false;
                attackChecked = false;
            }
        }
    }

    public void moves()
    {
        int previousAnimation = action;
        if (playerMoving)
            switch (playerDirection)
            {
                case RIGHT:
                    action = MOVING_RIGHT;
                    break;
                case LEFT:
                    action = MOVING_LEFT;
                    break;
            }
        else
            switch (playerDirection)
            {
                case RIGHT:
                    action = IDLE_RIGHT;
                    break;
                case LEFT:
                    action = IDLE_LEFT;
                    break;
                default:
                    action = IDLE_RIGHT;
                    break;
            }

        if(inAir)
        {
            switch (playerDirection)
            {
                case RIGHT:
                    action = JUMP_RIGHT;
                    break;
                case LEFT:
                    action = JUMP_LEFT;
                    break;
                default:
                    action = JUMP_RIGHT;
                    break;
            }
        }

        if(playerAttacking)
            switch (playerDirection)
            {
                case RIGHT:
                    action = ATTACK_RIGHT;
                    if(previousAnimation != ATTACK_RIGHT)
                    {
                        animationIndex = 3;
                        animationTick = 0;
                        return;
                    }
                    break;
                case LEFT:
                    action = ATTACK_LEFT;
                    if(previousAnimation != ATTACK_LEFT)
                    {
                        animationIndex = 3;
                        animationTick = 0;
                        return;
                    }
                    break;

                default:
                    action = ATTACK_RIGHT;
                    break;
            }
        if(previousAnimation != action)
        {
            animationTick=0;
            animationIndex=0;
        }
    }



    private void updateSpeed()
    {
        playerMoving = false;

        if(jump)
        {
            jump();
        }

//        if(!left && !right && !playerInAir)
//            return;

        if(!inAir)
            if((!left && !right) || (right && left))
                return;

        float xSpeed = 0;

        if(!inAir)
        {
            if(!IsEntityOnFloor(hitbox,levelData))
            {
                inAir = true;
            }
        }

        if(left)
        {
            xSpeed -= walkSpeed;
            playerDirection = LEFT;
            //playerMoving = true;
        }
        if (right)
        {
            xSpeed += walkSpeed;
            playerDirection = RIGHT;
            //playerMoving = true;
        }

        if(inAir)
        {
            if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, PLAYER_HITBOX_WIDTH, PLAYER_HITBOX_HEIGHT,levelData))
            {
                hitbox.y += airSpeed;
                y += airSpeed;
                airSpeed += GRAVITY;
                updateXPos(xSpeed);
            }
            else
            {
                hitbox.y = GetEntityPosition(hitbox, airSpeed);
                y = GetEntityPosition(hitbox, airSpeed);
                if(airSpeed > 0)
                {
                    inAir = false;
                    airSpeed = 0;
                }
                else
                {
                    airSpeed = FALL_SPEED;
                }
                updateXPos(xSpeed);

//                if(y>TOTAL_GAME_HEIGHT)
//                    playing.setGameOver(true);
            }
        }
        else
        {
            updateXPos(xSpeed);
        }
        playerMoving = true;

    }



    private void jump()
    {
        if(inAir)
            return;
        inAir = true;
        airSpeed = JUMP_SPEED;
    }


    private void updateXPos(float xSpeed)
    {
        if(CanMoveHere(hitbox.x+xSpeed,hitbox.y,hitbox.width,hitbox.height,levelData))
        {
            x += xSpeed;
            hitbox.x += xSpeed;

        }
        else
        {
            x = GetEntityPosNextToWall(hitbox,xSpeed);
            hitbox.x = GetEntityPosNextToWall(hitbox,xSpeed);
        }
    }

    public boolean isLeft()
    {
        return left;
    }

    public void setLeft(boolean left)
    {
        this.left = left;
    }

    public boolean isRight()
    {
        return right;
    }

    public void setRight(boolean right)
    {
        this.right = right;
    }
    public void setJump(boolean jump)
    {
        this.jump = jump;
    }

    public boolean isPlayerAttacking()
    {
        return playerAttacking;
    }

    public void setPlayerAttacking(boolean playerAttacking)
    {
        this.playerAttacking = playerAttacking;
    }
    public void loadLevelData(int[][] levelData)
    {
        this.levelData = levelData;
        if(!IsEntityOnFloor(hitbox,levelData))
            inAir = true;
    }

    public void resetPlayer()
    {
        left = false;
        right = false;
        jump = false;
        inAir = false;
        playerAttacking = false;
        playerMoving = false;
        action = IDLE_RIGHT;
        playerDirection = RIGHT;
        //currentHealth = maxHealth;
        torchNumber = 0;


        hitbox.x = deployX;
        hitbox.y = deployY;
        x = deployX;
        y = deployY;

        if(!IsEntityOnFloor(hitbox,levelData))
            inAir = true;
    }

    public void increaseTorchNumber()
    {
        torchNumber++;
    }
    public static void SetTorchNumber(int torchNumber)
    {
        Player.torchNumber = torchNumber;
    }

    public static int GetTorchNumber()
    {
        return torchNumber;
    }

    public int getCurrentHealth()
    {
        return this.currentHealth;
    }
}

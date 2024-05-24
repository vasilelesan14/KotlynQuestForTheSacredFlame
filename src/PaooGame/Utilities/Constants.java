package PaooGame.Utilities;

import static PaooGame.Utilities.Constants.GameConstants.TILE_WIDTH;

public class Constants
{
    public static class UI
    {
        public static class Buttons
        {
            public static final int BUTTON_WIDTH = 180;
            public static final int BUTTON_HEIGHT = 50;
            public static final int ROUND_BUTTON_SIZE = 50;
        }
        public static class Menu
        {
            public static final int EMPTY_MENU_WIDTH = 350;
            public static final int EMPTY_MENU_HEIGHT = 496;
            public static final int EMPTY_MENU_X = GameConstants.VISIBLE_GAME_WIDTH/2 - EMPTY_MENU_WIDTH/2;
            public static final int EMPTY_MENU_Y = GameConstants.VISIBLE_GAME_HEIGHT/2 - EMPTY_MENU_HEIGHT/2;

        }

        public static class BackgroundsConstants
        {
            public static final int X_ARMOR_OFFSET = 575;
            public static final int Y_ARMOR_OFFSET = 285;
            public static final int ARMOR_WIDTH = 770;
            public static final int ARMOR_HEIGHT = 532;

            public static final int STATUS_BAR_WIDTH = 200;
            public static final int STATUS_BAR_HEIGHT = 55;
            public static final int HEALTH_BAR_HEIGHT = 31;
            public static final int HEALTH_BAR_WIDTH = STATUS_BAR_WIDTH - STATUS_BAR_HEIGHT;

            public static final int STATUS_BAR_X = 30;
            public static final int STATUS_BAR_Y = 20;
            public static final int HEALTH_BAR_X = STATUS_BAR_X + STATUS_BAR_HEIGHT;
            public static final int HEALTH_BAR_Y = STATUS_BAR_Y + 10;
            public static final int CURTAIN_WIDTH = 1897;
            public static final int CURTAIN_HEIGHT = 1080;
        }
    }

    public static class GameConstants
    {
        public static final int TILE_WIDTH = 64;
        public static final int TILE_HEIGHT = 64;
        public static final int VISIBLE_GAME_WIDTH = 960;           /*!< Latimea ferestrei de afisare jocului.*/
        public static final int VISIBLE_GAME_HEIGHT = 640;          /*!< Inaltimea ferestrei de afisare jocului.*/
        public static final int TILES_IN_WIDTH = 45;        /*!< Latimea ferestrei de afisare jocului exprimata in numarul de tile-uri.*/
        public static final int TILES_IN_HEIGHT = 10;       /*!< Inaltimea ferestrei de afisare jocului exprimata in numarul de tile-uri.*/
        public static final int VISIBLE_TILES_WIDTH = VISIBLE_GAME_WIDTH / TILE_WIDTH;


        public static final int TOTAL_GAME_WIDTH = TILE_WIDTH * TILES_IN_WIDTH;
        public static final int TOTAL_GAME_HEIGHT = TILE_HEIGHT * TILES_IN_HEIGHT;

    }

    public static class Directions
    {
        public static final int UP = 0;
        public static final int  LEFT = 1;
        public static final int DOWN = 2;
        public static final int RIGHT = 3;
    }

    public static class EnemiesConstants
    {
        public static final int SPIRIT = 9;
        public static final int LICH = 2;
        public static final int DEMON = 4;
        public static final int MAZE = 5;
        public static final int KALI = 10;


        public static final int MOVE_RIGHT = 0;
        public static final int MOVE_LEFT = 1;
        public static final int ATTACK_RIGHT = 2;
        public static final int ATTACK_LEFT = 3;
        public static final int DEAD = 4;
        public static final int ATTACKING = 5;



        public static final int HITBOX_WIDTH = 20;
        public static final int HITBOX_HEIGHT = 40;

        public static class SpiritConstants
        {
            public static final int X_DRAW_OFFSET = TILE_WIDTH - HITBOX_WIDTH;
            public static final int Y_DRAW_OFFSET = TILE_WIDTH - HITBOX_HEIGHT;
            public static final int SPIRIT_HEIGHT = 50;
            public static final int SPIRIT_WIDTH = 50;

        }
        public static class LichConstants
        {
            public static final int LICH_MOVE_RIGHT = 0;
            public static final int LICH_MOVE_LEFT = 1;
            public static final int LICH_ATTACK_RIGHT = 2;
            public static final int LICH_ATTACK_LEFT = 3;

        }

        public static class DemonConstants
        {
            public static final int DEMON_MOVE_RIGHT = 0;
            public static final int DEMON_MOVE_LEFT = 1;
            public static final int DEMON_ATTACK_RIGHT = 2;
            public static final int DEMON_ATTACK_LEFT = 3;

        }

        public static class MazeConstants
        {
            public static final int MAZE_MOVE_RIGHT = 0;
            public static final int MAZE_MOVE_LEFT = 1;
            public static final int MAZE_ATTACK_RIGHT = 2;
            public static final int MAZE_ATTACK_LEFT = 3;

        }

        public static int GetMaxHealth(int enemyType)
        {
            switch (enemyType)
            {
                case LICH:
                    return 10;
                case DEMON:
                    return 10;
                case MAZE:
                    return 10;
                case KALI:
                    return 20;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDamage(int enemyType)
        {
            switch (enemyType)
            {
                case SPIRIT:
                    return 1;
                case LICH:
                    return 5;
                case DEMON:
                    return 5;
                case MAZE:
                    return 10;
                case KALI:
                    return 15;
                default:
                    return 1;
            }
        }

        public static int GetSpriteNumber(int enemyType,int enemyAction)
        {
            switch (enemyType)
            {
                case (SPIRIT):
                    switch (enemyAction)
                    {
                        case (MOVE_LEFT):
                            return 6;
                        case (MOVE_RIGHT):
                            return 6;
                    }
                case (LICH):
                    switch (enemyAction)
                    {
                        case (MOVE_RIGHT):
                            return 9;
                        case (MOVE_LEFT):
                            return 9;
                        case(ATTACK_RIGHT):
                            return 6;
                        case (ATTACK_LEFT):
                            return 6;
                        case (DEAD):
                            return 6;
                    }
                case(DEMON):
                    switch (enemyAction)
                    {
                        case (MOVE_RIGHT):
                            return 9;
                        case (MOVE_LEFT):
                            return 9;
                        case (ATTACK_RIGHT):
                            return 6;
                        case (ATTACK_LEFT):
                            return 6;
                        case (DEAD):
                            return 6;
                    }
                case(MAZE):
                    switch (enemyAction)
                    {
                        case (MOVE_RIGHT):
                            return 9;
                        case (MOVE_LEFT):
                            return 9;
                        case(ATTACK_RIGHT):
                            return 8;
                        case (ATTACK_LEFT):
                            return 8;
                        case (DEAD):
                            return 6;
                    }
                case(KALI):
                    switch (enemyAction)
                    {
                        case (MOVE_RIGHT):
                            return 9;
                        case (MOVE_LEFT):
                            return 9;
                        case(ATTACK_RIGHT):
                            return 8;
                        case (ATTACK_LEFT):
                            return 8;
                        case (DEAD):
                            return 6;
                    }
            }
            return 0;
        }
    }

    public static class ObjectConstants
    {
        public static final int TORCH = 1;
        public static final int SPIKE = 18;
        public static int GetSpriteNumber(int objectType)
        {
            switch (objectType)
            {
                case TORCH:
                    return 8;
                case SPIKE:
                    return 1;
                default:
                    return 0;
            }
        }

    }

    public static class PlayerConstants
    {
        /// Constante ce ma ajuta sa procesez matricea de sprite-uri a playerului
        public static final int IDLE_RIGHT = 0;
        public static final int IDLE_LEFT = 1;
        public static final int MOVING_RIGHT = 2;
        public static final int MOVING_LEFT = 3;
        public static final int JUMP_RIGHT = 4;
        public static final int JUMP_LEFT = 5;
        public static final int ATTACK_RIGHT = 6;
        public static final int ATTACK_LEFT = 7;
        public static final int DEAD = 8;

        public static final float PLAYER_SPEED = 2f;
        public static final float JUMP_SPEED = -9.0f;
        public static final float FALL_SPEED = 1f;


        public static final float GRAVITY = 0.2f;


        public static final float X_HITBOX_OFFSET = 16;
        public static final float Y_HITBOX_OFFSET = 16;
        public static final float PLAYER_HITBOX_WIDTH = 16;
        public static final float PLAYER_HITBOX_HEIGHT = 45;


        /*! \fn public static int GetSpriteNumbers()
            \brief Returneaza numarul de sprite-uri corespunzator fiecarei actiuni in parte.
         */
        public static int GetSpritesNumber(int action)
        {
            switch (action)
            {
                case IDLE_RIGHT:
                    return 1;
                case IDLE_LEFT:
                    return 1;
                case MOVING_RIGHT:
                    return 8;
                case MOVING_LEFT:
                    return 8;
                case JUMP_RIGHT:
                    return 1;
                case JUMP_LEFT:
                    return 1;
                case ATTACK_RIGHT:
                    return 6;
                case ATTACK_LEFT:
                    return 6;
                case DEAD:
                    return 6;
                default:
                    return 1;
            }
        }
    }


}

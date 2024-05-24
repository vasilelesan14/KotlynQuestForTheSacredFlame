package PaooGame.Entities;

import PaooGame.Tiles.Tile;


import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGame.Utilities.Constants.GameConstants.*;

/*! \class public class Collision
    \brief Clasa ce contine doua metode statice pentru verificarea coliziunlor player-ului cu mediul.
 */
public class Collision
{
    /*! \fn  public static boolean CanMoveHere(int x, int y, int width, int height, int [][] levelMap)
        \brief Verifica daca playerul poate fi mutat in coordonatele din apropierea sa cu ajutorul fn IsSolid(...). Verificarea se face
        \pe rand, pornind din colturi si mergand pe diagonalele patratului descris de x,y,width,height.

        \param x,y, width, height Coordonatele curente ale playerului, respectiv latimea si inaltimea pentru
        care se face verificarea coliziunii; levelMap Matricea corespunzatoare tile-urilor nivelului curent.
     */
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelMap)
    {
        if (!IsSolid(x, y, levelMap))
            if (!IsSolid(x + width, y + height, levelMap))
                if (!IsSolid(x + width, y, levelMap))
                    if (!IsSolid(x, y + height, levelMap))
                        return true;
        return false;
    }


    /*! \fn  public static boolean IsSolid(int x, int y, int [][] levelMap)
        \brief Satbileste coliziunile player-ului cu fereastra, dupa care verifica pe rand
        \fiecare tip de tile solid intalnit in matrice.

        \param x,y, levelMap Coordonatele curente ale player-ului, respectiv harta nivelului curent.
     */
    public static boolean IsSolid(float x, float y, int[][] levelMap)
    {
        if (x < 0 || x >= TOTAL_GAME_WIDTH)
            return true;
        if (y < 0 || y >= TOTAL_GAME_HEIGHT)
            return true;

        /// Se stabilesc indecsii din matrice pe baza pozitiei curente si a diimensiunii unui tile.
        float xIndex = x / Tile.TILE_WIDTH;
        float yIndex = y / Tile.TILE_HEIGHT;

        return IsTileSolid((int)yIndex, (int)xIndex, levelMap);
    }

    /*! \fn  public static boolean IsSolid(int x, int y, int [][] levelMap)
        \brief Satbileste coliziunile player-ului cu fereastra, dupa care verifica pe rand
        \fiecare tip de tile solid intalnit in matrice.

        \param x,y, levelMap Coordonatele curente ale player-ului, respectiv harta nivelului curent.
     */
    public static boolean IsTileSolid(int xTile, int yTile, int [][] levelMap)
    {
        int currentTile = levelMap[xTile][yTile];

        switch (currentTile)
        {

            case 3:
                return true;
            case 6:
                return true;
            case 7:
                return true;
            case 8:
                return true;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            case 20:
                return true;
            case 21:
                return true;
            case 22:
                return true;
            case 23:
                return true;
        }

        return false;

    }

    /*! \fn  public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData)
       \brief Satbileste daca entitatea se afla pe podea, folosindu-se de metosa IsSolid.
       \

       \param Rectangle2D.Float hitbox Hitboxul entitatii
       \param float xSpeed      viteza cu care se deplaseaza entitatea pe axa Ox
       \param int[][] lvlData   matricea cu datele(tile-urile) fiecarui nivel
    */
    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData)
    {
        if(xSpeed > 0)
            return IsSolid(hitbox.x + xSpeed + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
        else
            return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }


    /*! \fn  public static boolean IsAllTileWalkable(int xStart, int xEnd, int y, int[][] levelMap)
       \brief Satbileste daca exista tile-uri solide intre doua puncte de pe aceeasi coordonata y a hartii.
       \

       \param int xStart        punctul de start
       \param int xEnd          punctul de stop
       \param int y             coordonata y
       \param int[][] lvlData   matricea cu datele(tile-urile) fiecarui nivel
    */
    public static boolean IsAllTileWalkable(int xStart, int xEnd, int y, int[][] levelMap)
    {
        for(int i=0; i<(xEnd-xStart); ++i)
        {
            if(IsSolid( y,(xStart+i), levelMap))
            {
                return false;
            }
            if(!IsSolid( (y + 1),(xStart + i), levelMap))
            {
                return false;
            }
        }
        return true;
    }

    /*! \fn  public static boolean IsSightClear(int [][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile)
       \brief Satbileste daca exista doua entitati "se vad" pe harta. Functia returneaza true daca intre cele doua entitati nu exista tile-uri solide.
       \

       \param int[][] lvlData   matricea cu datele(tile-urile) fiecarui nivel
       \param Rectangle2D.Float firsthitbox Hitboxul primei entitati
       \param Rectangle2D.Float secondhitbox Hitboxul celei de a doua entitati
       \param int yTile             coordonata y
    */
    public static boolean IsSightClear(int [][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile)
    {
        /// veirificam coordonatele x ale celor doua hitbox-uri
        int xFirstTile = (int)(firstHitbox.x / TILE_WIDTH);
        int xSecondTile = (int)(secondHitbox.x / TILE_WIDTH);

        if(xFirstTile > xSecondTile)
        {
            return IsAllTileWalkable(xSecondTile,xFirstTile,yTile,lvlData);
        }
        else
        {
            return IsAllTileWalkable(xFirstTile,xSecondTile,yTile,lvlData);
        }
    }


}


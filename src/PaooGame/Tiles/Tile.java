package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie
    public static Tile platformTile     = new PlatformTile(3);  /*!< Dala de tip platforma.*/
    public static Tile floorTile        = new FloorTile(6);     /*!< Dala de tip podea.*/
    public static Tile rightWallTile    = new RightWallTile(7); /*!< Dala de tip perete pe partea dreapta.*/
    public static Tile leftWallTile     = new LeftWallTile(8);  /*!< Dala de tip perete pe partea stanga.*/

    public static Tile dirtFloorTile    = new DirtTile(12);
    public static Tile leftWallDirt     = new LeftWallDirt(13);
    public static Tile rightWallDirt    = new RightWallDirt(14);
    public static Tile dirtPlatform    = new DirtPlatform(15);


    public static Tile closedDoorTile   = new ClosedDoorTile(16);
    public static Tile openDoorTile   = new OpenDoorTile(16);


    public static Tile floorRockTile   = new FloorRockTile(20);
    public static Tile leftRockWallTile   = new LeftRockWallTile(21);
    public static Tile rightRockWallTile   = new RightRockWallTile(22);
    public static Tile platformRockTile   = new RockPlatformTile(23);




    public static final int TILE_WIDTH  = 64;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 64;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}

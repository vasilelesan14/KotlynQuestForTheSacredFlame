package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class LeftWallTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip perete oreintat pe partea stanga.
 */
public class LeftWallTile extends Tile
{
    /*! \fn public LeftWallTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
    */
    public LeftWallTile(int id)
    {
        super(Assets.left_wall , id);
    }
}

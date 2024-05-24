package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class RightWallTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip perete oreintat pe partea dreapta.
 */
public class RightWallTile extends Tile
{
    /*! \fn public RightWallTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
    */
    public RightWallTile(int id)
    {
        super(Assets.right_wall , id);
    }
}

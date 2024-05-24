package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class FloorTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip podea/piatra.
 */
public class FloorTile extends Tile
{
    /*! \fn public FloorTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
    */
    public FloorTile(int id)
    {
        super(Assets.castle_floor , id);
    }
}

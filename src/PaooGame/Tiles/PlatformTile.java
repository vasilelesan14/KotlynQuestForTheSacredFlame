package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class PlatformTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip platforma.
 */
public class PlatformTile extends Tile
{
    /*! \fn public PlatformTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
    */
    public PlatformTile(int id)
    {
        super(Assets.castle_platform, id);
    }
}

package PaooGame.Levels;

import PaooGame.Entities.*;
import PaooGame.Game;
import PaooGame.GameStates.GameState;
import PaooGame.Graphics.Assets;
import PaooGame.Objects.Spike;
import PaooGame.Objects.Torch;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static PaooGame.Entities.Player.GetTorchNumber;
import static PaooGame.Utilities.Constants.EnemiesConstants.*;
import static PaooGame.Utilities.Constants.GameConstants.*;
import static PaooGame.Utilities.Constants.ObjectConstants.SPIKE;
import static PaooGame.Utilities.Constants.ObjectConstants.TORCH;

/*! \class public class LevelManager
    \brief Clasa se ocupa de manipularea nivelurilor si a datelor fiecarui nivel.

 */

public class LevelManager
{
    private Game game;
    private ArrayList<Level> levels;
    private static int levelIndex = 0;
    public LevelManager(Game game)
    {
        this.game = game;
        levels = new ArrayList<>();
        createAllLevels();
    }

    private void createAllLevels()
    {
        levels.add(new Level(Assets.firstLevelMap, Assets.lvl1_bck1, Assets.lvl1_bck2, Assets.lvl1_bck3));
        levels.add(new Level(Assets.secondLevelMap, Assets.lvl2_bck1, Assets.lvl2_bck2, Assets.lvl2_bck3));
        levels.add(new Level(Assets.thirdLevelMap, Assets.lvl3_bck1, Assets.lvl3_bck2, Assets.lvl3_bck3));
    }

    public void update()
    {}

    public void drawFog(Graphics g, int cameraOffset)
    {
        g.setColor(null);
        if(levelIndex == 0 || levelIndex == 2)
        {
            int circleSize = 230;
            BufferedImage fogFilter = new BufferedImage(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)fogFilter.getGraphics();
            g2.setColor(null);

            Area screenArea = new Area(new Rectangle2D.Float(0,0, VISIBLE_GAME_WIDTH,VISIBLE_GAME_HEIGHT));

            int xcircleCenter = (int)(game.getPlaying().getPlayer().getX() + TILE_WIDTH/2);
            int ycircleCenter = (int)(game.getPlaying().getPlayer().getY() + TILE_HEIGHT/2);

            float x = xcircleCenter - circleSize/2;
            float y = ycircleCenter - circleSize/2;

            Shape circleShape = new Ellipse2D.Float((x-cameraOffset)-game.getPlaying().getPlayer().getX(),y-game.getPlaying().getPlayer().getY(),(circleSize+50* GetTorchNumber()), (circleSize+50* GetTorchNumber()));
            Area lightArea = new Area(circleShape);

            screenArea.subtract(lightArea);

            Color color[] = new Color[6];
            float fraction[] = new float[6];

            color[0] = new Color(0,0,0,0f);
            color[1] = new Color(0,0,0,0.1f);
            color[2] = new Color(0,0,0,0.3f);
            color[3] = new Color(0,0,0,0.6f);
            color[4] = new Color(0,0,0,0.8f);
            color[5] = new Color(0,0,0,1f);

            fraction[0] = 0f;
            fraction[1] = 0.1f;
            fraction[2] = 0.3f;
            fraction[3] = 0.6f;
            fraction[4] = 0.8f;
            fraction[5] = 1f;

            RadialGradientPaint gradientPaint = new RadialGradientPaint((xcircleCenter - cameraOffset),ycircleCenter, (circleSize+50* GetTorchNumber()), fraction,color);
            g2.setPaint(gradientPaint);
            g2.fill(lightArea);
            g2.fill(screenArea);
            g2.dispose();
            g.drawImage(fogFilter,0,0,null);
        }
    }

    public void draw(Graphics g, int cameraOffset)
    {
        /// Desenarea fundalului

        drawBackground(g, levels.get(levelIndex).background_1 ,0,0,levels.get(levelIndex).background_1.getWidth(null),0);
        drawBackground(g, levels.get(levelIndex).background_2,0,0, levels.get(levelIndex).background_2.getWidth(null),(int)(cameraOffset*0.8));
        drawBackground(g, levels.get(levelIndex).background_3, 0,0,levels.get(levelIndex).background_3.getWidth(null),(int)(cameraOffset*0.4));


        for(int i=0;i<TILES_IN_HEIGHT;i++)
        {
            for(int j=0;j<TILES_IN_WIDTH;++j)
            {
                switch (levels.get(levelIndex).getLvlData()[i][j])
                {
                    case 3:
                        Tile.platformTile.Draw(g, j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 6:
                        Tile.floorTile.Draw(g, j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 7:
                        Tile.leftWallTile.Draw(g, j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 8:
                        Tile.rightWallTile.Draw(g, j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 12:
                        Tile.dirtFloorTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 13:
                        Tile.leftWallDirt.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 14:
                        Tile.rightWallDirt.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 15:
                        Tile.dirtPlatform.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 16:
                        if(game.getPlaying().getObjectManager().isAnyTorchActive()==true)
                            Tile.closedDoorTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT + 10);
                        else
                            Tile.openDoorTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT + 10);
                        break;

                    case 20:
                        Tile.floorRockTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 21:
                        Tile.leftRockWallTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 22:
                        Tile.rightRockWallTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                    case 23:
                        Tile.platformRockTile.Draw(g,j * Tile.TILE_WIDTH - cameraOffset, i * Tile.TILE_HEIGHT);
                        break;
                }
            }
        }


    }

    public void changeLevel()
    {
        levelIndex++;
        if(levelIndex >= levels.size())
        {
            levelIndex=0;
            System.out.println(" Joc terminat! ");
            GameState.state = GameState.MENU;
        }

        Level newLevel = levels.get(levelIndex);
        game.getPlaying().getPlayer().loadLevelData(newLevel.getLvlData());
        game.getPlaying().getEnemyManager().initEnemies(newLevel);
        game.getPlaying().setMaxLevelOffset(newLevel.getLevelOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);

        game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().GetLevelIndex());

    }

    public void drawBackground(Graphics g, Image image, int x, int y,int offset, int moveSpeed)
    {
        for(int i=0;i<5;++i)
            g.drawImage(image, i*offset-moveSpeed, y,image.getWidth(null), image.getHeight(null), null);
    }

    public static ArrayList<Spirit> GetSpirits(int [][]levelMap)
    {
        ArrayList<Spirit> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case SPIRIT:
                        list.add(new Spirit(j*TILE_WIDTH,i*TILE_HEIGHT));
                }
            }
        return list;
    }
    public static ArrayList<Demon> GetDemons(int [][]levelMap)
    {
        ArrayList<Demon> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case DEMON:
                        list.add(new Demon(j*TILE_WIDTH,i*TILE_HEIGHT));
                }
            }
        return list;
    }

    public static ArrayList<Lich> GetLichs(int [][]levelMap)
    {
        ArrayList<Lich> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case LICH:
                        list.add(new Lich(j*TILE_WIDTH,i*TILE_HEIGHT));
                }
            }
        return list;
    }

    public static ArrayList<Maze> GetMaze(int [][]levelMap)
    {
        ArrayList<Maze> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case MAZE:
                        list.add(new Maze(j*TILE_WIDTH,i*TILE_HEIGHT));
                }
            }
        return list;
    }
    public static ArrayList<Kali> GetKali(int [][]levelMap)
    {
        ArrayList<Kali> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case KALI:
                        list.add(new Kali(j*TILE_WIDTH,i*TILE_HEIGHT));
                }
            }
        return list;
    }

    public static ArrayList<Torch> GetTorches(int [][]levelMap)
    {
        ArrayList<Torch> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case TORCH:
                        list.add(new Torch(j*TILE_WIDTH,i*TILE_HEIGHT, TORCH));
                }
            }
        return list;
    }
    public static ArrayList<Spike> GetSpikes(int [][]levelMap)
    {
        ArrayList<Spike> list = new ArrayList<>();
        for(int i=0;i<TILES_IN_HEIGHT; ++i)
            for(int j = 0; j<TILES_IN_WIDTH;++j)
            {
                switch (levelMap[i][j])
                {
                    case SPIKE:
                        list.add(new Spike(j*TILE_WIDTH,i*TILE_HEIGHT, TORCH));
                }
            }
        return list;
    }

    public Level getLevel()
    {
        return levels.get(levelIndex);
    }
    public int getNumberOfLevels()
    {
        return levels.size();
    }
    public static int GetLevelIndex()
    {
        return levelIndex;
    }

    public static void setLevelIndex(int levelIndex)
    {
        LevelManager.levelIndex = levelIndex;
    }

}

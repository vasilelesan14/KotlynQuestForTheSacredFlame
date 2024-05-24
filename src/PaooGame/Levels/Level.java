package PaooGame.Levels;

import PaooGame.Entities.*;
import PaooGame.Objects.Spike;
import PaooGame.Objects.Torch;

import java.awt.*;
import java.util.ArrayList;

import static PaooGame.Utilities.Constants.GameConstants.*;

public class Level
{
    private int [][] lvlData;
    public Image background_1, background_2, background_3;

    private int levelTilesWide;
    private int maxTilesOffset;
    private int maxLevelOffset;

    private ArrayList<Torch> torches    = new ArrayList<>();
    private ArrayList<Spirit> spirits1  = new ArrayList<>();
    private ArrayList<Demon> demons1    = new ArrayList<>();
    private ArrayList<Spike> spikes1    = new ArrayList<>();
    private ArrayList<Lich> liches1    = new ArrayList<>();
    private ArrayList<Maze> maze1    = new ArrayList<>();
    private ArrayList<Kali> kali1    = new ArrayList<>();



    public Level(int[][] lvlData, Image background_1, Image background_2, Image background_3)
    {
        this.lvlData = lvlData;
        this.background_1 = background_1;
        this.background_2 = background_2;
        this.background_3 = background_3;

        createEnemies();
        createTorches();
        createSpikes();
        calcLevelOffset();
    }

    private void createSpikes()
    {
        spikes1 = LevelManager.GetSpikes(lvlData);
    }

    private void createTorches()
    {
        torches = LevelManager.GetTorches(lvlData);
    }

    private void createEnemies()
    {
        spirits1 = LevelManager.GetSpirits(lvlData);
        demons1 = LevelManager.GetDemons(lvlData);
        liches1 = LevelManager.GetLichs(lvlData);
        maze1 = LevelManager.GetMaze(lvlData);
        kali1 = LevelManager.GetKali(lvlData);
    }

    private void calcLevelOffset()
    {
        levelTilesWide = TILES_IN_WIDTH;
        maxTilesOffset = levelTilesWide - VISIBLE_TILES_WIDTH;
        maxLevelOffset = TILE_WIDTH * maxTilesOffset;
    }
    public int[][] getLvlData()
    {
        return lvlData;
    }
    public int getLevelOffset()
    {
        return maxLevelOffset;
    }
    public ArrayList<Spirit> getSpirits1()
    {
        return spirits1;
    }

    public ArrayList<Demon> getDemons1()
    {
        return demons1;
    }
    public ArrayList<Torch> getTorches1()
    {
        return torches;
    }
    public ArrayList<Spike> getSpikes1()
    {
        return spikes1;
    }
    public ArrayList<Torch> getTorches()
    {
        return torches;
    }
    public ArrayList<Lich> getLiches1()
    {
        return liches1;
    }
    public ArrayList<Maze> getMaze1()
    {
        return maze1;
    }
    public ArrayList<Kali> getKali1()
    {
        return kali1;
    }


}

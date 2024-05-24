package PaooGame.Graphics;



import PaooGame.Utilities.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static PaooGame.Utilities.Constants.EnemiesConstants.DemonConstants.*;
import static PaooGame.Utilities.Constants.EnemiesConstants.LichConstants.*;
import static PaooGame.Utilities.Constants.EnemiesConstants.*;
import static PaooGame.Utilities.Constants.EnemiesConstants.MazeConstants.*;
import static PaooGame.Utilities.Constants.GameConstants.*;


/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    /// Referinte catre elementele grafice (dale) utilizate in joc.

    public static BufferedImage playerAnimations[][];   /*!< Tablou bidimensional unde sunt stocate imaginile corespunzatoare animatiilor playerului.*/
    public static BufferedImage spiritAnimations[][];   /*!< Tablou bidimensional unde sunt stocate imaginile corespunzatoare animatiilor inamicului spirit.*/
    public static BufferedImage lichAnimations[][];
    public static BufferedImage demonAnimations[][];
    public static BufferedImage mazeAnimations[][];
    public static BufferedImage kaliAnimations[][];
    public static BufferedImage castle_floor;   /*!< Imaginea corespunzatoare podelei castelului.*/
    public static BufferedImage right_wall;     /*!< Imaginea corespunzatoare peretelui orientat spre dreapta a castelului.*/
    public static BufferedImage left_wall;      /*!< Imaginea corespunzatoare peretelui orientat spre stanga a castelului.*/
    public static BufferedImage castle_platform;       /*!< Imaginea corespunzatoare platformelor castelului.*/

    public static BufferedImage dirt;       /*!< Imaginea corespunzatoare platformelor castelului.*/
    public static BufferedImage dirt_platform;       /*!< Imaginea corespunzatoare platformelor castelului.*/
    public static BufferedImage left_dirt;       /*!< Imaginea corespunzatoare platformelor castelului.*/
    public static BufferedImage right_dirt;       /*!< Imaginea corespunzatoare platformelor castelului.*/


    public static BufferedImage right_rock;
    public static BufferedImage left_rock;
    public static BufferedImage floor_rock;
    public static BufferedImage platform_rock;


    public static BufferedImage closedDoor;
    public static BufferedImage openDoor;
    public static BufferedImage spikes;



    public static BufferedImage []torch;        /*!< Tablou bidimensional unde sunt stocate imaginile corespunzatoare animatiilor tortelor colectabile.*/
    public static BufferedImage light;
    public static Image lvl1_bck1;              /*!< Primul strat corespunzator fundalului scenei din castel.*/
    public static Image lvl1_bck2;              /*!< Al doilea strat corespunzator fundalului scenei din castel.*/
    public static Image lvl1_bck3;              /*!< Al treilea strat corespunzator fundalului scenei din castel.*/

    public static Image lvl2_bck1;
    public static Image lvl2_bck2;
    public static Image lvl2_bck3;


    public static Image lvl3_bck1;
    public static Image lvl3_bck2;
    public static Image lvl3_bck3;


    public static Image menu_bck;
    public static Image pause_bck;
    public static int [][] firstLevelMap = null;    /*!< Tablou bidimesnional ce retine id-urile tile-urilor ce constituie harta primului nivel.*/
    public static int [][] secondLevelMap = null;    /*!< Tablou bidimesnional ce retine id-urile tile-urilor ce constituie harta primului nivel.*/

    public static int [][] thirdLevelMap = null;
    public static BufferedImage playButton;
    public static BufferedImage quitButton;
    public static BufferedImage optionButton;
    public static BufferedImage restartButton;
    public static BufferedImage resumeButton;
    public static BufferedImage positiveButton;
    public static BufferedImage negativeButton;
    public static BufferedImage load_gameButton;
    public static BufferedImage save_gameButton;
    public static BufferedImage homeButton;
    public static BufferedImage next_levelButton;
    public static BufferedImage instructionsButton;

    public static BufferedImage musicText;
    public static BufferedImage soundText;


    public static BufferedImage torchImageScor;
    public static BufferedImage panelScor;

    public static BufferedImage empty_menu_panel;
    public static BufferedImage pause_panel;
    public static BufferedImage options_panel;
    public static BufferedImage level_completed_panel;
    public static BufferedImage game_over_panel;
    public static BufferedImage game_over_header;


    public static BufferedImage statusBar;
    public static BufferedImage hearth;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        SpriteSheet sheet = null;
        castle_floor = ImageLoader.LoadImage("/textures/Level1/floor2.png");
        right_wall = ImageLoader.LoadImage("/textures/Level1/wall_right1.png");
        left_wall = ImageLoader.LoadImage("/textures/Level1/wall_left1.png");
        castle_platform = ImageLoader.LoadImage("/textures/Level1/platform1.png");

        dirt = ImageLoader.LoadImage("/textures/Level2/dirt.png");
        dirt_platform = ImageLoader.LoadImage("/textures/Level2/dirt_platform.png");
        left_dirt = ImageLoader.LoadImage("/textures/Level2/left_dirt.png");
        right_dirt = ImageLoader.LoadImage("/textures/Level2/right_dirt.png");

        right_rock = ImageLoader.LoadImage("/textures/Level3/RightRockWall.png");
        left_rock = ImageLoader.LoadImage("/textures/Level3/LeftRockWall.png");
        floor_rock = ImageLoader.LoadImage("/textures/Level3/FloorRock.png");
        platform_rock = ImageLoader.LoadImage("/textures/Level3/RockPlatform.png");


        light = ImageLoader.LoadImage("/textures/Torch/light.png");

        closedDoor = ImageLoader.LoadImage("/textures/door1.png");
        openDoor = ImageLoader.LoadImage("/textures/door2.png");

        spikes = ImageLoader.LoadImage("/textures/spikes.png");

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Torch/torch.png"));
        torch = new BufferedImage[8];
        for(int i=0;i<4;++i)
        {
            torch[i] = sheet.crop(i,0);
        }
        for(int i=0;i<4;++i)
        {
            torch[i+4] = sheet.crop(i,1);
        }

        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavaler.png"));

        /*! \brief Se incarca sprite-urile corespunzatoare fiecarei actiuni in cate un vector de imagini.*/

        playerAnimations = new BufferedImage[9][8];

        /*! Sprite-urile de idle dreapta ale cavalerului.*/
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerIdleRight.png"));
        playerAnimations[0][0] = sheet.crop(0,0);

        /*! Sprite-urile de idle stanga ale cavalerului.*/
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerIdleLeft.png"));
        playerAnimations[1][0] = sheet.crop(0,0);

        /*! Sprite-urile de miscare ale cavalerului la dreapta.*/
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerMoveRight.png"));
        for(int i=0; i<8; ++i )
        {
            /// Imaginile din sprite au spatiu foarte mare intre ele (de inca un tile),
            /// asa ca iau imaginile din doua in doua.

            playerAnimations[2][i] = sheet.crop(i*2,0);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerMoveLeft.png"));
        for(int i=0; i<8; i++)
        {
            playerAnimations[3][i] = sheet.crop(i*2,0);
        }

        /*! Sprite-urile de atac dreapta ale cavalerului.*/
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerAttackingRight.png"));
        for(int i=0; i<6; i++)
        {
            playerAnimations[6][i] = sheet.crop(i*2,0);
        }

        /*! Sprite-urile de salt dreapta ale cavalerului.*/
        playerAnimations[4][0] = sheet.crop(10,0);

        /*! Sprite-urile de atac stanga ale cavalerului.*/
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerAttackingLeft.png"));
        for(int i=0; i<6; i++)
        {
            playerAnimations[7][i] = sheet.crop(i*2,0);
        }

        /*! Sprite-urile de salt stanga ale cavalerului.*/
        playerAnimations[5][0] = sheet.crop(10,0);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Player/cavalerDead.png"));
        for(int i=0;i<6;++i)
        {
            playerAnimations[Constants.PlayerConstants.DEAD][i] = sheet.crop(i,0);
        }


        /*! Sprite-urile corespunzatoare spiritului*/
        spiritAnimations = new BufferedImage[2][6];
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Spirit/spiritMovingRight.png"));
        for(int i=0;i<6;++i)
        {
            spiritAnimations[0][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Spirit/spiritMovingLeft.png"));
        for(int i=0;i<6;++i)
        {
            spiritAnimations[1][i] = sheet.crop(i,0);
        }



        lichAnimations = new BufferedImage[5][9];
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Lich/lichMoveRight.png"));
        for(int i=0;i<9;++i)
        {
            lichAnimations[LICH_MOVE_RIGHT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Lich/lichMoveLeft.png"));
        for(int i=0;i<9;++i)
        {
            lichAnimations[LICH_MOVE_LEFT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Lich/lichAttackRight.png"));
        for(int i=0;i<6;++i)
        {
            lichAnimations[LICH_ATTACK_RIGHT][i] = sheet.crop(i*3,0);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Lich/lichAttackLeft.png"));
        for(int i=0;i<6;++i)
        {
            lichAnimations[LICH_ATTACK_LEFT][i] = sheet.crop((i*3),0);
        }
        lichAnimations[LICH_ATTACK_LEFT][3] = ImageLoader.LoadImage("/textures/Lich/lichAttackLeft_4.png");
        lichAnimations[LICH_ATTACK_LEFT][4] = ImageLoader.LoadImage("/textures/Lich/lichAttackLeft_5.png");
        lichAnimations[LICH_ATTACK_LEFT][5] = ImageLoader.LoadImage("/textures/Lich/lichAttackLeft_6.png");

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Lich/lichDead.png"));
        for(int i=0;i<6;++i)
        {
            lichAnimations[DEAD][i] = sheet.crop(i,0);
        }



        demonAnimations = new BufferedImage[5][9];
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Demon/demonMoveRight.png"));
        for(int i=0;i<9;++i)
        {
            demonAnimations[DEMON_MOVE_RIGHT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Demon/demonMoveLeft.png"));
        for(int i=0;i<9;++i)
        {
            demonAnimations[DEMON_MOVE_LEFT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Demon/demonAttackRight.png"));
        for(int i=0;i<6;++i)
        {
            demonAnimations[DEMON_ATTACK_RIGHT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Demon/demonAttackLeft.png"));
        for(int i=0;i<6;++i)
        {
            demonAnimations[DEMON_ATTACK_LEFT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Demon/demonDead.png"));
        for(int i=0;i<6;++i)
        {
            demonAnimations[DEAD][i] = sheet.crop(i,0);
        }



        mazeAnimations = new BufferedImage[5][9];
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Maze/mazeMoveRight.png"));
        for(int i=0;i<9;++i)
        {
            mazeAnimations[MAZE_MOVE_RIGHT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Maze/mazeMoveLeft.png"));
        for(int i=0;i<9;++i)
        {
            mazeAnimations[MAZE_MOVE_LEFT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Maze/mazeAttackRight.png"));
        for(int i=0;i<8;++i)
        {
            mazeAnimations[MAZE_ATTACK_RIGHT][i] = sheet.crop(i*3,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Maze/mazeAttackLeft.png"));
        for(int i=0;i<8;++i)
        {
            mazeAnimations[MAZE_ATTACK_LEFT][i] = sheet.crop(i*3,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Maze/mazeDead.png"));
        for(int i=0;i<6;++i)
        {
            mazeAnimations[DEAD][i] = sheet.crop(i,0);
        }



        kaliAnimations = new BufferedImage[5][9];
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Kali/kaliMoveRight.png"));
        for(int i=0;i<9;++i)
        {
            kaliAnimations[MOVE_RIGHT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Kali/kaliMoveLeft.png"));
        for(int i=0;i<9;++i)
        {
            kaliAnimations[MOVE_LEFT][i] = sheet.crop(i,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Kali/kaliAttackRight.png"));
        for(int i=0;i<8;++i)
        {
            kaliAnimations[ATTACK_RIGHT][i] = sheet.crop(i*3,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Kali/kaliAttackLeft.png"));
        for(int i=0;i<8;++i)
        {
            kaliAnimations[ATTACK_LEFT][i] = sheet.crop(i*3,0);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Kali/kaliDead.png"));
        for(int i=0;i<6;++i)
        {
            kaliAnimations[DEAD][i] = sheet.crop(i,0);
        }


        /// Se obtin imaginile corespunzatoare background-ului
        BufferedImage temp;
        temp = ImageLoader.LoadImage("/textures/Level1/castle_1.png");
        lvl1_bck1 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        temp = ImageLoader.LoadImage("/textures/Level1/castle_2.png");
        lvl1_bck2 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        temp = ImageLoader.LoadImage("/textures/Level1/castle_3_1.png");
        lvl1_bck3 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        //temp = ImageLoader.LoadImage("/textures/Level1/castle_4.png");
        //lvl1_bck4 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, 550, Image.SCALE_SMOOTH);


        temp = ImageLoader.LoadImage("/textures/Level2/lvl2_bck1.png");
        lvl2_bck1 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        temp = ImageLoader.LoadImage("/textures/Level2/lvl2_bck2_2.png");
        lvl2_bck2 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        temp = ImageLoader.LoadImage("/textures/Level2/lvl2_bck3_1.png");
        lvl2_bck3 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);



        temp = ImageLoader.LoadImage("/textures/Level3/BG_1_1.png");
        lvl3_bck1 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        temp = ImageLoader.LoadImage("/textures/Level3/BG_1_2.png");
        lvl3_bck2 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

        temp = ImageLoader.LoadImage("/textures/Level3/BG_1_3.png");
        lvl3_bck3 = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);



        temp = ImageLoader.LoadImage("/textures/UserInterface/menu_bck_4.jfif");
        menu_bck = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);

//        temp = ImageLoader.LoadImage("/textures/UserInterface/menu_bck_3_final.jpg");
//        pause_bck = temp.getScaledInstance(VISIBLE_GAME_WIDTH, VISIBLE_GAME_HEIGHT, Image.SCALE_SMOOTH);




        empty_menu_panel        = ImageLoader.LoadImage("/textures/UserInterface/empty panel.png");
        pause_panel             = ImageLoader.LoadImage("/textures/UserInterface/paused panel.png");
        options_panel           = ImageLoader.LoadImage("/textures/UserInterface/options panel.png");
        level_completed_panel   = ImageLoader.LoadImage("/textures/UserInterface/level completed panel.png");
        game_over_panel         = ImageLoader.LoadImage("/textures/UserInterface/game over panel.png");

        game_over_header        = ImageLoader.LoadImage("/textures/UserInterface/game over header.png");

        musicText = ImageLoader.LoadImage("/textures/UserInterface/music text.png");
        soundText = ImageLoader.LoadImage("/textures/UserInterface/sound text.png");

        playButton = ImageLoader.LoadImage("/textures/UserInterface/play button.png");
        quitButton = ImageLoader.LoadImage("/textures/UserInterface/quit button.png");
        optionButton = ImageLoader.LoadImage("/textures/UserInterface/options button.png");
        resumeButton = ImageLoader.LoadImage("/textures/UserInterface/resume button.png");
        restartButton = ImageLoader.LoadImage("/textures/UserInterface/restart button.png");
        positiveButton = ImageLoader.LoadImage("/textures/UserInterface/positeve button.png");
        negativeButton = ImageLoader.LoadImage("/textures/UserInterface/negative button.png");
        load_gameButton = ImageLoader.LoadImage("/textures/UserInterface/load game button.png");
        save_gameButton = ImageLoader.LoadImage("/textures/UserInterface/save game button.png");
        homeButton = ImageLoader.LoadImage("/textures/UserInterface/home button.png");
        next_levelButton = ImageLoader.LoadImage("/textures/UserInterface/next level button.png");


        statusBar = ImageLoader.LoadImage("/textures/UserInterface/status bar.png");
        hearth = ImageLoader.LoadImage("/textures/UserInterface/hearth.png");

        torchImageScor = ImageLoader.LoadImage("/textures/UserInterface/torch.png");
        panelScor = ImageLoader.LoadImage("/textures/UserInterface/empty_bar.png");

        // initializarea hartii folosite in primul nivel
        firstLevelMap   = GetLevelData("res/textures/Maps/PlatformMap_LVL1.txt");
        secondLevelMap  = GetLevelData("res/textures/Maps/PlatformMap_LVL2.txt");
        thirdLevelMap   = GetLevelData("res/textures/Maps/PlatformMap_LVL3.txt");



        /// incarcarea fontului utilizat


    }

    public static void InitFont(Font font)
    {
        try
        {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/Midfield-Rounded.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        }
        catch (IOException | FontFormatException e)
        {
            e.printStackTrace();
        }
    }

    /*! \fn public static int[][] mapInit(String filename)
        \brief Functia initializeaza mapa corespunzatoare id-urilor tile-urilor utilizate in nivel.

        Aceasta functie poate primeste ca argument calea corespunzatoare fisierului
        text in care este stocata mapa si o incarca intr-un tablou bidimensional cu
        dimensiunile jocului exprimat in numar de tile-uri.
     */
    public static int[][] GetLevelData(String filename)
    {
        File file = new File(filename);
        int [][] map = null;
        try
        {
            Scanner scanner = new Scanner(file);
            map = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
            for(int i=0;i<TILES_IN_HEIGHT;++i)
            {
                for(int j=0; j<TILES_IN_WIDTH; ++j)
                {
                    map[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
        }catch (FileNotFoundException e)
        {
            System.out.println("Fișierul nu a fost găsit!");
            e.printStackTrace();
        }
        return map;
    }



}

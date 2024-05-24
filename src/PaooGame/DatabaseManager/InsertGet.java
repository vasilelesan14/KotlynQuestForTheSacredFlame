package PaooGame.DatabaseManager;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*! \class public class InsertGet
    \brief Clasa se ocupa de manipularea bazei de date.

    Aceasta cuprinde metode statice pentru a putea fi apelate usor.
 */
public class InsertGet
{
    /*! \fn SaveIntoDatabase(String nume_fisier, String nume_tabela, int levelIndex, int currentHealth, int torchNumber, float posX, float posY)
         \brief Functia se ocupa de salvarea datelor in baza de date.

     */
    public static void SaveIntoDatabase(String nume_fisier, String nume_tabela, int levelIndex, int currentHealth, int torchNumber, float posX, float posY) {
        Connection c = null;
        Statement stmt = null;


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Facultate\\An II\\Semestrul II\\Proiectarea Aplicatiilor Orientate Obiecte\\Proiect\\Etapa3\\KotlynGame Etapa III\\gamedatabase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO " + nume_tabela + " ( LevelIndex, CurrentHealth, TorchNumber, PosX, PosY ) " + " VALUES (" + levelIndex + "," + currentHealth + "," + torchNumber + "," + posX + "," + posY + ");" ;

            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.out.println("Eroare la insert!!!");
            e.printStackTrace();
            // Optional rollback in case of error
            try {
                if (c != null) {
                    c.rollback();

                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }




    /*! \fn LoadLevelIndex(String nume_fisier, String nume_tabela)
         \brief Functia se ocupa de incarcarea indexului nivelului din baza de date.

     */
    public static int LoadLevelIndex(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Facultate\\An II\\Semestrul II\\Proiectarea Aplicatiilor Orientate Obiecte\\Proiect\\Etapa3\\KotlynGame Etapa III\\gamedatabase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("LevelIndex");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }

    public static int LoadCurrentHealth(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Facultate\\An II\\Semestrul II\\Proiectarea Aplicatiilor Orientate Obiecte\\Proiect\\Etapa3\\KotlynGame Etapa III\\gamedatabase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("CurrentHealth");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }

    public static int LoadTorchNumber(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Facultate\\An II\\Semestrul II\\Proiectarea Aplicatiilor Orientate Obiecte\\Proiect\\Etapa3\\KotlynGame Etapa III\\gamedatabase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("TorchNumber");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }

    public static float LoadXPosition(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Facultate\\An II\\Semestrul II\\Proiectarea Aplicatiilor Orientate Obiecte\\Proiect\\Etapa3\\KotlynGame Etapa III\\gamedatabase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("PosX");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }
    public static float LoadYPosition(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\Facultate\\An II\\Semestrul II\\Proiectarea Aplicatiilor Orientate Obiecte\\Proiect\\Etapa3\\KotlynGame Etapa III\\gamedatabase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("PosY");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }
}

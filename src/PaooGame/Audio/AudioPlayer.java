package PaooGame.Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class AudioPlayer
{
    public static int meniu = 0;
    public static int level1 = 1;
    public static int level2 = 2;
    public static int level3 = 3;
    public static int pauze = 4;

    private Clip[] songs;
    private int currentSong;
    private float volume = 0.75f;
    private boolean songMute;

    public AudioPlayer() throws UnsupportedAudioFileException, IOException
    {
        loadSongs();
        playMusic(meniu);
    }

    private void loadSongs() throws UnsupportedAudioFileException, IOException
    {
        String[] names = {"menu", "level1", "level2", "level3", "pause"};
        songs = new Clip[names.length];
        for(int i=0 ; i<songs.length;i++)
        {
            songs[i] = getClip(names[i]);
        }
    }

    private Clip getClip(String name) throws UnsupportedAudioFileException, IOException
    {
        URL url = getClass().getResource("/music/" + name + ".wav");
        AudioInputStream audio;

        try
        {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public void playMusic(int music)
    {
        stopSong();
        currentSong = music;
        updateMusicVolume();
        songs[currentSong].setMicrosecondPosition(0);
        songs[currentSong].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void toggleSongMute()
    {
        this.songMute = !songMute;
        for(Clip c: songs)
        {
            BooleanControl control = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            control.setValue(songMute);
        }
    }

    private void updateMusicVolume()
    {
        FloatControl gainControl = (FloatControl) songs[currentSong].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range*volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    public void setVolume(float volume)
    {
        this.volume = volume;
        updateMusicVolume();

    }
    public void stopSong()
    {
        if(songs[currentSong].isActive())
        {
            songs[currentSong].stop();
        }
    }
    public void setLevelSong(int levelIndex)
    {
        if(levelIndex == 0)
            playMusic(level1);
        if(levelIndex == 1)
            playMusic(level2);
        if(levelIndex == 2)
            playMusic(level3);

    }
    public void levelCompleted()
    {}
}

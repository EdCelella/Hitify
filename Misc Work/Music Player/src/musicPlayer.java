import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.util.concurrent.TimeUnit;

import java.io.*;
import java.net.*;
import java.nio.channels.FileChannel;

public class musicPlayer{

    
    public static void main(String[] args) throws Exception{

    String source = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/music/test.mp3";
    String dest = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/play/playSong.wav";

    FileChannel sourceChannel = null;
    FileChannel destChannel = null;
    try {
        sourceChannel = new FileInputStream(source).getChannel();
        destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        destChannel.close();
    }catch(IOException e){}

    String playSong = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Sams Work/TestingCoursework/res/Music/Conv/playSong.wav";

    String musicFile = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/music/test.wav";

    // open the sound file as a Java input stream
    InputStream in = new FileInputStream(playSong);

    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);

    }
    
}

/*
    TimeUnit.SECONDS.sleep(15);

    AudioPlayer.player.stop(audioStream);

    TimeUnit.SECONDS.sleep(5);

    AudioPlayer.player.start(audioStream);
*/
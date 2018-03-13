import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.util.concurrent.TimeUnit;

public class musicPlayer{

    
    public static void main(String[] args) throws Exception{
    // open the sound file as a Java input stream
    String musicFile = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/music/test.wav";
    InputStream in = new FileInputStream(musicFile);

    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);

    TimeUnit.SECONDS.sleep(10);

    AudioPlayer.player.stop(audioStream);

    

    }
    
}


//"/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/music/Ed Sheeran,Dive.mp3"
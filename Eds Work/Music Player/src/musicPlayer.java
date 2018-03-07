import java.io.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musicPlayer extends Application{

    @Override
    public void start(Stage primaryStage) {
        String bip = "/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/music/Ed Sheeran,Dive.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}


//"/Users/edwardcelella/Documents/University/Systems Software/Shitify/Eds Work/Music Player/music/Ed Sheeran,Dive.mp3"
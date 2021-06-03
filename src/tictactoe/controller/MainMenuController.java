package tictactoe.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class MainMenuController extends Controller{

    private final URL bgURL;

    private final URL sfxSelectURL;
    private final URL sfxClickURL;

    private MediaPlayer sfxSelectPlayer;
    private MediaPlayer sfxClickPlayer;

    private boolean isMuted;

    public MainMenuController (){
        bgURL = getClass().getResource("/tictactoe/view/resources/MainMenu.mp4");
        sfxSelectURL = getClass().getResource("/tictactoe/view/resources/select_soundtrack.mp3");
        sfxClickURL = getClass().getResource("/tictactoe/view/resources/click_soundtrack.mp3");
        isMuted = false;
    }

    @FXML
    private ImageView boardID;

    @FXML
    private ImageView gameTitle;

    @FXML
    private MediaView mediaView;

    @FXML
    private MediaView sfx;

    @FXML
    private ImageView playButton;

    @FXML
    private ImageView selectButton;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView muteButton;

    @FXML
    private ImageView selectLevelText;

    @FXML
    private ImageView level1;

    @FXML
    private ImageView level0;

    @FXML
    private ImageView level2;

    @FXML
    private ImageView selectRick;

    @FXML
    private ImageView selectPaul;

    @FXML
    private ImageView selectRandy;

    private void setPage(int index){

        switch (index){
            case 1:
                boardID.setDisable(false);
                boardID.setVisible(true);

                gameTitle.setDisable(false);
                gameTitle.setVisible(true);

                playButton.setDisable(false);
                playButton.setVisible(true);

                selectButton.setDisable(false);
                selectButton.setVisible(true);

                exitButton.setDisable(false);
                exitButton.setVisible(true);

                selectLevelText.setDisable(true);
                selectLevelText.setVisible(false);

                level0.setDisable(true);
                level0.setVisible(false);

                level1.setDisable(true);
                level1.setVisible(false);

                level2.setDisable(true);
                level2.setVisible(false);

                selectRandy.setDisable(true);
                selectRandy.setVisible(false);

                selectPaul.setDisable(true);
                selectPaul.setVisible(false);

                selectRick.setDisable(true);
                selectRick.setVisible(false);
                break;
            case 2:
                boardID.setDisable(true);
                boardID.setVisible(false);

                gameTitle.setDisable(true);
                gameTitle.setVisible(false);

                playButton.setDisable(true);
                playButton.setVisible(false);

                selectButton.setDisable(true);
                selectButton.setVisible(false);

                exitButton.setDisable(true);
                exitButton.setVisible(false);

                selectLevelText.setDisable(false);
                selectLevelText.setVisible(true);

                level0.setDisable(false);
                level0.setVisible(true);

                level1.setDisable(false);
                level1.setVisible(true);

                level2.setDisable(false);
                level2.setVisible(true);

                selectRandy.setDisable(false);
                selectRandy.setVisible(true);

                selectPaul.setDisable(false);
                selectPaul.setVisible(true);

                selectRick.setDisable(false);
                selectRick.setVisible(true);
                break;
        }
    }

    @FXML
    public void exitButtonAction() {
        controller.getStage().close();
    }

    @FXML
    public void muteButtonAction(){
        isMuted = !isMuted;
        mediaView.getMediaPlayer().setMute(isMuted);
    }

    @FXML
    public void playButtonAction() throws IOException {
        sfx.setMediaPlayer(sfxClickPlayer);
        sfx.getMediaPlayer().play();

        mediaView.getMediaPlayer().stop();
        controller.changeScene(MainController.PAGE_BOARD);
    }

    @FXML
    public void selectButtonAction(){
        sfx.setMediaPlayer(sfxClickPlayer);
        sfx.getMediaPlayer().play();

        setPage(2);

        sfx.setMediaPlayer(sfxSelectPlayer);
    }

    @FXML
    void selectPaulAction() {
        sfx.setMediaPlayer(sfxClickPlayer);
        sfx.getMediaPlayer().play();

        controller.getGame().setLevel(1);
        boardID.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/id_paul.png")));
        setPage(1);

        sfx.setMediaPlayer(sfxSelectPlayer);
    }

    @FXML
    void selectRandyAction() {
        sfx.setMediaPlayer(sfxClickPlayer);
        sfx.getMediaPlayer().play();

        controller.getGame().setLevel(0);
        boardID.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/id_randy.png")));
        setPage(1);

        sfx.setMediaPlayer(sfxSelectPlayer);
    }

    @FXML
    void selectRickAction() {
        sfx.setMediaPlayer(sfxClickPlayer);
        sfx.getMediaPlayer().play();

        controller.getGame().setLevel(2);
        boardID.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/id_rick.png")));
        setPage(1);

        sfx.setMediaPlayer(sfxSelectPlayer);
    }

    /**
     * Below are the Mouse Events
     */
    @FXML
    public void muteButtonIn(){
        if(isMuted){
            muteButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/muteButton_Activated_Hovered.png")));
        }
        else{
            muteButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/muteButton_Hovered.png")));
        }
        sfx.getMediaPlayer().play();
    }

    @FXML
    public void muteButtonOut(){
        if(isMuted){
            muteButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/muteButton_Activated.png")));
        }
        else{
            muteButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/muteButton.png")));
        }
    }

    @FXML
    public void exitIn() {
        exitButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/exitButton_Hovered.png")));
        sfx.getMediaPlayer().play();
    }

    @FXML
    public void exitOut() {
        exitButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/exitButton.png")));
    }

    @FXML
    public void playIn() {
        playButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/playButton_Hovered.png")));
        sfx.getMediaPlayer().play();
    }

    @FXML
    public void playOut() {
        playButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/playButton.png")));
    }

    @FXML
    public void selectIn() {
        selectButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/selectButton_Hovered.png")));
        sfx.getMediaPlayer().play();
    }

    @FXML
    public void selectOut() {
        selectButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/selectButton.png")));
    }

    @FXML
    void selectPaulIn() {
        selectPaul.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/select_alone_Hovered.png")));
        selectLevelText.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/paul.png")));
        sfx.getMediaPlayer().play();
    }

    @FXML
    void selectPaulOut() {
        selectPaul.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/select_alone.png")));
        selectLevelText.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/selectLevelText.png")));
    }

    @FXML
    void selectRandyIn() {
        selectRandy.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/select_alone_Hovered.png")));
        selectLevelText.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/randy.png")));
        sfx.getMediaPlayer().play();
    }

    @FXML
    void selectRandyOut() {
        selectRandy.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/select_alone.png")));
        selectLevelText.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/selectLevelText.png")));
    }

    @FXML
    void selectRickIn() {
        selectRick.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/select_alone_Hovered.png")));
        selectLevelText.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/rick.png")));
        sfx.getMediaPlayer().play();
    }

    @FXML
    void selectRickOut() {
        selectRick.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/select_alone.png")));
        selectLevelText.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/selectLevelText.png")));
    }

    @FXML
    public void initialize() {

        setPage(1);

        Media media = new Media(bgURL.toString());
        Media sfxSelect = new Media(sfxSelectURL.toString());
        Media sfxClick = new Media(sfxClickURL.toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        sfxSelectPlayer = new MediaPlayer(sfxSelect);
        sfxClickPlayer = new MediaPlayer(sfxClick);

        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        sfxClickPlayer.setOnEndOfMedia(() -> {
            sfxClickPlayer.stop();
            sfxClickPlayer.seek(Duration.ZERO);
        });

        sfxSelectPlayer.setOnEndOfMedia(() -> {
            sfxSelectPlayer.stop();
            sfxSelectPlayer.seek(Duration.ZERO);
        });

        mediaView.setMediaPlayer(mediaPlayer);
        sfx.setMediaPlayer(sfxSelectPlayer);

        mediaPlayer.play();
    }
}

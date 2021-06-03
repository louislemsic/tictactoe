package tictactoe.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;

public class BoardController extends Controller{

    private final URL resource;
    private final URL sfxSelectURL;
    private final URL sfxClickURL;
    private MediaPlayer sfxSelectPlayer;
    private MediaPlayer sfxClickPlayer;

    private ImageView element;
    private boolean blank;
    private Timeline timeline;
    private final double millispeed;

    public BoardController (){

        resource = getClass().getResource("/tictactoe/view/resources/board_soundtrack.mp3");
        sfxSelectURL = getClass().getResource("/tictactoe/view/resources/select_soundtrack.mp3");
        sfxClickURL = getClass().getResource("/tictactoe/view/resources/click_soundtrack.mp3");
        blank = true;
        millispeed = 200; //<-- change AI move speed here
    }

    private void getComputerMove(){

        block.setDisable(false);
        imageText1.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/turn0.png")));

        if (!blank){

            Pair<Integer, Integer> moves = controller.getGame().getComputerMove();

            int x = moves.getKey();
            int y = moves.getValue();

            if (x == 0 && y == 0){
                checkElement();
                stackPane00.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane00.setDisable(true);
            }
            else if (x == 1 && y == 0){
                checkElement();
                stackPane10.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane10.setDisable(true);
            }
            else if (x == 2 && y == 0){
                checkElement();
                stackPane20.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane20.setDisable(true);
            }
            else if (x == 0 && y == 1){
                checkElement();
                stackPane01.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane01.setDisable(true);
            }
            else if (x == 1 && y == 1){
                checkElement();
                stackPane11.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane11.setDisable(true);
            }
            else if (x == 2 && y == 1){
                checkElement();
                stackPane21.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane21.setDisable(true);
            }
            else if (x == 0 && y == 2){
                checkElement();
                stackPane02.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane02.setDisable(true);
            }
            else if (x == 1 && y == 2){
                checkElement();
                stackPane12.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane12.setDisable(true);
            }
            else if (x == 2 && y == 2){
                checkElement();
                stackPane22.getChildren().add(element);
                controller.getGame().changeElement();
                stackPane22.setDisable(true);
            }

            blank = true;
            imageText1.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/turn1.png")));
            block.setDisable(true);
            checkGameOver();
        }
        else
            blank = false;
    }

    private void checkElement(){
        if(controller.getGame().getCurrentElement() == 'X'){
            element = new ImageView(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/X.png")));
        }
        else
            element = new ImageView(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/O.png")));
    }

    private boolean checkGameOver(){

        Pair<Boolean, Boolean> result = controller.getGame().isGameOver();

        if (result.getKey()){
            disable();

            if(!result.getValue()){
                if((controller.getTurn() == 1 && controller.getGame().getWinningLetter() == 'X') || (controller.getTurn() == 0 && controller.getGame().getWinningLetter() == 'O') ){
                    imageText3.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/humanWins.png")));
                }
                else{
                    imageText3.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/aiWins.png")));
                }

            }
            else {
                imageText3.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/draw.png")));
            }

            imageText1.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/gameOver.png")));

            againButton.setVisible(true);
            againButton.setDisable(false);
            return true;
        }
        return false;
    }

    @FXML
    private ImageView imageText1;

    @FXML
    private ImageView imageText2;

    @FXML
    private ImageView imageText3;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView againButton;

    @FXML
    private MediaView mediaView;

    @FXML
    private MediaView sfx;

    @FXML
    private StackPane stackPane00;

    @FXML
    private StackPane stackPane10;

    @FXML
    private StackPane stackPane20;

    @FXML
    private StackPane stackPane01;

    @FXML
    private StackPane stackPane11;

    @FXML
    private StackPane stackPane21;

    @FXML
    private StackPane stackPane02;

    @FXML
    private StackPane stackPane12;

    @FXML
    private StackPane stackPane22;

    @FXML
    private AnchorPane block;

    private void enable(){
        stackPane00.setDisable(false);
        stackPane01.setDisable(false);
        stackPane02.setDisable(false);
        stackPane10.setDisable(false);
        stackPane11.setDisable(false);
        stackPane12.setDisable(false);
        stackPane20.setDisable(false);
        stackPane21.setDisable(false);
        stackPane22.setDisable(false);
    }

    private void disable(){
        stackPane00.setDisable(true);
        stackPane01.setDisable(true);
        stackPane02.setDisable(true);
        stackPane10.setDisable(true);
        stackPane11.setDisable(true);
        stackPane12.setDisable(true);
        stackPane20.setDisable(true);
        stackPane21.setDisable(true);
        stackPane22.setDisable(true);
    }

    @FXML
    private void tryAgainButtonAction(){
        controller.setTurn(controller.getGame().getTurn());
        controller.getGame().resetBoard();

        stackPane00.getChildren().clear();
        stackPane01.getChildren().clear();
        stackPane02.getChildren().clear();
        stackPane10.getChildren().clear();
        stackPane11.getChildren().clear();
        stackPane12.getChildren().clear();
        stackPane20.getChildren().clear();
        stackPane21.getChildren().clear();
        stackPane22.getChildren().clear();

        enable();

        if(controller.getGame().getTurn() == 1){
            imageText1.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/turn1.png")));
        }
        else {
            block.setDisable(false);
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        imageText3.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/press.png")));

        againButton.setVisible(false);
        againButton.setDisable(true);
    }

    @FXML
    private void startButtonAction(){

        enable();

        imageText3.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/press.png")));

        if(controller.getTurn() == 0) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }
        else
            imageText1.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/turn1.png")));

        imageText2.setVisible(false);
        imageText2.setDisable(true);
    }

    @FXML
    public void exitButtonAction() throws IOException {
        mediaView.getMediaPlayer().stop();
        sfx.setMediaPlayer(sfxClickPlayer);
        sfx.getMediaPlayer().play();

        controller.setTurn(controller.getGame().getTurn());
        controller.changeScene(MainController.PAGE_MAIN);
    }

    @FXML
    public void exitButtonIn() {
        sfx.getMediaPlayer().play();
        exitButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/exitButton_Hovered.png")));
    }

    @FXML
    public void exitButtonOut() {
        exitButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/exitButton.png")));
    }

    @FXML
    public void againButtonIn() {
        sfx.getMediaPlayer().play();
        againButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/againButton_Hovered.png")));
    }

    @FXML
    public void againButtonOut() {
        againButton.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/againButton.png")));
    }

    @FXML
    public void startButtonIn() {
        sfx.getMediaPlayer().play();
        imageText2.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/startButton_Hovered.png")));
    }

    @FXML
    public void startButtonOut() {
        imageText2.setImage(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/startButton.png")));
    }

    @FXML
    public void stackPane00_Action() {
        block.setDisable(false);
        checkElement();
        stackPane00.getChildren().add(element);
        controller.getGame().setHumanMove(0, 0);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane00.setDisable(true);
    }

    @FXML
    public void stackPane01_Action() {
        block.setDisable(false);
        checkElement();
        stackPane01.getChildren().add(element);
        controller.getGame().setHumanMove(0, 1);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane01.setDisable(true);
    }

    @FXML
    public void stackPane02_Action() {
        block.setDisable(false);
        checkElement();
        stackPane02.getChildren().add(element);
        controller.getGame().setHumanMove(0, 2);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane02.setDisable(true);
    }

    @FXML
    public void stackPane10_Action() {
        block.setDisable(false);
        checkElement();
        stackPane10.getChildren().add(element);
        controller.getGame().setHumanMove(1, 0);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane10.setDisable(true);
    }

    @FXML
    public void stackPane11_Action() {
        block.setDisable(false);
        checkElement();
        stackPane11.getChildren().add(element);
        controller.getGame().setHumanMove(1, 1);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane11.setDisable(true);
    }

    @FXML
    public void stackPane12_Action() {
        block.setDisable(false);
        checkElement();
        stackPane12.getChildren().add(element);
        controller.getGame().setHumanMove(1, 2);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane12.setDisable(true);
    }

    @FXML
    public void stackPane20_Action() {
        block.setDisable(false);
        checkElement();
        stackPane20.getChildren().add(element);
        controller.getGame().setHumanMove(2, 0);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane20.setDisable(true);
    }

    @FXML
    public void stackPane21_Action() {
        block.setDisable(false);
        checkElement();
        stackPane21.getChildren().add(element);
        controller.getGame().setHumanMove(2, 1);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane21.setDisable(true);
    }

    @FXML
    public void stackPane22_Action() {
        block.setDisable(false);
        checkElement();
        stackPane22.getChildren().add(element);
        controller.getGame().setHumanMove(2, 2);
        controller.getGame().changeElement();

        if(!checkGameOver()) {
            timeline = new Timeline(new KeyFrame(Duration.millis(millispeed), e -> getComputerMove()));
            timeline.setCycleCount(2);
            timeline.play();
        }

        stackPane22.setDisable(true);
    }

    @FXML
    public void initialize(){

        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        /*
         * All SFX Configurations
         */
        Media sfxSelect = new Media(sfxSelectURL.toString());
        Media sfxClick = new Media(sfxClickURL.toString());

        sfxSelectPlayer = new MediaPlayer(sfxSelect);
        sfxClickPlayer = new MediaPlayer(sfxClick);

        sfxClickPlayer.setOnEndOfMedia(() -> {
            sfxClickPlayer.stop();
            sfxClickPlayer.seek(Duration.ZERO);
        });

        sfxSelectPlayer.setOnEndOfMedia(() -> {
            sfxSelectPlayer.stop();
            sfxSelectPlayer.seek(Duration.ZERO);
        });

        sfx.setMediaPlayer(sfxSelectPlayer);

        /*
         * All StackPanes Configurations
         */
        stackPane00.setOnMouseEntered(mouseEvent -> {
            stackPane00.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane00.setOpacity(0.50);
        });

        stackPane00.setOnMouseExited(mouseEvent -> {
            stackPane00.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane00.setOpacity(1);
        });

        stackPane01.setOnMouseEntered(mouseEvent -> {
            stackPane01.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane01.setOpacity(0.50);
        });

        stackPane01.setOnMouseExited(mouseEvent -> {
            stackPane01.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane01.setOpacity(1);
        });

        stackPane02.setOnMouseEntered(mouseEvent -> {
            stackPane02.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane02.setOpacity(0.50);
        });

        stackPane02.setOnMouseExited(mouseEvent -> {
            stackPane02.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane02.setOpacity(1);
        });

        stackPane10.setOnMouseEntered(mouseEvent -> {
            stackPane10.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane10.setOpacity(0.50);
        });

        stackPane10.setOnMouseExited(mouseEvent -> {
            stackPane10.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane10.setOpacity(1);
        });

        stackPane11.setOnMouseEntered(mouseEvent -> {
            stackPane11.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane11.setOpacity(0.50);
        });

        stackPane11.setOnMouseExited(mouseEvent -> {
            stackPane11.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane11.setOpacity(1);
        });

        stackPane12.setOnMouseEntered(mouseEvent -> {
            stackPane12.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane12.setOpacity(0.50);
        });

        stackPane12.setOnMouseExited(mouseEvent -> {
            stackPane12.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane12.setOpacity(1);
        });

        stackPane20.setOnMouseEntered(mouseEvent -> {
            stackPane20.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane20.setOpacity(0.50);
        });

        stackPane20.setOnMouseExited(mouseEvent -> {
            stackPane20.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane20.setOpacity(1);
        });

        stackPane21.setOnMouseEntered(mouseEvent -> {
            stackPane21.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane21.setOpacity(0.50);
        });

        stackPane21.setOnMouseExited(mouseEvent -> {
            stackPane21.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane21.setOpacity(1);
        });

        stackPane22.setOnMouseEntered(mouseEvent -> {
            stackPane22.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane22.setOpacity(0.50);
        });

        stackPane22.setOnMouseExited(mouseEvent -> {
            stackPane22.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            stackPane22.setOpacity(1);
        });

        disable();
    }
}

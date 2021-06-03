package tictactoe.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import tictactoe.model.*;

/**
 * The Main Controller of the whole game. This Controller is also responsible in creating a new TicTacToe game.
 * Every Controller is dependent to the leadership of the main controller. It contains the file locations and the
 * function changeScene where it allows the Stage to change scenes.
 * @author James Lemsic
 * @author Jordan Sibug
 * @version 1.0
 */
public class MainController {

    /** The Title of the Game */
    public static final String TITLE = "TicTacToe by Lemsic & Sibug";

    /** Value that corresponds to the Main Menu */
    public static final int PAGE_MAIN = 1;

    /** Value that corresponds to the Board of the Game */
    public static final int PAGE_BOARD = 2;

    /**
     * Value that corresponds to the Credits of the Game
     */
    public static final int PAGE_CREDITS = 3;

    /** The Primary Stage of the Game */
    private final Stage primaryStage;
    /** The Game itself */
    private TicTacToe game;
    private int turn;

    /**
     * From the driver, it passes the stage to this controller. This constructor also sets the stage, its title and icon.
     * @param stage the stage from the Driver.
     * @throws IOException throws the exception
     */
    public MainController(Stage stage) throws IOException {

        turn = 1;
        primaryStage = stage;
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/tictactoe/view/resources/icon.png")));
        primaryStage.setResizable(false);
        changeScene(PAGE_MAIN);
    }

    /**
     * One of the most important functions. This function allows the changing of scenes of the stage. It receives a parameter of integer
     * that corresponds to the indicated final attributes above.
     * @param view the number passed by the controllers to indicate what scene to transfer to.
     * @throws IOException throws the exception
     */
    public void changeScene(int view) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        switch(view){
            case PAGE_MAIN : loader = new FXMLLoader(getClass().getResource("/tictactoe/view/MainMenu.fxml"));
                game = new TicTacToe(turn);
                break;
            case PAGE_BOARD: loader = new FXMLLoader(getClass().getResource("/tictactoe/view/Board.fxml"));
                break;
            case PAGE_CREDITS: loader = new FXMLLoader(getClass().getResource("/tictactoe/view/Credits.fxml"));
                break;
        }

        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setMainController(this);

        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();

        primaryStage.show();
    }

    public void setTurn(int turn){
        this.turn = turn;
    }

    public int getTurn(){
        return turn;
    }

    /**
     * Allows every controller to get the primary stage.
     * @return primaryStage the stage of the GUI
     */
    public Stage getStage(){
        return primaryStage;
    }

    /**
     * Allows every controller to get the current game.
     * @return game The GoldMiner being played
     */
    public TicTacToe getGame(){
        return game;
    }
}

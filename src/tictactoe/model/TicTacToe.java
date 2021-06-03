package tictactoe.model;

import javafx.util.Pair;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TicTacToe {

    private int turn;
    private int rationalLevel;
    private char currentElement;
    private char[][] board;
    private char winningLetter;

    private int x;
    private int y;

    private boolean gameOver;
    private boolean draw;
    private int paulCount;

    /**
     * The constructor initializes everything but gets a parameter on who gets to move first. Human for 1, and AI for 0.
     */
    public TicTacToe (int turn){
        this.turn = turn;
        rationalLevel = 0; //default to randy
        resetBoard();
    }

    public void resetBoard(){
        paulCount = 1;
        currentElement = 'X';
        winningLetter = '-';
        board = new char[3][3];

        //make the board blank
        int i, j;
        for (i = 0; i < 3; i++){
            for (j = 0; j < 3; j++)
                board[i][j] = '-';
        }

        gameOver = false;
        draw = false;
    }

    /**
     * The function that allows the GUI to tell the backend, what did the user choose.
     * @param index the Rational Behavior Level
     */
    public void setLevel(int index){
        rationalLevel = index;
    }

    /**
     * The function that allows the GUI to tell the backend, what's the human move.
     * Rest assured that it no longer needs a validation, if the chosen coordinate is valid or not.
     * @param x - x coordinate of the grid.
     * @param y - y coordinate of the grid.
     */
    public void setHumanMove(int x, int y){
        board[y][x] = currentElement; //X and Y is inverted due to the format of the GUI.
    }

    /**
     * The function that gets the Computer Move based on current status of the board.
     */
    private void getAI_Response(){

        if (rationalLevel == 0) {
            randy();
        }
        else if (rationalLevel == 1) {
            paul();
        }
        else if (rationalLevel == 2) {
            rick();
        }

//        displayBoard(); //To Be deleted.
    }

    /**
     * AI Rational Behavior: Level 0. Randy is the AI who chooses random things.
     */
    private void randy(){
        boolean key = false;
        int x, y;
        int safeCount = 0;
        Random rng = new Random();

        while (!key && safeCount < 10){
            x = rng.nextInt(3);
            y = rng.nextInt(3);

            if (board[x][y] == '-') {
                board[x][y] = currentElement; //<--
                this.x = x;
                this.y = y;
                key = true;
            }
            else
                safeCount++;
        }
    }

    /**
     * AI Rational Behavior: Level 1. Able to win, but the implementation of the AI is hard-coded.
     */
    //MINIMIZE PAUL RIGHT NOW, WARNING, TOO LONG TO SCROLL
    private void paul(){

        if (turn == 1) { //Paul is the second one to move
            switch (paulCount){
                case 1:
                    if(board[1][1] == '-'){
                        board[1][1] = currentElement;
                        this.x = 1;
                        this.y = 1;
                    }
                    else{
                        board[2][2] = currentElement;
                        this.x = 2;
                        this.y = 2;
                    }
                    break;
                case 2:
                    //if successfully obtained the center piece
                    if(board[1][1] == 'O'){

                        //deny the Human Player from winning if possible
                        if (board[0][0] == 'X' && board[1][0] == '-' && board[2][0] == 'X'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if (board[0][0] == '-' && board[1][0] == 'X' && board[2][0] == 'X'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[0][0] == 'X' && board[0][1] == '-' && board[0][2] == 'X'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if (board[0][0] == '-' && board[0][1] == 'X' && board[0][2] == 'X'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'X' && board[2][1] == '-' && board[2][0] == 'X'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if (board[2][2] == '-' && board[2][1] == 'X' && board[2][0] == 'X'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'X' && board[2][1] == 'X' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'X' && board[1][2] == '-' && board[0][2] == 'X'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if (board[2][2] == '-' && board[1][2] == 'X' && board[0][2] == 'X'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'X' && board[1][2] == 'X' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else { //if the human didn't attempt to get an advantage, then go for being aggressive.

                            if(board[0][0] == 'X' && board[2][1] == 'X'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }
                            else if(board[0][2] == 'X' && board[2][1] == 'X'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if(board[2][0] == 'X' && board[0][1] == 'X'){
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }
                            else if(board[2][2] == 'X' && board[0][1] == 'X'){
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else if (board[0][0] == 'X' && board[2][2] == 'X'){
                                board[1][2] = currentElement;
                                this.x = 1;
                                this.y = 2;
                            }
                            else if (board[2][0] == 'X' && board[0][2] == 'X'){
                                board[2][1] = currentElement;
                                this.x = 2;
                                this.y = 1;
                            }
                            else if (board[2][2] == '-'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if (board[2][0] == '-'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }
                            else {
                                System.out.println("Errrr... If you reached this, you've broken the AI");
                            }
                        }
                    }
                    //if not, play safe and go for the draw
                    else {
                        //block the Human player from winning
                        if (board[2][0] == 'X'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if (board[2][1] == 'X'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if (board[1][0] == 'X'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if (board[0][1] == 'X'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if (board[0][2] == 'X') {
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else if (board[1][2] == 'X') {
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        //Human made no sense in his move, go for being aggressive
                        else {
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                    }
                    break;
                case 3:
                    //if successfully obtained the center piece
                    if(board[1][1] == 'O'){

                        //check if winnable
                        if(board[0][0] == 'O' && board[2][2] == '-'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if(board[0][1] == 'O' && board[2][1] == '-'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if(board[0][2] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else if(board[1][2] == 'O' && board[1][0] == '-'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if(board[2][2] == 'O' && board[0][0] == '-'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else if(board[2][1] == 'O' && board[0][1] == '-'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if(board[2][0] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if(board[1][0] == 'O' && board[1][2] == '-'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        //go for the aggressive play
                        else {
                            if (board[0][0] == 'X' && board[1][0] == '-' && board[2][0] == 'X'){
                                board[1][0] = currentElement;
                                this.x = 1;
                                this.y = 0;
                            }
                            else if (board[0][0] == '-' && board[1][0] == 'X' && board[2][0] == 'X'){
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }
                            else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == '-'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }

                            else if (board[0][0] == 'X' && board[0][1] == '-' && board[0][2] == 'X'){
                                board[0][1] = currentElement;
                                this.x = 0;
                                this.y = 1;
                            }
                            else if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == '-'){
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else if (board[0][0] == '-' && board[0][1] == 'X' && board[0][2] == 'X'){
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }

                            else if (board[2][2] == 'X' && board[2][1] == '-' && board[2][0] == 'X'){
                                board[2][1] = currentElement;
                                this.x = 2;
                                this.y = 1;
                            }
                            else if (board[2][2] == '-' && board[2][1] == 'X' && board[2][0] == 'X'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if (board[2][2] == 'X' && board[2][1] == 'X' && board[2][0] == '-'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }

                            else if (board[2][2] == 'X' && board[1][2] == '-' && board[0][2] == 'X'){
                                board[1][2] = currentElement;
                                this.x = 1;
                                this.y = 2;
                            }
                            else if (board[2][2] == '-' && board[1][2] == 'X' && board[0][2] == 'X'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if (board[2][2] == 'X' && board[1][2] == 'X' && board[0][2] == '-'){
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else {
                                if(board[2][1] == '-'){
                                    board[2][1] = currentElement;
                                    this.x = 2;
                                    this.y = 1;
                                }
                                else if(board[1][2] == '-'){
                                    board[1][2] = currentElement;
                                    this.x = 1;
                                    this.y = 2;
                                }
                                else if(board[1][0] == '-'){
                                    board[1][0] = currentElement;
                                    this.x = 1;
                                    this.y = 0;
                                }
                                else if(board[0][1] == '-'){
                                    board[0][1] = currentElement;
                                    this.x = 0;
                                    this.y = 1;
                                }
                                else {
                                    //find a vacant spot
                                    int x, y;
                                    boolean key = false;
                                    for(x = 0; x < 3 && !key; x++)
                                        for(y = 0; y < 3 && !key; y++)
                                            if (board[x][y] == '-') {
                                                board[x][y] = currentElement;
                                                this.x = x;
                                                this.y = y;
                                                key = true;
                                            }
                                }
                            }
                        }
                    }
                    else {
                        //check if winnable
                        if (board[0][0] == 'O' && board[1][0] == '-' && board[2][0] == 'O'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if (board[0][0] == '-' && board[1][0] == 'O' && board[2][0] == 'O'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[0][0] == 'O' && board[0][1] == '-' && board[0][2] == 'O'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if (board[0][0] == '-' && board[0][1] == 'O' && board[0][2] == 'O'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'O' && board[2][1] == '-' && board[2][0] == 'O'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if (board[2][2] == '-' && board[2][1] == 'O' && board[2][0] == 'O'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'O' && board[2][1] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'O' && board[1][2] == '-' && board[0][2] == 'O'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if (board[2][2] == '-' && board[1][2] == 'O' && board[0][2] == 'O'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'O' && board[1][2] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else {
                            //force the draw
                            if(board[0][0] == 'X' && board[2][2] == '-'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if(board[0][1] == 'X' && board[2][1] == '-'){
                                board[2][1] = currentElement;
                                this.x = 2;
                                this.y = 1;
                            }
                            else if(board[0][2] == 'X' && board[2][0] == '-'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }
                            else if(board[1][2] == 'X' && board[1][0] == '-'){
                                board[1][0] = currentElement;
                                this.x = 1;
                                this.y = 0;
                            }
                            else if(board[2][2] == 'X' && board[0][0] == '-'){
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }
                            else if(board[2][1] == 'X' && board[0][1] == '-'){
                                board[0][1] = currentElement;
                                this.x = 0;
                                this.y = 1;
                            }
                            else if(board[2][0] == 'X' && board[0][2] == '-'){
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else if(board[1][0] == 'X' && board[1][2] == '-'){
                                board[1][2] = currentElement;
                                this.x = 1;
                                this.y = 2;
                            }
                            else {
                                if(board[2][1] == '-'){
                                    board[2][1] = currentElement;
                                    this.x = 2;
                                    this.y = 1;
                                }
                                else if(board[1][2] == '-'){
                                    board[1][2] = currentElement;
                                    this.x = 1;
                                    this.y = 2;
                                }
                                else if(board[1][0] == '-'){
                                    board[1][0] = currentElement;
                                    this.x = 1;
                                    this.y = 0;
                                }
                                else if(board[0][1] == '-'){
                                    board[0][1] = currentElement;
                                    this.x = 0;
                                    this.y = 1;
                                }
                                else {
                                    //find a vacant spot
                                    int x, y;
                                    boolean key = false;
                                    for(x = 0; x < 3 && !key; x++)
                                        for(y = 0; y < 3 && !key; y++)
                                            if (board[x][y] == '-') {
                                                board[x][y] = currentElement;
                                                this.x = x;
                                                this.y = y;
                                                key = true;
                                            }
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    //if successfully obtained the center piece
                    if(board[1][1] == 'O'){
                        //check if winnable
                        if(board[0][0] == 'O' && board[2][2] == '-'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if(board[0][1] == 'O' && board[2][1] == '-'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if(board[0][2] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else if(board[1][2] == 'O' && board[1][0] == '-'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if(board[2][2] == 'O' && board[0][0] == '-'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else if(board[2][1] == 'O' && board[0][1] == '-'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if(board[2][0] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if(board[1][0] == 'O' && board[1][2] == '-'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if (board[0][0] == 'O' && board[1][0] == '-' && board[2][0] == 'O'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if (board[0][0] == '-' && board[1][0] == 'O' && board[2][0] == 'O'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[0][0] == 'O' && board[0][1] == '-' && board[0][2] == 'O'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if (board[0][0] == '-' && board[0][1] == 'O' && board[0][2] == 'O'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'O' && board[2][1] == '-' && board[2][0] == 'O'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if (board[2][2] == '-' && board[2][1] == 'O' && board[2][0] == 'O'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'O' && board[2][1] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'O' && board[1][2] == '-' && board[0][2] == 'O'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if (board[2][2] == '-' && board[1][2] == 'O' && board[0][2] == 'O'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'O' && board[1][2] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }

                        else if (board[0][0] == 'X' && board[1][0] == '-' && board[2][0] == 'X'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if (board[0][0] == '-' && board[1][0] == 'X' && board[2][0] == 'X'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[0][0] == 'X' && board[0][1] == '-' && board[0][2] == 'X'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if (board[0][0] == '-' && board[0][1] == 'X' && board[0][2] == 'X'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'X' && board[2][1] == '-' && board[2][0] == 'X'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else if (board[2][2] == '-' && board[2][1] == 'X' && board[2][0] == 'X'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'X' && board[2][1] == 'X' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }

                        else if (board[2][2] == 'X' && board[1][2] == '-' && board[0][2] == 'X'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if (board[2][2] == '-' && board[1][2] == 'X' && board[0][2] == 'X'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'X' && board[1][2] == 'X' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else {
                            //find a vacant spot
                            int x, y;
                            boolean key = false;
                            for(x = 0; x < 3 && !key; x++)
                                for(y = 0; y < 3 && !key; y++)
                                    if (board[x][y] == '-') {
                                        board[x][y] = currentElement;
                                        this.x = x;
                                        this.y = y;
                                        key = true;
                                    }
                        }
                    }
                    //force the draw
                    else {
                        //check if winnable
                        if(board[2][2] == 'O' && board[2][1] == 'O' && board[2][0] == '-'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else if(board[2][2] == 'O' && board[1][2] == 'O' && board[0][2] == '-'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else {
                            //secure the draw
                            if(board[0][1] == 'X' && board[2][1] == '-'){
                                board[2][1] = currentElement;
                                this.x = 2;
                                this.y = 1;
                            }
                            else if(board[2][1] == 'X' && board[0][1] == '-'){
                                board[0][1] = currentElement;
                                this.x = 0;
                                this.y = 1;
                            }
                            else if(board[1][0] == 'X' && board[1][2] == '-'){
                                board[1][2] = currentElement;
                                this.x = 1;
                                this.y = 2;
                            }
                            else if(board[1][2] == 'X' && board[1][0] == '-'){
                                board[1][0] = currentElement;
                                this.x = 1;
                                this.y = 0;
                            }
                            else if(board[0][0] == 'X' && board[2][2] == '-'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if(board[0][2] == 'X' && board[2][0] == '-'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }
                            else if(board[2][0] == 'X' && board[0][2] == '-'){
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else if(board[2][2] == 'X' && board[0][0] == '-'){
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }
                            else {
                                //find a vacant spot
                                int x, y;
                                boolean key = false;
                                for(x = 0; x < 3 && !key; x++)
                                    for(y = 0; y < 3 && !key; y++)
                                        if (board[x][y] == '-') {
                                            board[x][y] = currentElement;
                                            this.x = x;
                                            this.y = y;
                                            key = true;
                                        }
                            }
                        }
                    }
                    break;
            }
        }
        //This means if Paul has the opportunity to move first.
        else {
            switch(paulCount){
                case 1: //first move take center.
                    board[1][1] = currentElement;
                    this.x = 1;
                    this.y = 1;
                    break;
                case 2:
                    /*   O X -     - X O
                         - X - or  - X -
                         - - -     - - -   */
                    if (board[0][0] == 'O' || board[2][0] == 'O'){
                        board[0][1] = currentElement;
                        this.x = 0;
                        this.y = 1;
                    }
                    /*   - - -     - - -
                         - X - or  - X -
                         O X -     - X O   */
                    else if (board[2][2] == 'O' || board[0][2] == 'O'){
                        board[2][1] = currentElement;
                        this.x = 2;
                        this.y = 1;
                    }
                    else {
                        /*   X O -
                             O X O
                             - O -   because they didn't get any corner spots. */
                        board[0][0] = currentElement;
                        this.x = 0;
                        this.y = 0;
                    }
                    break;
                case 3:
                    //if diagonal trap set up successfully
                    if(board[0][0] == 'X'){

                        if (board[2][2] != 'O'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if ((board[2][1] == 'O') || (board[0][1] == 'O')) {
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else if ((board[1][2] == 'O') || (board[1][0] == 'O')) {
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                    }
                    //go for the draw
                    else {

                        //but check first if winnable
                        if(board[2][1] == 'X' && board[0][1] != 'O') {
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if(board[0][1] == 'X' && board[2][1] != 'O') {
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else {

                            //continue to go for the draw
                            if((board[0][1] == 'O' && board[2][0] == 'O') || (board[0][1] == 'O' && board[0][2] == 'O')) {
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }
                            else if((board[0][1] == 'O' && board[2][2] == 'O') || (board[0][1] == 'O' && board[0][0] == 'O')) {
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else if((board[2][1] == 'O' && board[0][0] == 'O') || (board[2][1] == 'O' && board[2][2] == 'O')) {
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }
                            else if((board[2][1] == 'O' && board[0][2] == 'O') || (board[2][1] == 'O' && board[2][0] == 'O')) {
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                        }
                    }
                    break;
                case 4:
                    //Devour the user with your trap.
                    if(board[0][0] == 'X' && board[2][0] == 'X'){
                        if (board[1][0] != 'O'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else {
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                    }
                    else if(board[0][0] == 'X' && board[0][2] == 'X'){
                        if (board[2][0] != 'O'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else {
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                    }
                    // go for the draw.
                    else {
                        //but check first if winnable
                        if (board[0][0] == 'X' && board[2][2] != 'O'){
                            board[2][2] = currentElement;
                            this.x = 2;
                            this.y = 2;
                        }
                        else if (board[0][2] == 'X' && board[2][0] != 'O'){
                            board[2][0] = currentElement;
                            this.x = 2;
                            this.y = 0;
                        }
                        else if (board[2][0] == 'X' && board[0][2] != 'O'){
                            board[0][2] = currentElement;
                            this.x = 0;
                            this.y = 2;
                        }
                        else if (board[2][2] == 'X' && board[0][0] != 'O'){
                            board[0][0] = currentElement;
                            this.x = 0;
                            this.y = 0;
                        }
                        else {

                            //force the draw
                            if (board[0][0] == 'O' && board[1][0] == '-' && board[2][0] == 'O'){
                                board[1][0] = currentElement;
                                this.x = 1;
                                this.y = 0;
                            }
                            else if (board[0][0] == 'O' && board[0][1] == '-' && board[0][2] == 'O'){
                                board[0][1] = currentElement;
                                this.x = 0;
                                this.y = 1;
                            }
                            else if (board[2][2] == 'O' && board[2][1] == '-' && board[2][0] == 'O'){
                                board[2][1] = currentElement;
                                this.x = 2;
                                this.y = 1;
                            }
                            else if (board[2][2] == 'O' && board[1][2] == '-' && board[0][2] == 'O'){
                                board[1][2] = currentElement;
                                this.x = 1;
                                this.y = 2;
                            }
                            else {

                                //force to the end game
                                if(board[1][0] == '-'){
                                    board[1][0] = currentElement;
                                    this.x = 1;
                                    this.y = 0;
                                }
                                else if(board[0][1] == '-'){
                                    board[0][1] = currentElement;
                                    this.x = 0;
                                    this.y = 1;
                                }
                                else if(board[1][2] == '-'){
                                    board[1][2] = currentElement;
                                    this.x = 1;
                                    this.y = 2;
                                }
                                else if(board[2][1] == '-'){
                                    board[2][1] = currentElement;
                                    this.x = 2;
                                    this.y = 1;
                                }
                            }
                        }
                    }
                    break;
                case 5:
                        //check if winnable again
                        if(board[1][0] == '-'){
                            board[1][0] = currentElement;
                            this.x = 1;
                            this.y = 0;
                        }
                        else if(board[0][1] == '-'){
                            board[0][1] = currentElement;
                            this.x = 0;
                            this.y = 1;
                        }
                        else if(board[1][2] == '-'){
                            board[1][2] = currentElement;
                            this.x = 1;
                            this.y = 2;
                        }
                        else if(board[2][1] == '-'){
                            board[2][1] = currentElement;
                            this.x = 2;
                            this.y = 1;
                        }
                        else {

                            //end the game with a draw
                            if(board[0][0] == '-'){
                                board[0][0] = currentElement;
                                this.x = 0;
                                this.y = 0;
                            }
                            else if(board[0][2] == '-'){
                                board[0][2] = currentElement;
                                this.x = 0;
                                this.y = 2;
                            }
                            else if(board[2][2] == '-'){
                                board[2][2] = currentElement;
                                this.x = 2;
                                this.y = 2;
                            }
                            else if(board[2][0] == '-'){
                                board[2][0] = currentElement;
                                this.x = 2;
                                this.y = 0;
                            }
                        }
                    break;
            }
        }
        paulCount++; //Increment for the next move.
    }

    /**
     * AI Rational Behavior: Level 2
     */
    private void rick(){
        // AI to make its turn
        int bestScore = Integer.MIN_VALUE;
        int x, y;
        char human, ai;

        ai = currentElement;
        changeElement();
        human = currentElement;
        changeElement();

        for(x = 0; x < 3; x++) {
            for(y = 0; y < 3; y++) {
                if (board[x][y] == '-') {
                    board[x][y] = ai;
                    int score = minimax(board, 0, false, human, ai);
                    board[x][y] = '-';
                    if (score > bestScore) {
                        bestScore = score;
                        this.x = x;
                        this.y = y;
                    }
                }
            }
        }

        board[this.x][this.y] = currentElement; //<--
    }

    public int minimax(char[][] board, int depth, boolean isMaximizing, char human, char ai) {

        isGameOver();

        if(gameOver) {
            changeFirstTurn();
            if(draw) {
                winningLetter = '-';
                gameOver = false;
                draw = false;
                return 0;
            } else if(winningLetter == ai) {
                winningLetter = '-';
                gameOver = false;
                draw = false;
                return ((getBlankSpaces() + 1));
            } else if (winningLetter == human) {
                winningLetter = '-';
                gameOver = false;
                draw = false;
                return (-1 * (getBlankSpaces() + 1));
            }
        }

        if(isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == '-') {
                        board[i][j] = ai;
                        int score = minimax(board, depth + 1, false, human, ai);
                        board[i][j] = '-';
                        maxScore = max(score, maxScore);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = human;
                        int score = minimax(board, depth + 1, true, human, ai);
                        board[i][j] = '-';
                        minScore = min(score, minScore);
                    }
                }
            }
            return minScore;
        }
    }

    /**
     * Shall be deleted in the final program. This is only used to see the board in the backend.
     */
    public void displayBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    /**
     * A convenient function to change element from X to O and vice versa.
     * Since the X and O users can be a Human and a Computer in different rounds,
     * it's essential to have a separate function.
     */
    public void changeElement(){
        if (currentElement == 'X'){
            currentElement = 'O';
        }
        else
            currentElement = 'X';
    }

    /**
     * The function that checks if there's a winner or not.
     */
    public Pair<Boolean, Boolean> isGameOver(){

        int x, y;
        boolean blankExist = false;

        //Checks Vertically and Horizontally for X
        for (x = 0, y = 0; y < 3 && !gameOver; y++, x++) {
            if (board[0][y] == 'X' && board[1][y] == 'X' && board[2][y] == 'X')
                gameOver = true; draw = false; winningLetter = 'X';

            if (board[x][0] == 'X' && board[x][1] == 'X' && board[x][2] == 'X')
                gameOver = true;

            //Simultaneously checks for blank spaces.
            if(board[x][0] == '-' || board[x][1] == '-' || board[x][2] == '-')
                blankExist = true;
        }

        //Checks Vertically and Horizontally for O
        for (x = 0, y = 0; y < 3 && !gameOver; y++, x++) {
            if (board[0][y] == 'O' && board[1][y] == 'O' && board[2][y] == 'O') {
                gameOver = true; draw = false; winningLetter = 'O';
            }

            if (board[x][0] == 'O' && board[x][1] == 'O' && board[x][2] == 'O') {
                gameOver = true; draw = false; winningLetter = 'O';
            }
        }

        if (!gameOver) {
            //Checks Diagonally for X
            if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
                gameOver = true; draw = false;
            }
            else if (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X') {
                gameOver = true; draw = false;
            }

            //Checks Diagonally for O
            if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
                gameOver = true; draw = false; winningLetter = 'O';
            }
            else if (board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O') {
                gameOver = true; draw = false; winningLetter = 'O';
            }

        }

        if (!gameOver && !blankExist) {
            gameOver = true;
            draw = true;
        }

        if(gameOver) {
            changeFirstTurn();
        }


        return new Pair<>(gameOver, draw);
    }

    /**
     * Each round, this function is called to let the former 'O' user be the 'X' player next round.
     */
    private void changeFirstTurn(){
        if (turn == 1)
            turn = 0;
        else
            turn = 1;
    }

    /**
     * All Getters Below
     */
    public int getTurn(){
        return turn;
    }

    private int getBlankSpaces() {
        int total = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == '-')
                    total++;
            }
        }
        return total;
    }

    public char getCurrentElement(){
        return currentElement;
    }

    public Pair<Integer, Integer> getComputerMove(){
        getAI_Response();

        return new Pair<>(y, x);
    }

    public char getWinningLetter() {
        return winningLetter;
    }
}

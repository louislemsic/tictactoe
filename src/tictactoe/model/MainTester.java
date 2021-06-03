package tictactoe.model;

public class MainTester {

    private static TicTacToe game;
    private static int count;

    public static void move(int x, int y){
        System.out.println("[" + count + "] Human Move:");
        game.setHumanMove(x, y);
        game.changeElement();
        game.displayBoard();

        System.out.println("[" + count + "] Computer Move:");
        game.getComputerMove();
        game.changeElement();
        game.displayBoard();

        count++;
    }

    public static void main (String[] args){

        count = 1;
        game = new TicTacToe(1); //1 means human first, 0 means computer first
                                      //change it nalang.
        game.setLevel(1); //Level 0: Randy, Level 1: Paul, Level 2: Randy

        runScript();
    }

    //PLACE ALL MOVES HERE
    public static void runScript(){

        move(1,2); //<-- call move() nalang, no need to call getComputerMove()
        move(2,1);
        move(0,0);
    }
}

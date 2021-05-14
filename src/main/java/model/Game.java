package model;

import lombok.Data;

@Data
public class Game {

    public static final int MAX_SCORE = 100;

    // Data Fields
    private Die die;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    // The start state
    public Game(String playerOneName, String playerTwoName ){
        die = new Die();
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
        currentPlayer = playerOne;
    }

    // Status Methods
    // Current player's score is greater than 100 which means the game is over
    public boolean gameOver(){
        return currentPlayer.getTotalScore() >= MAX_SCORE;
    }

    public boolean playerOneTurn(){
        return currentPlayer == playerOne;
    }

    // Game Play Methods
     public void switchTurn(){
        if (playerOneTurn()) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
     }

     public void roll(){
        die.roll();
        int top = die.getTop();
        currentPlayer.updateTurn(top);
        if (top == 1){
            currentPlayer.resetTurnScore();
            switchTurn();
        }
     }

     public void hold(){
        currentPlayer.saveScore();
        switchTurn();
        die.setTop(0);
     }
}

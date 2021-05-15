package pig.model;

import lombok.Data;


@Data
public class Player {

    // Data Fields
    private String name;
    private int turnScore;
    private int totalScore;

    // Constructor
    public Player(String name){
        this.name = name;
        turnScore = 0;
        totalScore = 0;
    }

    // Game Play Methods
    public void resetTurnScore(){
        turnScore = 0;
    }

    public void updateTurn(int roll){
        turnScore += roll;
    }

    public void saveScore(){
        totalScore += turnScore;
        resetTurnScore();
    }
}

package pig.model;

import lombok.Data;

/**
 * Ez az osztály reprezentálja a játék menetét.
 */
@Data
public class Game {

    public static final int MAX_SCORE = 50;

    // Data Fields
    private Die die;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    /**
     * A jéték kezdetekor létrehozzuk a két játékos és a kocka példányt.
     *
     * @param playerOneName az első játékos példánya
     * @param playerTwoName a második játékos példánya
     */
    public Game(String playerOneName, String playerTwoName ){
        die = new Die();
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
        currentPlayer = playerOne;
    }

    /**
     * Ha a jelenlegi játékos pontja nagyobb mint 50 akkor vége a játéknak és ezzel Ő a nyertes.
     */
    public boolean gameOver(){
        return currentPlayer.getTotalScore() >= MAX_SCORE;
    }

    /**
     * Ellenőrizük, hogy az aktuális kör az első játékosé-e.
     *
     * @return csak egy True vagy False logikai értékkel tér vissza.
     */
    public boolean playerOneTurn(){
        return currentPlayer == playerOne;
    }

     /**
     * Ha az első játékosé a kör akkor Ő a currentPlayer ellenkező esetben a második játékos.
     */
     public void switchTurn(){
        if (playerOneTurn()) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
     }

    /**
     * Ha 1-es dobot a kockával az aktuális játékos akkor nullázuk az eddig összegyűjtött turnScore értéket
     * és a második játékos köre kezdődik.
     */
    public void roll(){
        die.roll();
        int top = die.getTop();
        currentPlayer.updateTurn(top);
        if (top == 1){
            currentPlayer.resetTurnScore();
            switchTurn();
        }
     }

    /**
     * Ha úgy dönt az aktuális játékos, hogy nem dob hanem megtartja az eddigi turnScore erteket
     * akkor az hozzáadjuk a totalScore-hoz és a következő játékosé a dobási lehetőség.
     */
    public void hold(){
        currentPlayer.saveScore();
        switchTurn();
        die.setTop(0);
     }
}

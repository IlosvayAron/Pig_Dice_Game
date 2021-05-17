package pig.model;

import lombok.Data;
import org.tinylog.Logger;

/**
 * Ez az osztály fogja azon informáciokat tárolni, hogy a játékosnak, mennyi pontja van a jelenlegi körben
 * és mennyi van összesen.
 */
@Data
public class Player {

    // Data Fields
    private String name;
    private int turnScore;
    private int totalScore;

    /**
     * @param name nevű játékosnak a játék kezdetekor 0 a turnScore és a totalScore értéke.
     */
    public Player(String name){
        this.name = name;
        turnScore = 0;
        totalScore = 0;
        Logger.info(name + " kezdeti állapota: TurnScore = " + turnScore + ", TotalScore = " + totalScore);
    }

    /**
     * Nullára allítjuk a turnScore értékét mert a játékos 1-es értéket dobott a kockával.
     */
    public void resetTurnScore(){
        turnScore = 0;
        Logger.debug("A turnScore értéke nullázva let!");
    }

    /**
     *
     * @param roll értéket hozzáadajuk minden dobást követően a turnScore-hoz ezzel figyelve, hogy a
     * jatékosnak mennyi az aktuális pontja ebben a körben.
     */
    public void updateTurn(int roll){
        turnScore += roll;
        Logger.debug("Aktuális pontszám:" + turnScore);
    }

    /**
     * Mentjük a totalScore értéket és nullázuk a turnScore értékét, mivel a következő játékosé
     * a dobási lehetőség.
     */
    public void saveScore(){
        totalScore += turnScore;
        Logger.debug("Elmentett pontszám:" + totalScore);
        resetTurnScore();
    }
}

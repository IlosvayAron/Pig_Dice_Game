package pig.model;

import lombok.Data;

/**
 * Ez az osztaly reprezentalja a kockat, amellyel jatszani fogunk.
 * */
@Data
public class Die {

    //Private Data Fields
    private int sides;
    private int top;

    // Constructors
    /**
     * Bealitjuk a sides es a top valtozo alapertekit.
     * */
    public Die(){
        sides = 6;
        top = 0;
    }

    public Die(int sides, int top){
        this.sides = sides;
        this.top = top;
    }

    /**
     * @param top beallitja ezen osztaly top valtozojat arra az ertekre amit a kockaval dobtunk,
     * de csak akkor ha az ervenyes. Azaz 0 es 6 kozott van.
     */
    public void setTop(int top){
        if (top > 0 && top <= sides){
            this.top = top;
        }
    }

    /**
     * 0 és 6 kozott 'dobunk' egy szamot. Ez lesz a kockaval dobott aktualis ertek.
     */
    public void roll(){
        top = 1 + (int)(Math.random() * sides);
    }


}

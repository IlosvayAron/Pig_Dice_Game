package model;

import lombok.Data;

@Data
public class Die {

    //Private Data Fields
    private int sides;
    private int top;

    // Constructors
    public Die(){
        sides = 6;
        top = 0;
    }

    public Die(int sides, int top){
        this.sides = sides;
        this.top = top;
    }

    // Mutator Methods
    public void setTop(int top){
        if (top > 0 && top <= sides){
            this.top = top;
        }
    }

    // Game Play Methods
    public void roll(){
        top = 1 + (int)(Math.random() * sides);
    }


}

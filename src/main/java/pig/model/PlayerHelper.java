package pig.model;

@lombok.Data
public class PlayerHelper {

    private String player_name;
    private int totalScore;

    public PlayerHelper(String player_name, int totalScore) {
        this.player_name = player_name;
        this.totalScore = totalScore;
    }
}

import java.util.*;

public class Day21Second {
    public static void main(String[] args) {

    }

    private static int numP1Win(Integer pos1, Integer pos2, Integer score1, Integer score2, Integer roll, Integer turn,
                                Map<String, Integer> memo) {
        String hashKey = pos1.toString() + "_" + pos2.toString() + "_" + score1.toString() +
                "_" + score2.toString() + "_" + roll.toString() + "_" + turn.toString();
        if (memo.containsKey(hashKey)) {
            return memo.get(hashKey);
        } else {
            if (score1 >= 21) {
                return 1;
            } else if (score2 >= 21) {
                return 0;
            } else {

            }
        }
        return 0;
    }

}

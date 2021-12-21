import java.math.BigInteger;
import java.util.*;

public class Day21Second {
    public static void main(String[] args) {
        Map<String, BigInteger> memo1 = new HashMap<>();
        Map<String, BigInteger> memo2 = new HashMap<>();
        System.out.println(numP1Win(9, 4, 0, 0, 0, 0, memo1));
        System.out.println(numP1Win(4, 9, 0, 0, 3, 0, memo2));
        System.out.println(memo1.size());
    }

    private static BigInteger numP1Win(Integer pos1, Integer pos2, Integer score1, Integer score2, Integer roll, Integer currentSpan,
                                       Map<String, BigInteger> memo) {
        String hashKey = pos1.toString() + "_" + pos2.toString() + "_" +
                score1.toString() + "_" + score2.toString() + "_" +
                roll.toString() + "_" + currentSpan.toString();
        if (memo.containsKey(hashKey)) {
            return memo.get(hashKey);
        } else {
            if (score1 >= 21) {
                return BigInteger.valueOf(1);
            } else if (score2 >= 21) {
                return BigInteger.valueOf(0);
            } else {
                BigInteger result = BigInteger.valueOf(0);
                for (int i = 1; i <= 3; ++i) {
                    currentSpan += i;
                    if (roll == 2) {
                        int newPos1 = (pos1 + currentSpan) % 10;
                        if (newPos1 == 0) newPos1 = 10;
                        int newScore1 = score1 + newPos1;
                        result = numP1Win(newPos1, pos2, newScore1, score2, 3, 0, memo).add(result);
                    } else if (roll == 5) {
                        int newPos2 = (pos2 + currentSpan) % 10;
                        if (newPos2 == 0) newPos2 = 10;
                        int newScore2 = score2 + newPos2;
                        result = numP1Win(pos1, newPos2, score1, newScore2, 0, 0, memo).add(result);
                    } else {
                        result = numP1Win(pos1, pos2, score1, score2, (roll + 1) % 6, currentSpan, memo).add(result);
                    }
                    currentSpan -= i;
                }
                memo.put(hashKey, result);
                return result;
            }
        }
    }

}

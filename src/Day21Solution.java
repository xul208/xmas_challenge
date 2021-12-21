public class Day21Solution {
    public static void main(String[] args) {
        int[] pos = {9,4};
        int[] scores = {0,0};
        int dice = 1;
        int turn = 0;
        int rounds = 0;
        while(scores[0] < 1000 && scores[1] < 1000) {
            int newScore = dice + (dice+1)%100 + (dice+2)%100;
            ++rounds;
            dice = (dice+3)%100;
            pos[turn] = (pos[turn] + newScore) % 10;
            if (pos[turn] == 0) {
                pos[turn] = 10;
            }
            scores[turn] += pos[turn];
            System.out.printf("- Player %d moves to %d \n", turn, pos[turn]);
            turn = (turn+1)%2;
        }
        System.out.println(scores[0]);
        System.out.println(scores[1]);
        System.out.println(rounds * 3);
        System.out.println(Math.min(scores[0],scores[1])*rounds*3);
    }
}

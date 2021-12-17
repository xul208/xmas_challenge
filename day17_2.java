// Your First Program
class Solution {
    public static void main(String[] args) {
        int minX = 94;
        int maxX = 151;
        int minY = -156;
        int maxY = -103;
        int maxHeight = 0;
        int combo = 0;

        for (int vx = 1; vx <= 200; ++vx) {
            for(int vy = -200; vy <= 200; ++vy) {
                System.out.printf("%d, %d \n", vx, vy);
                int simulationMax = 0, x = 0, y = 0, v1 = vx, v2 = vy;
                boolean hit = false;
                while(y>=minY && x<=maxX) {
                    x+=v1;
                    y+=v2;
                    v1=Math.max(--v1, 0);
                    v2--;
                    if (x>=minX && x <= maxX && y >= minY && y <= maxY) {
                        hit = true;
                        break;
                    }
                    simulationMax = Math.max(simulationMax, y);
                }
                if (hit == true) {
                    maxHeight = Math.max(maxHeight, simulationMax);
                    ++combo;
                }
            }
        }
        System.out.printf("maxHeight: %d \n", maxHeight);
        System.out.printf("combo: %d \n", combo);
    }
}
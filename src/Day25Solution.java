import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day25Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        List<List<String>> seaMap = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("day25_example.txt"));
            String line = reader.readLine();
            while (line != null) {
                List<String> entry = Arrays.asList(line.split(""));
                seaMap.add(entry);
                line = reader.readLine();
            }
            prettyPrint(seaMap);
            int steps = 0;
            for (int i = 1; ; ++i) {
                int moveTotal = 0;
                moveTotal += move(seaMap, Direction.EAST);
                moveTotal += move(seaMap, Direction.SOUTH);
                if (moveTotal == 0) {
                    steps = i;
                    break;
                }
            }
            prettyPrint(seaMap);
            System.out.println(steps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void prettyPrint(List<List<String>> seaMap) {
        for (List<String> e : seaMap) {
            System.out.println(e);
        }
        System.out.println("");
    }

    public enum Direction {
        EAST {
            @Override
            public AbstractMap.SimpleEntry getOffset() {
                return new AbstractMap.SimpleEntry<>(0, 1);
            }

            @Override
            public String getSymbol() {
                return ">";
            }
        }, SOUTH {
            @Override
            public AbstractMap.SimpleEntry getOffset() {
                return new AbstractMap.SimpleEntry<>(1, 0);
            }

            @Override
            public String getSymbol() {
                return "v";
            }
        };

        public abstract AbstractMap.SimpleEntry<Integer, Integer> getOffset();

        public abstract String getSymbol();
    }

    public static int move(List<List<String>> seaMap, Direction direction) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> toMove = new ArrayList<>();
        Map.Entry<Integer, Integer> offset = direction.getOffset();
        String symbol = direction.getSymbol();
        for (int i = 0; i < seaMap.size(); ++i) {
            for (int j = 0; j < seaMap.get(0).size(); ++j) {
                if (seaMap.get(i).get(j).equals(symbol)) {
                    int nextY = (i + offset.getKey()) % (seaMap.size());
                    int nextX = (j + offset.getValue()) % (seaMap.get(0).size());
                    if (seaMap.get(nextY).get(nextX).equals(".")) {
                        toMove.add(new AbstractMap.SimpleEntry<>(i, j));
                    }
                }
            }
        }
        for (var item: toMove) {
           int currentY = item.getKey();
           int currentX = item.getValue();
            int nextY = (currentY + offset.getKey()) % (seaMap.size());
            int nextX = (currentX + offset.getValue()) % (seaMap.get(0).size());
            seaMap.get(currentY).set(currentX, ".");
            seaMap.get(nextY).set(nextX, symbol);
        }
        return toMove.size();
    }
}

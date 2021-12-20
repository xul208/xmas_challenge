import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Day20Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        Map<Integer, Boolean> dictionary = new HashMap<>();
        Map<String, Boolean> seaMap = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader("day20_1.txt"));
            //reader = new BufferedReader(new FileReader("day20Example.txt"));
            String line = reader.readLine();
            buildDictionary(line, dictionary);
            System.out.println(dictionary);

            int lineCounter = 0;
            while (!line.isBlank()) {
                line = reader.readLine();
                if (line == null) break;
                buildSeaMap(line, lineCounter++, seaMap);
            }
            reader.close();
            System.out.println(seaMap);
            printMap(seaMap, -5, 10);
            System.out.println(seaMap.values().stream().filter(b -> b).count());
            System.out.println("\n=====================\n");
            for (int i = 0; i < 50; ++i) {
                seaMap = enhanceMap(seaMap, dictionary, -3 * (i+1), 100 + 3 *(i+1), i % 2 > 0);
            }
            printMap(seaMap, -5, 105);
            System.out.println(seaMap.values().stream().filter(b -> b).count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buildDictionary(String line, Map<Integer, Boolean> dictionary) {
        IntStream.range(0, line.length()).forEach(i -> {
            if (line.charAt(i) == '#') {
                dictionary.put(i, true);
            } else {
                dictionary.put(i, false);
            }
        });
    }

    private static void buildSeaMap(String line, int counter, Map<String, Boolean> seaMap) {
        IntStream.range(0, line.length()).forEach(i -> {
            if (line.charAt(i) == '#') {
                String key = Integer.toString(counter) + '_' + i;
                seaMap.put(key, true);
            }
        });
    }

    private static Boolean enhancePosition(Integer y, Integer x,
                                           Map<String, Boolean> seaMap,
                                           Map<Integer, Boolean> dictionary, Boolean defaultVal) {
        int dictionaryKey = 0;
        for (Integer i = y - 1; i <= y + 1; ++i) {
            for (int j = x - 1; j <= x + 1; ++j) {
                dictionaryKey *= 2;
                String mapKey = i.toString() + '_' + j;
                Boolean mapResult = seaMap.getOrDefault(mapKey, defaultVal);
                if (mapResult) {
                    dictionaryKey += 1;
                }
            }
        }
        return dictionary.get(dictionaryKey);
    }

    private static Map<String, Boolean> enhanceMap(Map<String, Boolean> prevMap,
                                                   Map<Integer, Boolean> dictionary,
                                                   int from, int to,
                                                   Boolean defaultVal) {
        Map<String, Boolean> newMap = new HashMap<>();
        for (Integer i = from; i < to; ++i) {
            for (int j = from; j < to; ++j) {
                String mapKey = i.toString() + '_' + j;
                newMap.put(mapKey, enhancePosition(i, j, prevMap, dictionary, defaultVal));
            }
        }
        return newMap;
    }

    private static void printMap(Map<String, Boolean> seaMap, int from, int to) {
        for (Integer i = from; i < to; ++i) {
            for (int j = from; j < to; ++j) {
                String mapKey = i.toString() + '_' + j;
                if (seaMap.getOrDefault(mapKey, false)) {
                    System.out.printf("#  ");
                } else {
                    System.out.printf(".  ");
                }
            }
            System.out.println("\n");
        }
    }
}

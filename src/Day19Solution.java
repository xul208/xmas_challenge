import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day19Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        List<List<List<Integer>>> beacons = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("day19_example.txt"));
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                if (line.isBlank()) {
                    line = reader.readLine();
                }
                if (line.startsWith("---")) {
                    beacons.add(counter++, new ArrayList<>());
                } else {
                    String[] posStrs = line.split(",");
                    var entry = Arrays.stream(posStrs).filter(s -> !s.isBlank())
                            .map(Integer::parseInt).collect(Collectors.toList());
                    beacons.get(counter - 1).add(entry);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(beacons);
        findNumBeacons(beacons);
    }

    static int[][][] possible_rotations = {
            {
                    {1, 0, 0},
                    {0, 0, 1},
                    {0, -1, 0},
            }, {
            {-1, 0, 0},
            {0, 0, 1},
            {0, -1, 0},
    }, {
            {0, 0, 1},
            {0, 1, 0},
            {-1, 0, 0},
    }, {
            {0, 0, 1},
            {0, -1, 0},
            {-1, 0, 0},
    }, {
            {0, 1, 0},
            {-1, 0, 0},
            {0, 0, 1},
    }, {
            {0, 1, 0},
            {-1, 0, 0},
            {0, 0, -1},
    }
    };

    private static List<Integer> rotate(List<Integer> position, int[][] rotation) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0, dResult = 0; i < 3; ++i) {
            dResult = 0;
            for (int j = 0; j < 3; ++j) {
                dResult += position.get(j) * rotation[j][i];
            }
            result.add(dResult);
        }
        return result;
    }

    // <rotation #, times, x, y, z>
    private static List<Integer> findRotationOffset(List<List<Integer>> reference, List<List<Integer>> observed) {
        for (int i = 0; i < 6; ++i) {
            for (int times = 0; times < 4   ; ++times) {
                var rotation = possible_rotations[i];
                List<List<Integer>> observedTransformed = new ArrayList<>();
                for (List<Integer> pos : observed) {
                    for (int m = 0; m < times; ++m) {
                        pos = rotate(pos, rotation);
                    }
                    observedTransformed.add(pos);
                }
                for (int x = 0; x < reference.size(); ++x) {
                    for (int j = 0; j < observedTransformed.size(); ++j) {
                        List<Integer> possibleOffset = new ArrayList<>();
                        for (int k = 0; k < 3; ++k) {
                            possibleOffset.add(observedTransformed.get(j).get(k) - reference.get(x).get(k));
                        }
                        if (j == 1 && x == 4) {
                            System.out.printf("[DEBUG]: %d rotates %d times, delta %s \n",i, times, possibleOffset);
                        }
                        int hits = 0;
                        for (List<Integer> rotated : observedTransformed) {
                            for (int k = 0; k < 3; ++k) {
                                rotated.set(k, rotated.get(k) - possibleOffset.get(k));
                            }
                            if (reference.stream().anyMatch(pos -> pos.equals(rotated))) {
                                ++hits;
                                if (hits >= 12) {
                                    break;
                                }
                            }
                        }
                        if (hits >= 12) {
                            possibleOffset.add(0, times);
                            possibleOffset.add(0, i);
                            return possibleOffset;
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private static int findNumBeacons(List<List<List<Integer>>> beacons) {
        for (int i = 1; i < beacons.size(); ++i) {
            List<Integer> rotationOffset = findRotationOffset(beacons.get(0), beacons.get(i));
            System.out.printf("rotation vector between scanner 0 and %d is: %s\n", i, rotationOffset);
        }
        return 0;
        /*
        3,4
                   0  1
                  -1  0
        -4,3
        -3, -4,
        4,-3
         */
    }
}

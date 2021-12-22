import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day22Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("d22_1.txt"));
            String line = reader.readLine();
            List<List<Integer>> operations = new ArrayList<>();
            while (line != null) {
                List<Integer> entry = new ArrayList<>(7);
                Pattern pattern = Pattern.compile("^(.*) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)$");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    for (int i = 2; i <= 7; ++i) {
                        entry.add(Integer.parseInt(matcher.group(i)));
                    }
                    if (matcher.group(1).equals("on")) {
                        entry.add(1);
                    } else {
                        entry.add(0);
                    }
                }
                operations.add(entry);
                line = reader.readLine();
            }

            System.out.println(question2((operations)));

        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }
    }

    public static int question1(List<List<Integer>> operations) {
        int lightCount = 0;
        for (int x = -50; x <= 50; ++x) {
            for (int y = -50; y <= 50; ++y) {
                for (int z = -50; z <= 50; ++z) {
                    int light = 0;
                    for (var operation : operations
                    ) {
                        if (x >= operation.get(0) && x <= operation.get(1) && y >= operation.get(2) && y <= operation.get(3) &&
                                z >= operation.get(4) && z <= operation.get(5)) {
                            light = operation.get(6);
                        }
                    }
                    lightCount += light;
                }
            }
        }
        return lightCount;
    }

    public static BigInteger question2(List<List<Integer>> operations) {
        Map<Integer, Cubic> registry = new HashMap<>();
        int MAX_LEN = 100_000;
        List<Integer> space = Arrays.asList(-MAX_LEN, MAX_LEN, -MAX_LEN, MAX_LEN, -MAX_LEN, MAX_LEN, 0);
        Cubic cubic = new Cubic(space);
        registry.put(cubic.hashKey(), cubic);
        List<Integer> xAxis = new ArrayList<>();
        Set<Integer> xSeen = new HashSet<>();
        List<Integer> yAxis = new ArrayList<>();
        Set<Integer> ySeen = new HashSet<>();
        List<Integer> zAxis = new ArrayList<>();
        Set<Integer> zSeen = new HashSet<>();

        xSeen.add(cubic.stub.get(0));
        xAxis.add(cubic.stub.get(0));
        xSeen.add(cubic.stub.get(1));
        xAxis.add(cubic.stub.get(1));

        ySeen.add(cubic.stub.get(2));
        yAxis.add(cubic.stub.get(2));
        ySeen.add(cubic.stub.get(3));
        yAxis.add(cubic.stub.get(3));

        zSeen.add(cubic.stub.get(4));
        zAxis.add(cubic.stub.get(4));
        zSeen.add(cubic.stub.get(5));
        zAxis.add(cubic.stub.get(5));

        for (var op : operations) {
            System.out.println(op);
            if(!xSeen.contains(op.get(0)-1)) {
                xSeen.add(op.get(0)-1)
                xAxis.add(op.get(0) - 1);
            }
            xAxis.add(op.get(0));
            xAxis.add(op.get(1));
            xAxis.add(op.get(1) + 1);
            yAxis.add(op.get(2) - 1);
            yAxis.add(op.get(2));
            yAxis.add(op.get(3));
            yAxis.add(op.get(3) + 1);
            zAxis.add(op.get(4) - 1);
            zAxis.add(op.get(4));
            zAxis.add(op.get(5));
            zAxis.add(op.get(5) + 1);
            Collections.sort(xAxis);
            Collections.sort(yAxis);
            Collections.sort(zAxis);

            Set<Integer> seen = new HashSet<>();
            Cubic cubicFromOp = new Cubic(op);

            for (int i = 0; i < xAxis.size() - 1; i += 2) {
                for (int j = 0; j < yAxis.size() - 1; j += 2) {
                    for (int k = 0; k < zAxis.size() - 1; k += 2) {
                        List<Integer> currentCubicStub = Arrays.asList(xAxis.get(i), xAxis.get(i + 1),
                                yAxis.get(j), yAxis.get(j + 1), zAxis.get(k), zAxis.get(k + 1), 0);
                        Cubic currentCubic = new Cubic(currentCubicStub);
                        Integer hashKey = currentCubic.hashKey();

                        if (!registry.containsKey(hashKey)) { // newly cut cubic
                            if (cubicFromOp.contains(currentCubic)) {
                                currentCubic.stub.set(6, cubicFromOp.stub.get(6));
                            } else {
                                registry.values().stream().filter(c -> c.contains(currentCubic)).findFirst().map(
                                       p -> currentCubic.stub.set(6, p.stub.get(6))
                                ).orElseThrow(
                                        ()->new Error("no parent")
                                );
                            }
                        }
                        seen.add(hashKey);
                        registry.put(hashKey, currentCubic);
                    }
                }
            }
            registry = registry.entrySet().stream()
                    .filter(x -> seen.contains(x.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return registry.values().stream().map(c -> c.volume()).reduce(BigInteger.ZERO, BigInteger::add);
    }

    public static class Cubic {
        public List<Integer> stub;


        public Cubic(List<Integer> tips) {
            stub = new ArrayList<>();
            stub.addAll(tips);
        }

        public boolean contains(Cubic c) {
            return stub.get(0) <= c.stub.get(0) && stub.get(1) >= c.stub.get(1) &&
                    stub.get(2) <= c.stub.get(2) && stub.get(3) >= c.stub.get(3) &&
                    stub.get(4) <= c.stub.get(4) && stub.get(5) >= c.stub.get(5);
        }

        public BigInteger volume() {
            if (stub.get(6) == 0) {
                return BigInteger.ZERO;
            }
            return BigInteger.valueOf(stub.get(1) - stub.get(0)).multiply(
                    BigInteger.valueOf(stub.get(3) - stub.get(2))).multiply(
                    BigInteger.valueOf(stub.get(5) - stub.get(4)));
        }

        public int hashKey() {
            return stub.hashCode();
        }
    }
}

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
import java.util.stream.IntStream;

public class Day22Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("d22_1.txt"));
            String line = reader.readLine();
            List<List<Integer>> operations = new ArrayList<>();
            int[] boundary = {0, 0, 0, 0, 0, 0};
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
                    boundary[0] = Math.min(boundary[0], entry.get(0));
                    boundary[1] = Math.max(boundary[1], entry.get(1));
                    boundary[2] = Math.min(boundary[2], entry.get(2));
                    boundary[3] = Math.max(boundary[3], entry.get(3));
                    boundary[4] = Math.min(boundary[4], entry.get(4));
                    boundary[5] = Math.max(boundary[5], entry.get(5));
                }
                operations.add(entry);
                line = reader.readLine();
            }

            System.out.println(question1((operations)));

        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }
    }

    public static int question1(List<List<Integer>> operations) {
        int lightCount = 0;
        for (int x = -120100; x <= 120875; ++x) {
            var date=java.util.Calendar.getInstance().getTime();
            System.out.println(date);
            System.out.printf("current x: %d, ===>%d \n", x, lightCount );
            for (int y = -124565; y <= 118853; ++y) {
                final int xx = x; final int yy = y;
                int lights = IntStream.range(-121762, 119054).parallel().map(z -> {
                        int light = 0;
                        for (int i= operations.size()-1; i>=0; --i){
                            var operation = operations.get(i);
                            if (xx >= operation.get(0) && xx <= operation.get(1) && yy >= operation.get(2) && yy <= operation.get(3) &&
                                    z >= operation.get(4) && z <= operation.get(5)) {
                                light = operation.get(6);
                                System.out.println(i);
                                break;
                            }
                        }
                        return light;
                    }).reduce(Integer::sum).getAsInt();
                lightCount += lights;
            }
        }
        return lightCount;
    }

    public static BigInteger question2(List<List<Integer>> operations) {
        Map<Integer, Cubic> registry = new HashMap<>();
        int MAX_LEN = 1_000_000;
        List<Integer> space = Arrays.asList(-MAX_LEN, MAX_LEN, -MAX_LEN, MAX_LEN, -MAX_LEN, MAX_LEN, 0);
        Cubic cubic = new Cubic(space);
        registry.put(cubic.hashKey(), cubic);
        List<Integer> xAxis = new ArrayList<>();
        List<Integer> yAxis = new ArrayList<>();
        List<Integer> zAxis = new ArrayList<>();

        xAxis.add(cubic.stub.get(0));
        xAxis.add(cubic.stub.get(1));

        yAxis.add(cubic.stub.get(2));
        yAxis.add(cubic.stub.get(3));

        zAxis.add(cubic.stub.get(4));
        zAxis.add(cubic.stub.get(5));

        for (var op : operations) {
            System.out.println(op);
            xAxis.add(op.get(0));
            xAxis.add(op.get(1));
            yAxis.add(op.get(2));
            yAxis.add(op.get(3));
            zAxis.add(op.get(4));
            zAxis.add(op.get(5));
            Collections.sort(xAxis);
            Collections.sort(yAxis);
            Collections.sort(zAxis);

            Set<Integer> seen = new HashSet<>();
            Cubic cubicFromOp = new Cubic(op);

            for (int i = 0; i < xAxis.size() - 1; i += 1) {
                for (int j = 0; j < yAxis.size() - 1; j += 1) {
                    for (int k = 0; k < zAxis.size() - 1; k += 1) {
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
            return BigInteger.valueOf(stub.get(1) - stub.get(0) + 1).multiply(
                    BigInteger.valueOf(stub.get(3) - stub.get(2) + 1)).multiply(
                    BigInteger.valueOf(stub.get(5) - stub.get(4) + 1));
        }

        public int hashKey() {
            return stub.hashCode();
        }
    }
}

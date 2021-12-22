import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.out.println(lightCount);

        } catch (Exception e) {
            System.out.println(
                    e.getMessage()
            );
        }

    }
}

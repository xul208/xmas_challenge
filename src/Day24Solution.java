import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static java.util.Map.copyOf;

public class Day24Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        List<List<String>>operations = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("day24_1.txt"));
            String line = reader.readLine();
            while (line != null) {
                List<String> operation = Arrays.asList(line.split(" "));
                operations.add(operation);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(operations);
        System.out.println(question1(operations));
    }

    public static long question1(List<List<String>> ops) {
        return 0;
    }

    public static Map<Character, Integer> iteration(Map<Character, Integer> prev) {
        /*
inp w       w = new_num
mul x 0
add x z
mod x 26
div z 1
add x 12    x = z % 26 + (>10) OR   + (<0)
            z = z  OR   z/26
eql x w
eql x 0     x = x != w ? 1 : 0
mul y 0
add y 25
mul y x
add y 1
mul z y     z *= ((25 * x) + 1)  <26 or 1>
mul y 0
add y w
add y 8
mul y x
add z y     z += (w+(offset2)) * x

if z is zero, the next round z will be non-zero
if "offset1 > 10", z will be   +=(w + offset2)

if x == 1 => z *= 26 => z += (w+offset2) => next round : x = w+offset2 + offset1
if x == 0 => z unchanged

         */
        return null;
    }

    public static boolean isValidate(long ver, List<List<String>> ops) {
        String verStr = String.valueOf(ver);
        if(verStr.contains("0")) return false;
        return false;
    }
}

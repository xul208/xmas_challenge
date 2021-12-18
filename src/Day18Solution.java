import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day18Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        List<String> input = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("day18_example1.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                input.add(line);
                line = reader.readLine();
            }
            reader.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(input);
        for (String s : input) {
            TreeNode tree = parseInputLine(s);
            System.out.println(tree);
        }
    }

    public static class TreeNode {
        public int val;
        public int level;
        public TreeNode parent;
        public List<TreeNode> children;

        public TreeNode(TreeNode parent, int value) {
            this.parent = parent;
            this.val = value;
            this.level = parent == null ? 0 : parent.level + 1;
            this.children = new ArrayList<>();
        }

        public boolean isLeaf() {
            return val >= 0;
        }

        public String toString() {
            return Integer.toString(val);
        }
    }

    static TreeNode parseInputLine(String line) {
        int len = line.length();
        TreeNode root = new TreeNode(null, -1);
        TreeNode prev = root;
        List<TreeNode> leaves = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            char current = line.charAt(i);
            switch (current) {
                case '[' -> {
                    TreeNode firstNode = new TreeNode(prev, -1);
                    prev.children.add(firstNode);
                    firstNode.parent = prev;
                    prev = firstNode;
                }
                case ',' -> {
                    prev = prev.parent;
                    TreeNode secondNode = new TreeNode(prev, -1);
                    prev.children.add(secondNode);
                    secondNode.parent = prev;
                    prev = secondNode;
                }
                case ']' -> prev = prev.parent;
                default -> {
                    prev.val = Character.getNumericValue(current);
                    leaves.add(prev);
                }
            }
        }
        // scan leaves to perform explode and split
        for (int i = 0; i < leaves.size(); ++i) {

        }
        return root;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day18Solution {
    public static void main(String[] args) {
        BufferedReader reader;
        List<String> input = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("day18_1.txt"));
            String line = reader.readLine();
            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeNode prevTree = null;
        for (String s : input) {
            TreeNode tree = deserialize(s);
            if (prevTree != null) {
                prevTree = prevTree.add(tree);
                prevTree = deserialize(prevTree.toString());
            } else {
                prevTree = tree;
            }
        }
        System.out.println(prevTree);
        System.out.println(prevTree.getMagnitude());

        //second question
        int maxMagnitude = 0;
        for (int i = 0; i < input.size(); ++i) {
            for (String s : input) {
                if (s.equals(input.get(i))) {
                    continue;
                }
                TreeNode resultTree = deserialize(deserialize(input.get(i)).add(deserialize(s)).toString()) ;
                int currentSum = resultTree.getMagnitude();
                System.out.println("---------------");
                System.out.println(input.get(i));
                System.out.println(s);
                System.out.println(resultTree);
                System.out.println(currentSum);
                maxMagnitude = Math.max(maxMagnitude, currentSum);
            }
        }

        System.out.println(maxMagnitude);
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

        public TreeNode add(TreeNode otherTree) {
            TreeNode newRoot = new TreeNode(null, -1);
            newRoot.children.add(this);
            newRoot.children.add(otherTree);
            return newRoot;
        }

        public int getMagnitude() {
            if (val >= 0) {
                return val;
            } else {
                return 3 * (children.get(0).getMagnitude()) + 2 * (children.get(1).getMagnitude());
            }
        }

        public String toString() {
            if (val >= 0) {
                return Integer.toString(val);
            }
            StringBuilder sb = new StringBuilder("[");
            try {
                sb.append(this.children.get(0).toString());
                sb.append(",");
                sb.append(this.children.get(1).toString());
                sb.append("]");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return sb.toString();
        }
    }

    static TreeNode deserialize(String line) {
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
        explode(root, leaves);
        return root;
    }

    static void explode(TreeNode root, List<TreeNode> leaves) {
        for (int i = 0; i < leaves.size(); ++i) {
            TreeNode currentLeaf = leaves.get(i);
            if (currentLeaf.level > 5) {
                throw new Error("Level cannot be larger than 5");
            } else if (currentLeaf.level == 5) {
                TreeNode parentToExplode = currentLeaf.parent;
                if (i > 0) {
                    leaves.get(i - 1).val += currentLeaf.val;
                }
                if (i < leaves.size() - 2) {
                    leaves.get(i + 2).val += leaves.get(i + 1).val;
                }
                parentToExplode.val = 0;
                parentToExplode.children = Collections.emptyList();
                leaves.remove(i);
                leaves.remove(i);
                leaves.add(i, parentToExplode);
            }
        }
        split(root, leaves);
    }

    static void split(TreeNode root, List<TreeNode> leaves) {
        boolean hasNewChange = false;
        for (int i = 0; i < leaves.size(); ++i) {
            TreeNode currentLeaf = leaves.get(i);
            if (currentLeaf.val >= 10) {
                int leftVal = currentLeaf.val / 2;
                int rightVal = (currentLeaf.val + 1) / 2;
                currentLeaf.val = -1;
                TreeNode newLeft = new TreeNode(currentLeaf, leftVal);
                TreeNode newRight = new TreeNode(currentLeaf, rightVal);
                currentLeaf.children = new ArrayList<>();
                currentLeaf.children.add(newLeft);
                currentLeaf.children.add(newRight);
                leaves.remove(i);
                leaves.add(i, newRight);
                leaves.add(i, newLeft);
                hasNewChange = true;
                break;
            }
        }
        if (hasNewChange) {
            explode(root, leaves);
        }
    }
}

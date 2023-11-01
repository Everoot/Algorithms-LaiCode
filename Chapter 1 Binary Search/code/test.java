import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static class TreeNode{
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key){
            this.key = key;
        }

    }
    public static void main(String[] args){
        TreeNode rootM = new TreeNode(1);
        TreeNode rootR = new TreeNode(2);
        TreeNode rootP = new TreeNode(3);
        TreeNode rootA = new TreeNode(4);
        TreeNode rootX = new TreeNode(5);
        TreeNode rootL = new TreeNode(6);
        TreeNode rootB = new TreeNode(7);
        TreeNode rootH = new TreeNode(8);
        TreeNode rootQ = new TreeNode(9);
        TreeNode rootZ = new TreeNode(10);
        TreeNode rootS = new TreeNode(11);

        rootM.left = rootR;
        rootM.right = rootP;
        rootR.left = rootA;

        rootA.right = rootX;
        rootP.left = rootL;
        rootL.left = rootB;
        rootL.right = rootH;
        rootP.right = rootQ;
        rootQ.left = rootZ;
        rootZ.right = rootS;

        System.out.println(Arrays.toString(preOrder(rootM).toArray()));
        System.out.println(Arrays.toString(inOrder(rootM).toArray()));
        System.out.println(Arrays.toString(postOrder(rootM).toArray()));
    }

    public static List<Integer> preOrder(TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    public static void helper(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        //System.out.println(root.key);
        res.add(root.key);
        helper(root.left, res);
        helper(root.right, res);
    }

    public static List<Integer> inOrder(TreeNode root){
        List<Integer> res  = new ArrayList<Integer>();
        helperin(root, res);
        return res;
    }

    public static void helperin(TreeNode root, List<Integer> res){
        if (root == null ){
            return;
        }

        helperin(root.left, res);
        res.add(root.key);
        //System.out.println(root.value);
        helperin(root.right, res);
    }

    public static List<Integer> postOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<Integer>();
        helperpost(root, res);
        return res;
    }

    public static void helperpost(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        helperpost(root.left, res);
        helperpost(root.right, res);
        res.add(root.key);
    }


}

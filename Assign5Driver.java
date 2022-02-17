package assign5BST;
/** 
 * COSC 2100 – Fall 2021 
 * Assignment#5 
 * <driver class to test the functionality of the new methods in BST.java> 
 * @author <Claire Kraft> 
 */

public class Assign5Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// adding elements to BTS
		BST<Integer> tree = new BST<Integer>();
		BST<Integer> tree1 = new BST<Integer>();
		tree.add(10);
		tree.add(12);
		tree.add(14);
		tree.add(13);
		tree.add(16);
		tree.add(18);
		//tree.add(21);
		
		tree1.add(7);
		tree1.add(9);
		tree1.add(11);
		tree1.add(10);
		tree1.add(13);
		tree1.add(15);
		//tree1.add(12);
		
		BST<String> tree2 = new BST<String>();
		BST<String> tree3 = new BST<String>();
		tree2.add("d");
		tree2.add("c");
		tree2.add("a");
		tree2.add("b");
		tree2.add("e");
		tree2.add("f");
		tree2.add("g");
		
		tree3.add("d");
		tree3.add("b");
		tree3.add("f");
		tree3.add("a");
		tree3.add("c");
		tree3.add("e");
		tree3.add("g");
		
		// print statments to clarify the method calls
		
		System.out.print("Leaf Nodes: ");
		tree2.printLeafNodes();
		System.out.println("");
		System.out.print("Number of Interior nodes: ");
		System.out.println(tree2.countInterior());
		System.out.print("Number of nodes with exactly one child node: ");
		System.out.println(tree2.num1ChildNodes());
		System.out.print("Full Bianary search tree: ");
		System.out.println(tree2.isFullBinaryTree());
		System.out.print("Identical trees: ");
		System.out.println(tree2.isIdentical(tree3));
		
		
		//int minLevels = (int) (Math.log(4) +1);
		//System.out.print(minLevels);
		
	}
	

}

package assign5BST;

import ch07.trees.BinarySearchTree;

//----------------------------------------------------------------------------
//BinarySearchTree.java          by Dale/Joyce/Weems                Chapter 7
//
//Defines all constructs for a reference-based BST
//----------------------------------------------------------------------------

/** 
 * COSC 2100 – Fall 2021 
 * Assignment#5 
 * <Holds the methods for creating a binary search tree> 
 * @edited by <Claire Kraft> 
 */

public class BST<T extends Comparable<T>> implements BSTInterface<T>{
	protected BSTNode<T> root;      // reference to the root of this BST

	boolean found;   // used by remove

	public BST()
	// Creates an empty BST object.
	{
		root = null;
	}

	public boolean isEmpty()
	// Returns true if this BST is empty; otherwise, returns false.
	{
		return (root == null);
	}

	private int recSize(BSTNode<T> node)
	// Returns the number of elements in tree.
	{
		if (node == null)    
			return 0;
		else
			return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
	}

	public int size()
	// Returns the number of elements in this BST.
	{
		return recSize(root);
	}

	private boolean recContains(T element, BSTNode<T> node)
	// Returns true if tree contains an element e such that 
	// e.compareTo(element) == 0; otherwise, returns false.
	{
		if (node == null)
			return false;       // element is not found
		else if (element.compareTo(node.getInfo()) < 0)
			return recContains(element, node.getLeft());   // Search left subtree
		else if (element.compareTo(node.getInfo()) > 0)
			return recContains(element, node.getRight());  // Search right subtree
		else
			return true;        // element is found
	}

	public boolean contains (T element)
	// Returns true if this BST contains an element e such that 
	// e.compareTo(element) == 0; otherwise, returns false.
	{
		return recContains(element, root);
	}

	private T recGet(T element, BSTNode<T> node)
	// Returns an element e from tree such that e.compareTo(element) == 0;
	// if no such element exists, returns null.
	{
		if (node == null)
			return null;             // element is not found
		else if (element.compareTo(node.getInfo()) < 0)
			return recGet(element, node.getLeft());          // get from left subtree
		else
			if (element.compareTo(node.getInfo()) > 0)
				return recGet(element, node.getRight());         // get from right subtree
			else
				return node.getInfo();  // element is found
	}

	public T get(T element)
	// Returns an element e from this BST such that e.compareTo(element) == 0;
	// if no such element exists, returns null.
	{
		return recGet(element, root);
	}

	private BSTNode<T> recAdd(T element, BSTNode<T> node)
	// Adds element to tree; tree retains its BST property.
	{
		if (node == null)
			// Addition place found
			node = new BSTNode<T>(element);
		else if (element.compareTo(node.getInfo()) <= 0)
			node.setLeft(recAdd(element, node.getLeft()));    // Add in left subtree
		else
			node.setRight(recAdd(element, node.getRight()));   // Add in right subtree
		return node;
	}

	public void add (T element)
	// Adds element to this BST. The tree retains its BST property.
	{
		root = recAdd(element, root);
	}

	private T getPredecessor(BSTNode<T> node)
	// Returns the information held in the rightmost node in tree
	{
		while (node.getRight() != null)
			node = node.getRight();
		return node.getInfo();
	}

	private BSTNode<T> removeNode(BSTNode<T> node)
	// Removes the information at the node referenced by tree.
	// The user's data in the node referenced by tree is no
	// longer in the tree.  If tree is a leaf node or has only
	// a non-null child pointer, the node pointed to by tree is
	// removed; otherwise, the user's data is replaced by its
	// logical predecessor and the predecessor's node is removed.
	{
		T data;
		if (node.getLeft() == null) 
			return node.getRight();
		else if (node.getRight() == null) 
			return node.getLeft();
		else
		{
			data = getPredecessor(node.getLeft());
			node.setInfo(data);
			node.setLeft(recRemove(data, node.getLeft()));  
			return node;
		}
	}

	private BSTNode<T> recRemove(T element, BSTNode<T> node)
	// Removes an element e from tree such that e.compareTo(element) == 0
	// and returns true; if no such element exists, returns false. 
	{
		if (node == null)
			found = false;
		else if (element.compareTo(node.getInfo()) < 0)
			node.setLeft(recRemove(element, node.getLeft()));
		else if (element.compareTo(node.getInfo()) > 0)
			node.setRight(recRemove(element, node.getRight()));
		else  
		{
			node = removeNode(node);
			found = true;
		}
		return node;
	}

	public boolean remove (T element)
	// Removes an element e from this BST such that e.compareTo(element) == 0
	// and returns true; if no such element exists, returns false. 
	{
		root = recRemove(element, root);
		return found;
	}

	//Preorder traversal
	public void preorder() {
		System.out.println("\nPreorder Traversal: ");
		if(root==null)
			System.out.println("Tree is empty");
		else {
			recPreorder(root);
		}
	}
	private void recPreorder(BSTNode<T> node) {	
		if(node!= null) {
			System.out.print(node.getInfo()+ " ");
			recPreorder(node.getLeft());
			recPreorder(node.getRight());
		}
	}

	//Inorder traversal
	public void inorder() {
		System.out.println("\nInorder Traversal: ");
		if(root==null)
			System.out.println("Tree is empty");
		else {
			recInorder(root);
		}
	}
	private void recInorder(BSTNode<T> node) {	
		if(node!= null) {
			recInorder(node.getLeft());
			System.out.print(node.getInfo()+ " ");
			recInorder(node.getRight());		
		}
	}

	//Postorder traversal
	public void postorder() {
		System.out.println("\nPostorder Traversal: ");
		if(root==null)
			System.out.println("Tree is empty");
		else {
			recPostorder(root);
		}
	}
	private void recPostorder(BSTNode<T> node) {	
		if(node!= null) {
			recPostorder(node.getLeft());
			recPostorder(node.getRight());
			System.out.print(node.getInfo()+ " ");
		}
	}
	
	
	
	
	
	// Methods for Assignment 5
	
	// this method prints all of the leafs nodes with in a bianary search tree
	public void printLeafNodes() {
		
		// if tree is empty print so
		if (root == null)
			System.out.println("Tree is empty");
		
		// else call the recursive method 
		else {
			recPrintLeafNodes(root);	
			}	
		}
	
	// recursive method for print thr leaf nodes
	public void recPrintLeafNodes(BSTNode<T> location) {
		
		// if the node does not have any child nodes print the info from node
		if (location.getLeft() == null && location.getRight() == null ) {
			System.out.print(location.getInfo()+ " ");
		}
		
		// if the node just has a left child node, call method for left child 
		else if (location.getLeft() != null && location.getRight() == null )
			recPrintLeafNodes(location.getLeft());
		
		// if the node just has a right child node, call method for right child 
		else if (location.getLeft() == null && location.getRight() != null )
			recPrintLeafNodes(location.getRight());
		
		// if node has two child nodes call method again for each node 
		else {
			recPrintLeafNodes(location.getRight());
			recPrintLeafNodes(location.getLeft());
		}
	}
	
	
	
	// this method makes a count of all the non leaf nodes or the the interior nodes
	public int countInterior() {
		
		// if tree is empty return zero 
		if (root == null)
			return 0;
		
		// else call the recursive method
		else {
			return recCountInterior(root);	
			}
	}
	
	// recursive call for counting interior nodes
	public int recCountInterior(BSTNode<T> location) {
		
		// if the node has no child nodes return zero 
		if (location.getLeft() == null && location.getRight() == null)
			return 0;
		
		// if the node just has a left node return 1 + called method for the left child 
		else if (location.getLeft() != null && location.getRight() == null) {
			return 1 + recCountInterior(location.getLeft());
		}
		
		// if the node just has a right node return 1 + called method for the right child 
		else if (location.getLeft() == null && location.getRight() != null) {
			return 1 + recCountInterior(location.getRight());
		}
		
		// if the node has two child nodes return 1 + called method for both child nodes
		else 
			return 1 + recCountInterior(location.getRight()) + recCountInterior(location.getLeft());
	}
	
	
	
	
	// Counts the number of nodes with exactly one child node
	public int num1ChildNodes() {
		
		// if tree is empty return 0
		if (root == null)
			return 0;
		
		// else call the recursice method 
		else {
			return RecNum1ChildNodes(root);	
			}
	}
	
	// recursive call for count the number of nodes with exactly one child nodes
	public int RecNum1ChildNodes(BSTNode<T> location) {
		
		// if the node has no children return zero 
		if ((location.getLeft() == null && location.getRight() == null))
			return 0;
		
		// if node has two children nodes, didn't add annything to the count but call method for both child nodes
		else if(location.getLeft() != null && location.getRight() != null)
			return 0 + RecNum1ChildNodes(location.getLeft()) + RecNum1ChildNodes(location.getRight());
		
		// if node has left child add one to count and call method for left node 
		else if (location.getLeft() != null) {
			return 1 + RecNum1ChildNodes(location.getLeft());
		}
		// if the node has right child add one to the count and call method for the right node
		else
			return 1 + RecNum1ChildNodes(location.getRight());
	}
	
	
	
	
	// this method checks if the tree is full and returns true of false
	public boolean isFullBinaryTree() {
		
		// if tree is empty return false 
		if (root == null)
			return false;
		
		// compare the actual size to the maxnodes of the tree height 
		else {
			int size = this.size();
			double maxNodes = 0;
			for (int i = 0; i <= this.height(); i++) {
				maxNodes = maxNodes + Math.pow(2, i);
			}
			if (maxNodes == size)
				return true;
			else
				return false;
			}
	}
	
	// call the recursive method for finding the height of the tree
	public int height() {
		return recHeight(root);
		
	}
	
	// recursive method to find the height of the tree
	public int recHeight(BSTNode<T> node) {
		if (node == null) {
			return -1;
		}
		else
			return 1+ Math.max(recHeight(node.getLeft()), recHeight(node.getRight()));
		
	}
	

	
	
	
	
	
	
	
	// this method compared to trees and returns true if they are identical in values and structure
	public boolean isIdentical(BST<T> other) {
		
		// return recursive method for comparing the two trees
	    	return recIsIdentical(this.root, other.root);	
	    }
	
	// recusive call for comparing the two BST
	boolean recIsIdentical(BSTNode<T> bst, BSTNode<T> other) {
		
		// if both nodes are empty return true
		if (bst == null && other == null)
	        return true;
		
		// if one is null and one is not return false
	    else if (bst != null && other == null)
	        return false;
	    else if (bst == null && other != null)
	        return false;
		
		// if both node are not null compare the info in each node and their child nodes
		else if (bst.getInfo() == other.getInfo() && recIsIdentical(bst.getLeft(), other.getLeft()) == true &&
	            recIsIdentical(bst.getRight(), other.getRight()) == true)
	            return true;
	    else
	            return false;
	}
	
	
}
import java.io.*;
import java.util.*;

class Node {
      int value;
      Node left;
      Node right;
      
      Node(int value) {
         this.value = value;
         right = null;
         left = null;
      }
}

public class BinarySearchTree { 
   Node root;

   private Node addRecursive(Node current, int value) {
      if (current == null) { // if there is no root already
         return new Node(value);
      }
      if (value < current.value) {
         current.left = addRecursive(current.left, value);
      }
      else if (value > current.value) {
         current.right = addRecursive(current.right, value);
      }
      else { // if value already exists, return the same root
         return current;
      }
      return current;
   }
   
   public void add(int value) {
      root = addRecursive(root, value);
   }
   
   private boolean containsRecursive(Node current, int value) {
      if (current == null) { // there is no root to compare
         return false;
      }
      if (value == current.value) { // found the number in the tree
         return true;
      }
      return value < current.value 
         ? containsRecursive(current.left, value)
         : containsRecursive(current.right, value);
   }
   
   public boolean contains(int value) {
      return containsRecursive(root, value);
   }
   
   private int findSmallestValue(Node root) {
      return root.left == null ? root.value : findSmallestValue(root.left);
   }
   
   private Node removeRecursive(Node current, int value) {
      if (current == null) { // if there is no root already
         return null;
      }
      if (value == current.value) {
         if(current.left == null && current.right == null) { //if a node has no children
            return null;
         }
         if(current.right == null) { //if a node has one child
            return current.left;
         }
         if(current.left == null) { //if a node has one child
            return current.right;
         }
         int smallestValue = findSmallestValue(current.right); // if a node has two children
         current.value = smallestValue;
         current.right = removeRecursive(current.right, smallestValue);
         return current;
      }
      if (value < current.value) {
         current.left = removeRecursive(current.right, value);
      }
      current.right = removeRecursive(current.right, value);
      return current;
   }
   
   public void remove(int value) {
      root = removeRecursive(root, value);
   }
   
   public void traverseInOrder() {
      traverseInOrder(root);
   }
   
   public void traverseInOrder(Node node) { // first visits the left subtree, then root, then right subtree
      if (node != null) {
         traverseInOrder(node.left);
         System.out.print(" " + node.value);
         traverseInOrder(node.right);
      }
   }
   
   public void traversePreOrder() {
      traversePreOrder(root);
   }
   
   public void traversePreOrder(Node node) { // first visits the root, then the left subtree, then right subtree
      if (node != null) {
         System.out.print(" " + node.value);
         traversePreOrder(node.left);
         traversePreOrder(node.right);
      }
   }
   
   public void traversePostOrder() {
      traversePostOrder(root);
   }
   
   public void traversePostOrder(Node node) { // first visits the left subtree, then right subtree, then the root
      if (node != null) {
         traversePostOrder(node.left);
         traversePostOrder(node.right);
         System.out.print(" " + node.value);
      }
   }
   
   public void traverseLevelOrder() { // visits all the nodes of a level before going to the next level
      if (root == null) {
         return;
      }
      
      Queue<Node> nodes = new LinkedList<>();
      nodes.add(root); 
      
      while (!nodes.isEmpty()) {
         Node node = nodes.remove();
         
         System.out.print(" " + node.value);
         
         if (node.left != null) {
            nodes.add(node.left);
         }
         
         if (node.right != null) {
            nodes.add(node.right);
         }
      }
   }
   
   public static void main(String[] args) {
      BinarySearchTree bst = new BinarySearchTree();

      bst.add(6);
      bst.add(4);
      bst.add(8);
      bst.add(3);
      bst.add(5);
      bst.add(7);
      bst.add(9);
      
      bst.traverseInOrder();
      System.out.println();
      bst.traversePreOrder();
      System.out.println();
      bst.traversePostOrder();
      System.out.println();
      bst.traverseLevelOrder();
   }
}
package service;

import datastr.MyBST;
import datastr.MyBSTNode;


public class MainService {

	public static void main(String[] args) {
		System.out.println("----------------BST (INTEGERS)------------------");
		MyBST<Integer> bstForInteger = new MyBST<Integer>();
	
		try {
			System.out.println("----------------ADD------------------");
			bstForInteger.add(12);
			bstForInteger.add(8);
			bstForInteger.add(18);
			bstForInteger.add(5);
			bstForInteger.add(11);
			System.out.println(bstForInteger.treeHeight()); 
			
		/*
			MyBSTNode<Integer> node12 = bstForInteger.searchNode(12);
			if (node12 != null) {
			    int diff = bstForInteger.levelDifference(node12);
			    System.out.println("Level difference at node 12: " + diff);
			} else {
			    System.out.println("Node 12 not found");
			}*/
			int diff = bstForInteger.levelDifference(12);
			System.out.println("Level difference at node 12: " + diff);

			
			
			bstForInteger.print();
			/*          12
				       /  \
				      8    18
				     / \
				    5  11
*/
			bstForInteger.makeEmpty();
			System.out.println("is empty!!!");
			bstForInteger.add(30);
			bstForInteger.add(20);
			bstForInteger.add(10);
			bstForInteger.add(9);
			bstForInteger.print();
			/*         30
				       /
				     20
				     /
				   10
				   /
				  9
 */
			System.out.println("----------------------------------");
			MyBSTNode<Integer> node30 = bstForInteger.searchNode(30);
			bstForInteger.rightRotate(node30);
			bstForInteger.add(7);
			MyBSTNode<Integer> node10 = bstForInteger.searchNode(10);
			bstForInteger.rightRotate(node10);
			bstForInteger.print();
			/*         
			 * 20
		      /  \
		     9    30
		    /  \
		   7    10
*/
			bstForInteger.makeEmpty();
			System.out.println("is empty!!!");
			System.out.println("----------------add function with balancing------------------");
			bstForInteger.addElement(30);
			bstForInteger.addElement(20);
			bstForInteger.addElement(10);
			bstForInteger.addElement(9);
			bstForInteger.addElement(7);
			bstForInteger.print();
			/*         20
				      /  \
				     9    30
				    / \
				   7  10
*/
			
			//System.out.println("----------------SEARCH------------------");
			//System.out.println("6? -> " + bstForInteger.search(6));//true
			//System.out.println("15? -> " + bstForInteger.search(15));//true
			//System.out.println("100? -> " + bstForInteger.search(100));//false
			
			
			//-----------------------------SĀKAS PIEVIENOTAIS KODA FRAGMENTS--------------------------
			//System.out.println("----------------DELETE------------------");
			//bstForInteger.delete(7);//izdzēšot 7, ta vietā vajadzētu ielikt 10
			//bstForInteger.print();
			
			//-----------------------------BEIDZAS PIEVIENOTAIS KODA FRAGMENTS--------------------------
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
package datastr;

import java.util.LinkedList;

public class MyBST<Ttype> {
	
	private MyBSTNode<Ttype> root = null;
	
	private int counter = 0;

	private boolean isFull() {
		try {
			new MyBSTNode<Integer>(45); 
			return false;
		} catch (OutOfMemoryError e) {
			return true;
		}
	}

	private boolean isEmpty() {
		return (counter == 0);
	}

	public int lenght() {
		return counter;
	}

	public void add(Ttype element) throws Exception {
		if (element == null) {
			throw new Exception("Element can not be null");
		}

		if (isEmpty()) {
			MyBSTNode<Ttype> newNode = new MyBSTNode(element);
			root = newNode;
			counter++;
		} else {
			if (isFull())
			{
				throw new Exception("BST is full");
			}

			addHelper(element, root);
		}

	}

	private void addHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		// pābraudam, pa kuru pusi elements tiks pievienots

		// ja elements ir lielāks par currentNode elementu, tad jāpavirzas uz labo pusi
		if (((Comparable) element).compareTo(currentNode.getElement()) == 1) {
			// ja pa neksiste labais bērns, tad var veidot jauno mezglu un pielipināt ka
			// labo bērnu
			if (currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setRightCh(newNode);
				newNode.setParent(currentNode);
				counter++;

			}
			// ja eksistē
			else {
				addHelper(element, currentNode.getRightCh());
			}

		}

		// ja elements ir mazaks par currentNode elementu, tad jāpavirzas uz kreiso pusi
		else if (((Comparable) element).compareTo(currentNode.getElement()) == -1) {
			// neeksiste kreisais bērns, tad izveidojam jaunu mezglu un pievienjam ka kreiso
			if (currentNode.getLeftCh() == null) {
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setLeftCh(newNode);
				newNode.setParent(currentNode);
				counter++;
			} else// ja tomēr jau eksiste kreisais bērns
			{
				addHelper(element, currentNode.getLeftCh());
			}
		}

	}
	
	//https://www.geeksforgeeks.org/find-the-maximum-depth-or-height-of-a-tree/
	//Izveidot funkciju, kas nosaka koka augstumu (0.5p).
	/*Types of DFS Traversals:
	Post-order DFS: Recursively visit the left and right children first, then visit the node.
	Order: Left → Right → Root(compute the result (height)).*/
	//Height is defined as number of edges on the longest path from currentNode down to a leaf.
	public int treeHeight() throws Exception {
		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to print the trees height");
		}
		//                       12
		return treeHeightHelper(root);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------

	
/*    12
     /  \
    8    18
   / \
  5  11

	 * */
	public int treeHeightHelper(MyBSTNode<Ttype> currentNode) throws Exception {
		if (currentNode == null) {
	        return -1; // Base case: null node has height -1
	    }
		//    1     =   treeHeightHelper(12.(8));
		//    0     =   treeHeightHelper(8.(5));
		//   -1     =   treeHeightHelper(5.(null));
        //	 -1     =   treeHeightHelper(11.(null));
		//   -1     =   treeHeightHelper(18.(null));
		int lHeight = treeHeightHelper(currentNode.getLeftCh());
		
	    //   0      =   treeHeightHelper(12.(18));
		//   0      =   treeHeightHelper(8.(11));
		//  -1      =   treeHeightHelper(5.(null)); 
		//  -1      =   treeHeightHelper(11.(null));
		//  -1      =   treeHeightHelper(18.(null));
        int rHeight = treeHeightHelper(currentNode.getRightCh());
        
        //Height of node 5 = max(-1, -1) + 1 = 0    
        //Height of node 11 = max(-1, -1) + 1 = 0
        //Height of node 8 = max(0, 0) + 1 = 1
        //Height of node 18 = max(-1, -1) + 1 = 0
        //Height of node 12 = max(1, 0) + 1 = 2
        return Math.max(lHeight, rHeight) + 1;
	}
/*   12
    /  \
   8   18
  / \
 5  11

	 * */
	/*
	//https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
	//Izveidot funkciju, kas nosaka līmeņu atšķirību starp konkrētā mezgla abiem apakškoka augstumiem (0.5p).
	public int levelDifference(MyBSTNode<Ttype> currentNode) throws Exception {
		if (currentNode == null) {
	        return 0; // Level difference for a null node is 0; Null node has no subtree height difference
	    }
            //    1     =   treeHeightHelper(12.(8));
			//    1     =   treeHeightHelper(8.(5));
		    //    0     =   treeHeightHelper(5.(null));
		    //	  0     =   treeHeightHelper(11.(null));
		    //    0     =   treeHeightHelper(18.(null));
		int leftHeight = treeHeightHelper(currentNode.getLeftCh());
	        //   1      =   treeHeightHelper(12.(18));
			//   1      =   treeHeightHelper(8.(11));
	        //   0      =   treeHeightHelper(5.(null)); 
			//   0      =   treeHeightHelper(11.(null));
			//   0      =   treeHeightHelper(18.(null));
			
		int rightHeight = treeHeightHelper(currentNode.getRightCh());
		//Height of node 5 = max(0, 0) + 1 = 1
		//Height of node 11 = max(0, 0) + 1 = 1
		//Height of node 8 = max(1, 1) + 1 = 2    		
		//Height of node 18 = max(0, 0) + 1 = 1
		
		//Math.abs(2 - 1) = 1
		return Math.abs(leftHeight - rightHeight);
	}*/
	
	public int levelDifference(Ttype element) throws Exception {
	    if (element == null) {
	        throw new Exception("Element cannot be null");
	    }
	    MyBSTNode<Ttype> node = searchNode(element);
	    if (node == null) {
	        throw new Exception("Element not found in the tree");
	    }
	    return levelDifference(node);
	}

	private int levelDifference(MyBSTNode<Ttype> currentNode) throws Exception {
	    if (currentNode == null) {
	        return 0; // Null node has no subtree height difference
	    }
	    int leftHeight = treeHeightHelper(currentNode.getLeftCh());
	    int rightHeight = treeHeightHelper(currentNode.getRightCh());
	    return Math.abs(leftHeight - rightHeight);
	}
	
	
	//https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/
	public void storeInorder(MyBSTNode<Ttype> currentNode, LinkedList<Ttype> nodes) {
	    if (currentNode == null)
	        return;

	    storeInorder(currentNode.getLeftCh(), nodes);
	    nodes.add(currentNode.getElement());
	    storeInorder(currentNode.getRightCh(), nodes);
	}
	/*          30
			    /
			  20
			  /
			10
			/
			9
*/
	//Izveidot funkciju, kas nodrošinās konkrētā mezgla apakškoku rotāciju uz labo pusi (0.5p).
	//                            rightRotate(30)
	public MyBSTNode<Ttype> rightRotate(MyBSTNode<Ttype> y) {
		//  30 == null (false) || 30.getLeftCh(20) == null(false)
	    if (y == null || y.getLeftCh() == null) {
	        return y; // Cannot rotate if no left child
	    }
	    //              x = 30.getLeftCh(20)
	    MyBSTNode<Ttype> x = y.getLeftCh();
	    //              T2 = 20.getRightCh(null);
	    MyBSTNode<Ttype> T2 = x.getRightCh();

	    // Perform rotation
	    //20.setRightCh(30)
	    x.setRightCh(y);
	    //30.setLeftCh(null)
	    y.setLeftCh(T2);
	    
	    /* 20
	     *   \
	     *    30
	     *   /
	     *  null */

	    // Update parents if necessary
	    if (T2 != null) {
	        T2.setParent(y);
	    }
        //      yParent = 30.getParent(20)
	    MyBSTNode<Ttype> yParent = y.getParent();
	    //20.setParent(20);
	    x.setParent(yParent);
	    //30.setParent(20);
	    y.setParent(x);

	    if (yParent != null) {
	    	// 20.getLeftCh(10) == 30
	        if (yParent.getLeftCh() == y) {
	            yParent.setLeftCh(x);
	        } else {
	            yParent.setRightCh(x);
	        }
	    } else {
	    	//root = 20
	        // y was root
	        root = x;
	    }

	    return x; // New root of rotated subtree
	}
	
	//Izveidot funkciju, kas nodrošinās tikai mezgla apakškoku rotāciju uz kreiso pusi (0.5p).
	public MyBSTNode<Ttype> rotateLeft(MyBSTNode<Ttype> x) {
	    if (x == null || x.getRightCh() == null) {
	        return x; // Cannot rotate if node or its right child is null
	    }

	    MyBSTNode<Ttype> y = x.getRightCh(); // y becomes the new root after rotation
	    x.setRightCh(y.getLeftCh()); // B becomes the right child of x
	    if (y.getLeftCh() != null) {
	        y.getLeftCh().setParent(x);
	    }
	    y.setLeftCh(x); // x becomes left child of y

	    // Update parents
	    y.setParent(x.getParent());
	    x.setParent(y);

	    return y; // Return new root after rotation
	}
	
	/*AVL koka gadījumā jebkura mezgla divu bērnu apakškoku augstumi atšķiras ne vairāk kā par vienu; 
	 * ja tie kādā brīdī atšķiras vairāk par vienu, tiek veikta līdzsvarošana, lai atjaunotu šo īpašību.  */
	
	//https://www.geeksforgeeks.org/insertion-in-an-avl-tree/
	//Papildināt esošo ievietošanas funcionalitāti, lai pēc elementa pievienošanas koks pašbalansētos (1p).
	public void addElement(Ttype element) throws Exception {
	    if (element == null) {
	        throw new Exception("Element can not be null");
	    }

	    if (isEmpty()) {
	        root = new MyBSTNode<>(element);
	        counter++;
	    } else {
	        if (isFull()) {
	            throw new Exception("BST is full");
	        }
	        root = addElementHelper(element, root); // notice we assign back to root because rotation might happen at root
	    }
	}

	private MyBSTNode<Ttype> addElementHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
	    if (currentNode == null) {
	        counter++;
	        return new MyBSTNode<>(element);
	    }

	    if (((Comparable) element).compareTo(currentNode.getElement()) < 0) {
	        currentNode.setLeftCh(addElementHelper(element, currentNode.getLeftCh()));
	        currentNode.getLeftCh().setParent(currentNode);
	    } else if (((Comparable) element).compareTo(currentNode.getElement()) > 0) {
	        currentNode.setRightCh(addElementHelper(element, currentNode.getRightCh()));
	        currentNode.getRightCh().setParent(currentNode);
	    } else {
	        // Duplicate keys are not allowed
	        return currentNode;
	    }

	    // After normal insertion, check balance
	    int balance;
	    try {
	        balance = levelDifference(currentNode);
	    } catch (Exception e) {
	        balance = 0;
	    }

	    // ===== AVL Rotations based on balance =====
	    
	    // Left Left Case
	    if (balance > 1 && ((Comparable) element).compareTo(currentNode.getLeftCh().getElement()) < 0) {
	        return rightRotate(currentNode);
	    }

	    // Right Right Case
	    if (balance < -1 && ((Comparable) element).compareTo(currentNode.getRightCh().getElement()) > 0) {
	        return rotateLeft(currentNode);
	    }

	    // Left Right Case
	    if (balance > 1 && ((Comparable) element).compareTo(currentNode.getLeftCh().getElement()) > 0) {
	        currentNode.setLeftCh(rotateLeft(currentNode.getLeftCh()));
	        return rightRotate(currentNode);
	    }

	    // Right Left Case
	    if (balance < -1 && ((Comparable) element).compareTo(currentNode.getRightCh().getElement()) < 0) {
	        currentNode.setRightCh(rightRotate(currentNode.getRightCh()));
	        return rotateLeft(currentNode);
	    }

	    return currentNode;
	}

	//Papildināt esošo dzēšanas funcionalitāti, lai pēc elementa izņemšanas koks pašbalansētos (1p).
	//šis nav
	
	// TODO
	// search
	public boolean search(Ttype element) throws Exception {
		if (element == null) {
			throw new Exception("Element can not be null");
		}

		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to search element");
		}

		boolean result = searchHelper(element, root);
		return result;

	}

	private boolean searchHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		if (element.equals(currentNode.getElement())) {
			return true;
		}

		else {
			// ja meklētais elements ir lielāks par currentNode elementu, tad jāpavirzas uz
			// labo pusi
			if (((Comparable) element).compareTo(currentNode.getElement()) == 1) {
				// pārbaudām, vai eksiste mezgls labājā pusē. Ja neeksistē, tad varam drošī
				// pateikt, ka elements nav atrasts, jo esam jau nokļuvisi pie vietas, kur tam
				// būtu jābūt, bet nav
				if (currentNode.getRightCh() == null) {
					return false;
				} else// eksistē bērna mezgls labajā pusē
				{
					return searchHelper(element, currentNode.getRightCh());
				}
			} // ja meklētais elements ir mazāks par currentNode elementu
			else if (((Comparable) element).compareTo(currentNode.getElement()) == -1) {
				// pārbaudām, vai eksiste mezgls kreisais pusē. Ja neeksistē, tad varam drošī
				// pateikt, ka elements nav atrasts, jo esam jau nokļuvisi pie vietas, kur tam
				// būtu jābūt, bet nav
				if (currentNode.getLeftCh() == null) {
					return false;
				} else// eksistē bērna mezgls kreisajā pusē
				{
					return searchHelper(element, currentNode.getLeftCh());
				}
			}
		}

		return false;

	}

	// print
	public void print() throws Exception {
		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to print elements");
		}

		printHelper(root);
	}

	// TODO uztaisīt funkcijas arī postfiksās un gala apstaigāšanas gadījumiem
	private void printHelper(MyBSTNode<Ttype> currentNode) {
		// PREFIX apstaigāsana Sakne-kreisais bēŗns - labais bērns
		System.out.println("P -> " + currentNode.getElement());

		// ja eksistē kreisais bērns
		if (currentNode.getLeftCh() != null) {
			System.out.println(
					"Left ch -> " + currentNode.getLeftCh().getElement() + " (" + currentNode.getElement() + ")");
			printHelper(currentNode.getLeftCh());
		}

		// ja eksistē labais bērns
		if (currentNode.getRightCh() != null) {
			System.out.println(
					"Right ch -> " + currentNode.getRightCh().getElement() + " (" + currentNode.getElement() + ")");
			printHelper(currentNode.getRightCh());
		}

	}
	// TODO

	// makeEmpty (skatīt piemēru no Sem2)
	public void makeEmpty() {
		if (!isEmpty()) {
			root = null;
			counter = 0;
			System.gc();
		}
	}

	// delete

	public void delete(Ttype element) throws Exception {
		if (isEmpty()) {
			throw new Exception("BST is empty and it is not possible to delete element");
		}

		if (!search(element)) // ja nebūs atrasts elements, ko grib dzest
		{
			throw new Exception("Element doesn't exists in BST and it is not possible to delete it");
		}

		deleteHelper(element, root);

	}

	private void deleteHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		// TODO samazinār coutner, pie atrastā elementa
		// ja sakritīs, tad dzēsīsim
		if (element.equals(currentNode.getElement())) {
			counter--;
			// ja currentNode mezgls ir kā lapa
			if (currentNode.getLeftCh() == null && currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();

				if (parentOfCurrentNode.getLeftCh() != null && parentOfCurrentNode.getLeftCh().getElement().equals(element)) {
				    parentOfCurrentNode.setLeftCh(null);
				} else if (parentOfCurrentNode.getRightCh() != null && parentOfCurrentNode.getRightCh().getElement().equals(element)) {
				    parentOfCurrentNode.setRightCh(null);
				}


			}

			// ja currentNode mezglam ir tikai viens bērns
			// gadījums, kad ir tikai labais bērns
			else if (currentNode.getLeftCh() == null && currentNode.getRightCh() != null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				MyBSTNode<Ttype> rightChildOCurrentNode = currentNode.getRightCh();

				parentOfCurrentNode.setRightCh(rightChildOCurrentNode);
				rightChildOCurrentNode.setParent(parentOfCurrentNode);
			}
			// ir tikai kreisais bērns
			else if (currentNode.getLeftCh() != null && currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				MyBSTNode<Ttype> leftChildOCurrentNode = currentNode.getLeftCh();

				parentOfCurrentNode.setLeftCh(leftChildOCurrentNode);
				leftChildOCurrentNode.setParent(parentOfCurrentNode);
			}
			/*        15                   |                  15                 
			           \                   |                   \
			           52                  |                  52
			          /   \                |                  /  \ 
			        20    64               |                20    64
			       /  \    /               |               /  \    /
			     17   26 63                |              17  26  63
			         /  \                  |                 /  \ 
			       23   34 <-remove        |                23  34 <-remove
			           /   \               |                    / \
			         27    39              |                   27  39
			               /               |                        \
			              38               |                        40 
			              /                |                         \
			             37                |                         41
			                               |
			                               |
			                               |
			          15                   |                 15   
			           \                   |                   \
			           52                  |                   52
			          /   \                |                  /   \
			        20    64               |                 20   64
			       /  \    /               |                /  \   /
			     17   26 63                |               17  26 63
			         /  \                  |                  / \
			       23   37                 |                23   39
			           /  \                |                    /  \
			         27   39               |                   27  40
			              /                |                         \
			             38                |                         41
			              
			             
 */
			
			
			//         10.(9)                             10.(20)
			// ja currentNode mezglam ir abi bērni
			else if (currentNode.getLeftCh() != null && currentNode.getRightCh() != null) {
				//------------------------------SĀKAS PIEVIENOTAIS KODA FRAGMENTS-----------------------------

				//                    10.(20) 34.(39) 34.(39) 37.(39)
				// Caur labo pusi, meklēs "kreisāko bērnu"  inorder successor can be obtained by finding the minimum value in the right child of the node.
				MyBSTNode<Ttype> temp = currentNode.getRightCh();
				//                      20 39 39 39
				System.out.println(temp.getElement());
				//             20.(null)  39.getLeftCh(null) 39.getLeftCh(null)
				if (temp.getLeftCh() == null) {
					//10.setElement(20)  34.setElement(39)    37.setElement(39)
					currentNode.setElement(temp.getElement());
					//10.setRightCh(null) 39.setRightCh(40) <- node 39 itself has been removed and replaced by its right child (40)
					//                    39.setRightCh(null)
					currentNode.setRightCh(temp.getRightCh());
					//              Since temp.getRightCh() is null, this step is skipped
					if (temp.getRightCh() != null) {
			            temp.getRightCh().setParent(currentNode);
			        }
					
				} else    
					while (temp.getLeftCh() != null) {
						//20.(12)  39.(38) 38.(37)
						temp = temp.getLeftCh();
					} //10.setElement(12) 34.setElement(37)
					currentNode.setElement(temp.getElement());
					
					//     12.getRightCh(17)   
					if (temp.getRightCh() != null) {
						//       parent = 12.getParent(20)                 
						MyBSTNode<Ttype> parent = temp.getParent();
						//       rightCh = 12.getRightCh(17)                 
						MyBSTNode<Ttype> rightCh = temp.getRightCh();
						//  20.setLeftCh(17)
						parent.setLeftCh(rightCh);
						//  17.setParent(20)
						rightCh.setParent(parent);
					}
					
					/*             15
						            \
						             52
						            /  \
						          20    64
						         /  \    /
						       17   26  63
						           /  \
						         23   37 <-delete
						             /  \
						           27    39
						                  
						                  
 */
					
					else // ja mezgls ir kā lapa, tad var uz to vienkārsi noņemt sasaisti no vecāka puses
					{
						// noņemam sasaisti vecākam uz to mezglu, kura vērtību uzlika dzēšamajā vietā
						//                   11.getParent(12)
						//          parent = 12.getParent(20)
						//          parent = 39.getParent(37)
						MyBSTNode<Ttype> parent = temp.getParent();
						//12.getLeftCh.equals(11)
						//20.getLeftCh.equals(12)
						// 27 == 39
						if (parent.getLeftCh().equals(temp)) {
							parent.setLeftCh(null); //12 == null 11 = null
							//39 == 39
						} else if (parent.getRightCh().equals(temp)) {
							//37.setRightCh(null)
							parent.setRightCh(null);
						}
					}
				}
				//------------------------------BEIDZAS PIEVIENOTAIS KODA FRAGMENTS----------------------------
		} else {
			// ja elements ir lielāks par currentNode elementu
			if (((Comparable) element).compareTo(currentNode.getElement()) == 1) {
				// izsaucam rekursiju, tikai tad, ja labajā pusē ir piesiastīts bērns
				if (currentNode.getRightCh() != null) {
					deleteHelper(element, currentNode.getRightCh());
				}
			} else if (((Comparable) element).compareTo(currentNode.getElement()) == -1) {
				// izsaucam rekursiju, tikai tad, ja kreisajā pusē ir piesiastīts bērns
				if (currentNode.getLeftCh() != null) {
					deleteHelper(element, currentNode.getLeftCh());
				}
			}
		}
	}
	public MyBSTNode<Ttype> searchNode(Ttype element) throws Exception {
	    if (element == null) {
	        throw new Exception("Element cannot be null");
	    }
	    if (isEmpty()) {
	        throw new Exception("BST is empty and it is not possible to search element");
	    }
	    return searchNodeHelper(element, root);
	}

	private MyBSTNode<Ttype> searchNodeHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
	    if (currentNode == null) {
	        return null;
	    }
	    int cmp = ((Comparable<Ttype>) element).compareTo(currentNode.getElement());
	    if (cmp == 0) {
	        return currentNode;
	    } else if (cmp < 0) {
	        return searchNodeHelper(element, currentNode.getLeftCh());
	    } else {
	        return searchNodeHelper(element, currentNode.getRightCh());
	    }
	}

	
}
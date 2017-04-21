/*
Given a list of child->parent relationships, build a binary tree out of it. All the element Ids inside the tree are unique. 

Example: 

Given the following relationships: 

Child Parent IsLeft 
15 20 true 
19 80 true 
17 20 false 
16 80 false 
80 50 false 
50 null false 
20 50 true 


You should return the following tree: 
50 
/ \ 
20 80 
/ \ / \ 
15 17 19 16 


Function Signature 


 
* Represents a pair relation between one parent node and one child node inside a binary tree 
* If the _parent is null, it represents the ROOT node 
 
public class Relation { 
public Integer _parent; 
public Integer _child; 
public boolean _isLeft; 
} 


/** 
* Represents a single Node inside a binary tree 
 
public class Node { 
public Integer _id; 
public Node _left; 
public Node _right; 
} 

/** 
* Implement a method to build a tree from a list of parent-child relationships
* And return the root Node of the tree 
 
	public Node buildTree (List<Relation> data) {	 
	
	}
*/





/*

One simple approach to this problem can be: we know the ROOT of the tree is the one where the parent is null in the list. 

Once we figure out the parent, we can iteratively figure out its children and their children- by looping over the complete list and finding out the ones that point the current node as its parent. 

To build tree efficiently, we can use queue to keep track of the tree built till then. The running time would be O(n^2), with constant space (not really, we are keeping a queue as well)

*/

public static Node buildTree(List<Relation> data){
	if(data==null) 
		return new Node();
	
	Node root=new Node();
	
	int children=0;
	
	for(int i=0;i<data.size();i++){
		Relation x=data.get(i);
		if(x.parent==null){ // find parent first
			root=new Node(x.child,null,null); // root's id will be relation.child
			break; // skip rest of the relations
		}
	}
	
	if(root==null) 
		return root;
	
	Queue<Node> q=new LinkedList<Node>();
	q.add(root);
	
	while(!q.isEmpty()){
		Node x = q.poll();
		
		for(int i=0;i<data.size();i++){
	
			Relation y = data.get(i);
			
			if(y.parent == x.id) { // if y is child of x
			
				Node n = new Node(y.child,null,null);
				
				if(y.isLeft)
					x.left=n; // assign to left
				else 
					x.right=n; // assign to right
				
				q.add(n);
				
				children++;
				
				if(children==2){
					children=0; // if we already found left and right children of parent, then no need to iterate other relations
					break;
				}
			}
		}
	}
	
	return root;
}
	
/*	

Another way to approach this problem for a better running time could be by using a HashMap. 

*/

public Node buildTree(List<Relation> data)
{
	HashMap<Integer, Node> map = new HashMap<Integer, Node>(); // key: node_value, value: node itself
	Node root = null;

	
	// keep interating over relations, and keep updating left and right as we go forth
	for(Relation r: data) {
		Node parent, child;

		if ((child = map.get(r.child)) == null) {
			child = new Node(r.child, null, null);
			map.put(r.child, child);
		}
		if (r.parent == null) {
			root = child; // root in all relations
			continue;
		}
		if ((parent = map.get(r.parent)) == null) {
			parent = new Node(r.parent, null, null);
			map.put(r.parent, parent);
		}

		if (r.isLeft) {
			parent.left = child;
		} 
		else {
			parent.right = child;
		}
	}
	return root;
}

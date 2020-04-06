
class node <T extends Comparable<T>>
{ 
	T key;
	T value;
	node left;
	node right;
	node parent;
	int height;
	node()
	{
		this.left = null;
		this.right = null;
		//this.parent = null;
	}
	node(T key, T value)
	{
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		height = 1;
	}
	void copy( node <T> N1)
	{
		this.key = N1.key;
		this.value = N1.value;
		this.left = N1.left;
		this.right = N1.right;
		this.parent = N1.parent;
	}  
}
 class TreeIterator {
    private node next;

    public TreeIterator(node root) {
        next = root;
        if(next == null)
            return;

        while (next.left != null)
           next = next.left;
    }

    public boolean hasNext(){
        return next != null;
    }

    public node next(){
        //if(!hasNext()) throw new NoSuchElementException();
        node r = next;

        // If you can walk right, walk right, then fully left.
        // otherwise, walk up until you come from left.
        if(next.right != null) {
            next = next.right;
            while (next.left != null)
                next = next.left;
            return r;
        }

        while(true) {
            if(next.parent == null) {
                next = null;
                return r;
            }
            if(next.parent.left == next) {
                next = next.parent;
               return r;
            }
            next = next.parent;
        }
     }
 }
class map <T extends Comparable<T>>
{
	node root;
	int count;
    //help function
	int height (node <T> Node)//function return heigh
	{
		if(Node != null) 
		{
			return Node.height;
		}
		else
			return 0;
	}
	int BF(node  Node)
	{
		return (Node == null) ? 0 : height(Node.right) - height(Node.left);
	}
	void Overheigh (node <T> Node)
	{
		if(height(Node.left) >= height(Node.right))  
			Node.height = height(Node.left) + 1;
		if(height(Node.left) < height(Node.right))
			Node.height = height(Node.right) + 1;
	}
    node parent (node Node, node Temp)
    {
        return Temp.parent = Node;
    }
    //insert
    node insert(node Node, T key, T value) 
    {
        if (Node == null) {
            return new node(key, value);
        } else if (Node.key.compareTo(key) > 0) {
            Node.left = insert(Node.left, key, value);
             parent(Node ,Node.left);
        } else if (Node.key.compareTo(key) < 0) {
            Node.right = insert(Node.right, key, value);
            parent(Node ,Node.right);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return balance(Node);
    }
    //balance tree
    node balance(node Node) 
    {
        Overheigh(Node);
        int balance = BF(Node);
        if (balance > 1) {
            if (height(Node.right.right) > height(Node.right.left)) {
                Node = little_left_rotate(Node);
            } else {
                Node.right = little_right_rotate(Node.right);
                Node = little_left_rotate(Node);
            }
        } else if (balance < -1) {
            if (height(Node.left.left) > height(Node.left.right)) {
                Node = little_right_rotate(Node);
            } else {
                Node.left = little_left_rotate(Node.left);
                Node = little_right_rotate(Node);
            }
        }
        return Node;
    }
	  
   //Rotate
   node <T> little_right_rotate(node <T> Rgh)
    {
    	node Lf = new node ();
    	Lf = Rgh.left;
    	//Lf.parent=Rgh;
    	Rgh.left = null;
    	Rgh.left = Lf.right;
    	Lf.right = Rgh;
    	Overheigh(Lf);
    	Overheigh(Rgh);
        System.out.println("rightRT");
    	return Lf;
    }
    node <T> little_left_rotate(node <T> Lf)
    {
    	node Rgh = new node ();
    	Rgh = Lf.right;
    	Lf.right = Rgh.left;
    	Rgh.left = Lf;
    	Overheigh(Lf);
    	Overheigh(Rgh);
        System.out.println("leftRT");
    	return Rgh;
    }
    node <T> big_left_rotate(node <T> Lf)
    {
        if ( Lf.right == null) 
        {
            return Lf;
        }
        Lf.right = little_right_rotate(Lf.right);
        return little_left_rotate(Lf);
    }
    node <T> big_right_rotate(node <T> Rgh)
    {
        if (Rgh.left == null) 
        {
            return Rgh;
        }

        Rgh.left = little_left_rotate(Rgh.left);

        return little_right_rotate(Rgh);
    }
//print
    
 void print()
    {
        if(this.root != null)
        {
            print_tree(this.root);
        }
        else
        {
            System.out.println( "Tree does not exist" );
        }
    }
    private void print_tree( node <T> Node )
    {
        if( Node != null)
        {
            print_tree( Node.left );
            System.out.println(Node.height +"   " + Node.key.toString());
            print_tree( Node.right );
        }
    }
    public String description() {
        return diagramFor(this.root, "", "", "");
    }

    private String diagramFor(node<T> Node, String top, String root, String bottom) {

        if ( Node == null ) { return root + "null\n"; }

        if ( Node.left == null && Node.right == null ) {
            return root + String.valueOf(Node.key) + "\n";
        }

        return diagramFor(Node.right, top + " ", top + "┌──", top + "│ ") 
                + root + String.valueOf(Node.key) + "\n" 
                + diagramFor(Node.left, bottom + "│ ", bottom + "└──", bottom + " ");
    }
//find
    public void ChangeValue (T key, T value)
    {
        node current = find(key);
        if(current == null)
        {
            System.out.println("Tree has not this key");
        }
        else
        {
             current.value = value;
        }



    }
    public T value (T key)
    {
        return find(key).value;
    }
    public node <T> find (T key) 
    {
        node current = root;
        while (current != null)
        {
            if (current.key.equals(key) == true)
            {
               break;
            }
            if(current.key.compareTo(key) < 0)
            {
                current = current.right;
            }
            else
            {
                current = current.left;
            }
        }
        return current;
    }
    //delete node
     node delete(node Node, T key)
    {
        if (Node == null) 
        {
            return Node;
        } 
        else if (Node.key.compareTo(key) > 0) 
        {
            Node.left = delete(Node.left, key);
        } 
        else if (Node.key.compareTo(key) < 0) 
        {
            Node.right = delete(Node.right, key);
        } 
        else 
        {
            if (Node.left == null || Node.right == null) 
            {
                Node = (Node.left == null) ? Node.right : Node.left;
            } 
            else 
            {
                node mostLeftChild = mostLeftChild(Node.right);
                Node.key = mostLeftChild.key;
                Node.right = delete(Node.right, key(mostLeftChild));
            }
        }
          if (Node != null) 
        {
            Node = balance(Node);
        }
        return Node;
    }
    private node mostLeftChild(node Node) {
        node current = Node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    T key(node Node)
    {
        T k;
        k=(T)Node.key;
        return k;
    }
    //iterator
}
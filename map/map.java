
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
		this.parent = null;
	}
	node(T key, T value)
	{
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		height=1;
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

class map <T extends Comparable<T>>
{
	node root;
	int count;
	int height (node <T> Node)//function return heigh
	{
		if(Node != null) 
		{
			return Node.height;
		}
		else
			return 0;
	}
	int BF(node <T> Node)
	{
		return height(Node.right)-height(Node.left);
	}
	void Overheigh (node <T> Node)
	{
		if(height(Node.left) >= height(Node.right))  
			Node.height = Node.left.height + 1;
		if(height(Node.left) < height(Node.right))
			Node.height = Node.right.height + 1;
	}
	void Add(T key, T value)
	{
		//System.out.println( key.toString() );
		if (this.root == null)
        {
            this.root = new node(key,value);
          //  System.out.println( key.toString() );
            count++;
        }
        else
        {
            Addto(this.root, key,value);
        }
	}
	node <T> Addto(node <T> Node, T key, T value)
    {
        if ( key.compareTo(Node.key) < 0)
        {
           	
            if(Node.left == null)
            {
                Node.left = new node(key, value);
         		Node.left.parent= Node;
                //Overheigh(Node);
              // System.out.println(Node.key.toString());
                count++;
            }
            else
            {
                Addto(Node.left, key, value);
            }
        }
        else if (key.compareTo(Node.key) >= 0)
        {
     
            if(Node.right==null)
            {
                Node.right=new node(key, value);
                Node.right.parent=Node;
                //Overheigh(Node);
               //System.out.println(Node.key.toString());
                count++;
            }
            else
            {

                Addto(Node.right,key, value);
            }
        }
       //node <T> balanceNode = Balance (Node);
        //BalanceNode = Overheigh(balanceNode);
        return Balance(Node);
    }
    void print()
    {
        if(this.root!=null)
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
            System.out.println(Node.height+"   " + Node.key.toString());
            print_tree( Node.right );
        }
    }
    void rotate(node Node)
    {
    	//Node.parentlittle_right_rotate(Node);
    }
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

    node <T> Balance (node <T> Node)
    {
       Overheigh(Node);
        if(BF(Node) == 2)
        {
            if ( BF(Node.right) < 0 ) 
            {
                    Node.right = little_right_rotate(Node.right);
                    return little_left_rotate(Node);
            }

        }
        if (BF(Node) == -2)
        {
            if (  BF(Node.left) > 0 ) 
            {
                    Node.left = little_left_rotate(Node.left);
                    return little_right_rotate(Node);
                
            }
    
        }
    	
        return Node;
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
   
}

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
		return Node.right.height-Node.left.height;
	}
	void Overheigh (node <T> Node)
	{
		if(Node.left.height > Node.right.height)  
			Node.height = Node.left.height + 1;
		else
			Node.height = Node.right.height + 1;
	}
	void Add(T key, T value)
	{
		//System.out.println( key.toString() );
		if (this.root==null)
        {
            this.root=new node(key,value);
          //  System.out.println( key.toString() );
            count++;
        }
        else
        {
            Addto(this.root, key,value);
        }
	}
	void Addto(node <T> Node, T key, T value)
    {
        if ( key.compareTo(Node.key) < 0)
        {
           	
            if(Node.left == null)
            {
                Node.left = new node(key, value);
         		Node.left.parent= Node;
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
               //System.out.println(Node.key.toString());
                count++;
            }
            else
            {

                Addto(Node.right,key, value);
            }
        }
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
        if( Node!=null)
        {
            print_tree( Node.left );
            System.out.println( Node.key.toString() );
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
    	Lf= Rgh.left;
    	//Lf.parent=Rgh;
    	Rgh.left = null;
    	Rgh.left= Lf.right;
    	Lf.right=Rgh;
    	Overheigh(Lf);
    	Overheigh(Rgh);
    	return Lf;
    }
    node <T> little_left_rotate(node <T> Lf)
    {
    	node Rgh = new node ();
    	Rgh = Lf.right;
    	Lf.right=Rgh.left;
    	Rgh.left=Lf;
    	Overheigh(Lf);
    	Overheigh(Rgh);
    	return Rgh;
    }

    node <T> Balance (node <T> Node)
    {
    	return Node;
    }
   
}
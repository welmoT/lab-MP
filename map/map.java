
class field <T extends Comparable<T>>
{
	T key;
	T value;

}
class node <T extends Comparable<T>>
{ 
	field field;
	node left;
	node right;
	node parent;
	node(field field)
	{
		this.field=field;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
}

class map <T extends Comparable<T>>
{
	node root;
	int count;
	void Add(field field)//T key,T value
	{
		if(this.root==null)
        {
            this.root=new node(field);
            count++;
        }
        else
        {
            Addto(this.root, field);
        }
	}
	void Addto(node <T> Node, field field)
    {
        if(field.key.compareTo(Node.field.key) <0)
        {
           	
            if(Node.left==null)
            {
                Node.left=new node(field);
                count++;
            }
            else
            {
                Addto(Node.left,field);
            }
        }
        else if (field.key.compareTo(Node.field.key)>=0)
        {
     
            if(Node.right==null)
            {
                Node.right=new node(field);
                count++;
            }
            else
            {

                Addto(Node.right,field);
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
            System.out.println("Tree does not exist");
        }
    }
    private void print_tree(node <T> node)
    {
        if(node!=null)
        {
            print_tree(node.left);
            System.out.println(node.field.key.toString());
            print_tree(node.right);
        }
        //System.out.println("count "+count);
    }
}
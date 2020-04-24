
   class Node <T extends Comparable<T>>
	{
		uint [] data; //array  key and values
		Node [] children;
		Node parent;	
		int size ;	
		boolean leaf;		//true if this node is  leaf
		
		public Node(int t)
		{
			this.data = new uint[2*t-1];
			this.children = new Node[2*t];
			this.parent = null;
			this.leaf = true;
			this.size = 0;
		}
	}
 class uint <T extends Comparable<T>>  //contain key and value
{
	T key;
	T  value;
	uint( T key,T value)
	{
		this.key = key;
		this.value = value;
	}
}
 class btree <T extends Comparable<T>> 
{
	static Node root;	

	static int t = 3;	//value of parameter t.
	btree()
	{
		this.root = null;
	}
	btree (btree root1)
	{
		this.root.size = root1.root.size;
		this.root.leaf = root1.root.leaf;
		this.root.parent = root1.root.parent;
		this.root.data = root1.root.data;
		this.root.children = root1.root.children;

	}
	
	private  void insertData(Node node, uint da) //insert key and values in the node's data array
	{
		int index = node.size;
		for(int i=node.size-1;i>-1;i--)
		{
			if(da.key.compareTo(node.data[i].key) <= 0)
			{
				node.data[i+1] = node.data[i];
				index = i;
			}
			else
			{
				break;
			}
		}
		node.data[index] =da;
		node.size++;
	}
       public Node splitNode(Node node, uint data) // split the node if it is completely filled
        {
		int mid = node.size/2;
		Node node1 = new Node(t);
		Node node2 = new Node(t);
		Node parent = node.parent;
		
		for(int i=0;i<mid;i++)
		{
			node1.data[i] = node.data[i];
			node1.children[i] = node.children[i];
			node1.size++;
		}
		node1.children[mid] = node.children[mid];
		
		int j=0;
		for(int i=mid+1;i<node.size;i++)
		{
			node2.data[j] = node.data[i];
			node2.children[j] = node.children[i];
			node2.size++;
			j++;
		}
		node2.children[mid] = node.children[node.size];
		
		node1.leaf = node.leaf;
		node2.leaf = node.leaf;
		uint temp = node.data[mid];
		if(parent==null)
		{
			node.data = new uint[2*t-1];
			node.data[0] = temp;
			node.size=1;
			node.leaf = false;
			node.children[0] = node1;
			node.children[1] = node2;
			node1.parent = node;
			node2.parent = node;
			if(data.key.compareTo(temp.key) > 0)
			{
				return node2;
			}
			return node1;
		}
		
		int index = parent.size;
		uint datatemp =node.data[mid];
		for(int i=parent.size-1;i>-1;i--)
		{
			if(node.data[mid].key.compareTo(parent.data[i].key) < 0)
			{
				parent.data[i+1] = parent.data[i];
				parent.children[i+2] = parent.children[i+1];
				index = i;
			}
			else
			{
				break;
			}
		}
		parent.data[index] = datatemp;
		parent.children[index] = node1;
		parent.children[index+1] = node2;
		parent.size++;
		node1.parent = parent;
		node2.parent = parent;
		if(data.key.compareTo(datatemp.key) > 0)
		{
			return node2;
		}
		return node1;
	}
		

	public  void put(uint data){
		if(root == null)
		{
			Node node = new Node(t);
			insertData(node,data);
			root = node;
			return;
		}
		
		Node temp = root;
		while(!temp.leaf || (temp.size==2*t-1 && temp.children[0] == null))
		{
			if(temp.size == 2*t-1)
			{
				temp = splitNode(temp,data);
				continue;
			}
			if(data.key.compareTo(temp.data[0].key) < 0)
			{
				temp = temp.children[0];
				continue;
			}
			if(data.key.compareTo(temp.data[temp.size-1].key) > 0)
			{
				temp = temp.children[temp.size];
				continue;
			}
			for(int i = 1;i < temp.size; i++){
				if(data.key.compareTo(temp.data[i].key) < 0)
				{
					temp = temp.children[i];
					break;
				}
			}
		}
		insertData(temp,data);
	}
	
	public static void print(Node node, int level)
	{
		if(node==null)
		{
			return;
		}
		System.out.print("Level : " + level + " " + "Data : ");
		for(int i=0;i<node.size;i++)
		{
			System.out.print(node.data[i].key.toString() + " ");
		}
		System.out.println();
		if(node.leaf)
		{
			return;
		}
		for(int i=0;i<node.size+1;i++){
			print(node.children[i],level+1);
		}
	}
	//search key
	boolean TreeSearch(T key)
	{
		return Search (key, root);
	}
	
	boolean Search (T key, Node node)
	{
		Node [] TempChild = node.children; 
		int k = 0;
		T temp;
		for(int i = 0;i < node.size; i++)
		{
			 temp = (T)node.data[i].key;
			//System.out.println(node.data[i].key.toString());

			if(key.compareTo(temp) == 0)
			{
			 return true;
			}
			if(key.compareTo(temp) < 0) return Search(key, TempChild[i]);
			if(key.compareTo(temp) > 0)  continue; //return Search(key,TempChild[node.size]);
		}
		return false;
	}
	//function for value
	T getValue(T key, Node node)
	{
		Node [] TempChild = node.children; 
		int k = 0;
		T temp;
		for(int i = 0;i < node.size; i++)
		{
			 temp = (T)node.data[i].key;
 
			if(key.compareTo(temp) == 0)
			{
			 return (T)node.data[i].value;
			}
			if(key.compareTo(temp) < 0) return getValue(key,TempChild[i]);
			if(key.compareTo(temp) > 0)  continue; 
		}
		return null;
	}
	T ChangeValue(T key,T value, Node node)
	{
		Node [] TempChild = node.children; 
		int k = 0;
		T temp;
		for(int i = 0;i < node.size; i++)
		{
			 temp = (T)node.data[i].key;
			if(key.compareTo(temp) == 0)
			{
				node.data[i].value = value;
			 return (T)node.data[i].value;
			}
			if(key.compareTo(temp) < 0) return ChangeValue(key,value,TempChild[i]);
			if(key.compareTo(temp) > 0)  continue;
		}

			
		return null;
	}
	void deleteAll(Node node)
	{
			int i = 0;
				for(int j = 0;j < node.size; j++)
				{
					node.data[j] = null;
					node.children[j] = null;
				}
				//node.children[i] = null;
				//if(i < node.size) deleteAll(node);
		node.parent = null;
		root = null;
	}
	boolean empty (Node node)
	{
		if(node == null) return true;
		else return false;

	}
	
}
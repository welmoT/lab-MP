
class field <T>
{
	private T key;
	private T value;
	field ()
	{
		this.key = key;
		this.value = value;
	}
}
class node <T extends Comparable<T>>
{ 
	private field field;
	private node left;
	private node right;
	private node parent;
	node(T key, T value)
	{
		this.left = null;
		this.right = null;
		this.parent = null;
	}
}

class map <T extends Comparable<T>>
{
	node root;
	int count;
}

public class TreeLocatorNode <T>  {
	public Location loc;
	public List<T> data = new LinkedList<T>();
	public TreeLocatorNode<T> c1, c2,c3,c4;
	

	public TreeLocatorNode(T v ,Location location) {
		loc=location ;
		data.insert(v);
	   c1 = c2 = c3=c4= null;
	}
	
	public TreeLocatorNode(Location location, T val, TreeLocatorNode<T> C1, TreeLocatorNode<T> C2,TreeLocatorNode<T> C3,TreeLocatorNode<T> C4) {
		loc=location;
		data.insert(val);
		c1=C1;
		c2=C2;
		c3=C3;
		c4=C4;
	}
	

}

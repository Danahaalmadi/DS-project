
public class BSTNode <K extends Comparable<K>,T> {
		public K key;
		public T data;
		public BSTNode<K,T> left, right;
		
	
		public BSTNode(K ke, T val) {
			key = ke;
			data = val;
			left = right = null;
		}
		
		public BSTNode(K ke, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
			key = ke;
			data = val;
			left = l;
			right = r;
		}
		
		
	}



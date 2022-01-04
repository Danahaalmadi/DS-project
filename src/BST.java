
public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	BSTNode<K,T> root, current;

	@Override
	public boolean empty() {
		
		return root==null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
		
	}

	@Override
	public void update(T e) {
		current.data = e;
		

	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K,T> p=root;
		Boolean found = Boolean.valueOf(false);
		Integer i = Integer.valueOf(0);
		
		 while(p != null) {
		    if(key.compareTo(p.key)==0) 
		      {current = p;
				found=true;
				i++;
				break;}
			else
			{
			 if(key.compareTo(p.key)<0)
			   { p = p.left; 
	            	i++;}
			else
				{p = p.right;
				i++;}}
		     }
		
		 Pair<Boolean,Integer> pai = new Pair<Boolean,Integer>(found,i);
				 return pai;
		
	}

	@Override
	  public Pair<Boolean, Integer> insert(K key, T data) {
     BSTNode<K,T> p=root, q = root;
     Boolean found = Boolean.valueOf(false);
	 Integer i = Integer.valueOf("0");
     BSTNode<K,T> bstk=new BSTNode<K,T>(key,data);

     if(empty())
     {
     root=bstk;
     found=true;
     current=bstk;}
     
		else if((find(key).first)==false)
		 {    found=true;
			 while(p!=null)
			 { 
			   if(key.compareTo(p.key)<0)
				   {q=p; 
                    p = p.left; 
		            	i++;
                      }
				else
					{q=p;
                   p = p.right;
					i++;
                }
               
				 }
			 
			 if(key.compareTo(q.key)<0)
			 { q.left=bstk;
			 current=bstk;
			 }
			 else
			 { q.right=bstk;
			 current=bstk;}
			 }
		 else i=find(key).second ;
				 Pair<Boolean,Integer> pai = new Pair<Boolean,Integer>(found,i);
		 return pai;}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
	     Boolean found = Boolean.valueOf(false);
		 Integer i = Integer.valueOf("0");
		  K ke =key;
	      BSTNode<K,T> p = root;
	      BSTNode<K,T> q = null; 
	      while (p != null) {

	         if (ke.compareTo(p.key)<0) {
	            q =p;
	            p = p.left;
	            i++;
	         } else if (ke.compareTo(p.key)>0) {
	            q = p;
	            p = p.right;
	            i++;
	         }
	         else { 
	             if ((p.left != null) && (p.right != null)) { 
	                BSTNode<K,T> min = p.right;
	                q = p;
	                while (min.left != null) {
	                   q = min;
	                   min = min.left;
	                }
	                p.key = min.key;
	                p.data = min.data;
	                ke = min.key;
	                p = min;
	                 }
	                if (p.left != null) { 
	                 p = p.left;} 
	                else { p = p.right; }
	                if (q == null) { 
	                    root = p;
	                 } else {
	                    if (ke.compareTo(q.key)<0) {
	                       q.left = p;
	                    } else {
	                       q.right = p;
	                    }
	                 }
	                 current = root;
	                 found=true;
	              }}
	      Pair<Boolean,Integer> pai = new Pair<Boolean,Integer>(found,i);
			 return pai;
			 }
	         
	     

	@Override
	public List<K> getAll() {
		         BSTNode<K,T> p=root;
		         List <K> list  = new LinkedList<K>();
		         inOrderT(p, list);
		         return list;
             	}

private void inOrderT( BSTNode<K,T>p, List<K> list) {
		            if (p!= null) {
		            inOrderT(p.left, list);
		            list.insert(p.key);
		            inOrderT(p.right,list);}
		    }

                                                       }

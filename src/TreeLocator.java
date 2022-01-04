
public class TreeLocator<T> implements Locator<T> {
	public TreeLocatorNode<T> root , current;
	@Override
	public int add(T e, Location loc) {
		TreeLocatorNode<T> p = root,q=null;
		TreeLocatorNode<T> n  = new TreeLocatorNode<T>(e,loc);
		int i=0;
		if(root==null)
			{root=n;}
		else
		    {
			while(p!=null)
		    {
			if(loc.x==p.loc.x &&loc.y==p.loc.y)
					 {i++;
					 p.data.insert(e);
					 break;}
			else if(loc.x<p.loc.x &&loc.y<=p.loc.y)
			      {q=p;
				   i++;
			       p=p.c1; }
			else if(loc.x<=p.loc.x &&loc.y>p.loc.y)
			       {q=p;
				    i++;
			        p=p.c2;
			        }
			else if(loc.x>p.loc.x &&loc.y>=p.loc.y)
		           {q=p;
				    i++;
		            p=p.c3;}
			else if(loc.x>=p.loc.x &&loc.y<p.loc.y)
	           {q=p;
				i++;
	            p=p.c4;}
		      }
			  if(p==null)
				 {
				 if(loc.x<q.loc.x && loc.y<=q.loc.y)
					 q.c1=n;
				 else
					 if(loc.x<=q.loc.x && loc.y>q.loc.y)
						 q.c2=n;
					 else
						 if(loc.x>q.loc.x && loc.y>=q.loc.y)
							 q.c3=n;
						 else
							 if(loc.x>=q.loc.x && loc.y<q.loc.y)
								 q.c4=n;
					 
					 }
				 }
	        	return i;}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		  List <T> list  = new LinkedList<T>();
			 Integer i = Integer.valueOf("0");
			 TreeLocatorNode<T> p = root;
			 while(p!=null)
			    {
				if(loc.x==p.loc.x &&loc.y==p.loc.y)
					   {i++;
					     list=p.data;
						 break;}
				else if(loc.x<p.loc.x &&loc.y<=p.loc.y)
				      { i++;
				       p=p.c1; }
				else if(loc.x<=p.loc.x &&loc.y>p.loc.y)
				       {i++;
				        p=p.c2; }
				else if(loc.x>p.loc.x &&loc.y>=p.loc.y)
			           {i++;
			            p=p.c3;}
				else if(loc.x>=p.loc.x &&loc.y<p.loc.y)
		              {i++;
		                p=p.c4;}}
			 Pair<List<T>,Integer> pai = new Pair<List<T>,Integer>(list,i);
			 return pai; }
			 
	


	@Override  
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		 Integer i = Integer.valueOf("0");
		 Boolean found = Boolean.valueOf(false);
		 TreeLocatorNode<T> p = root;
		 while(p!=null)
		    {
			if(loc.x==p.loc.x &&loc.y==p.loc.y)
				   {i++;
					 break;}
			else if(loc.x<p.loc.x &&loc.y<=p.loc.y)
			      { i++;
			       p=p.c1; }
			else if(loc.x<=p.loc.x &&loc.y>p.loc.y)
			       {i++;
			        p=p.c2; }
			else if(loc.x>p.loc.x &&loc.y>=p.loc.y)
		           {i++;
		            p=p.c3;}
			else if(loc.x>=p.loc.x &&loc.y<p.loc.y)
	              {i++;
	                p=p.c4;}}
	     if(p!=null)
	    { p.data.findFirst();
			  while(p.data.last()!=true)
			  { if(p.data.retrieve().equals(e))
			      {p.data.remove();
				  found=true;
				  break;}
			      else p.data.findNext();} 
			  if(p.data.retrieve().equals(e))
		      {p.data.remove();
			  found=true;}
		    }
	     Pair<Boolean,Integer> pai = new Pair<Boolean,Integer>(found,i);
	      return pai;}
	

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		TreeLocatorNode <T> p=root;
		List<Pair<Location, List<T>>> Nodes= new LinkedList<Pair<Location, List<T>>>();
		 allNodes(p, Nodes);
	     return Nodes;}
		
    
	private void allNodes(TreeLocatorNode <T> p,List<Pair<Location, List<T>>> Nodes) {
		if(p==null) return;
		
		Pair<Location, List<T>> np =new Pair<Location, List<T>>(p.loc,p.data);
		Nodes.insert(np);
		allNodes(p.c1,Nodes);	
		allNodes(p.c2,Nodes);	
		allNodes(p.c3,Nodes);	
		allNodes(p.c4,Nodes);	
	
		
	}
	
	
	
	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		TreeLocatorNode <T> p=root;
		  Integer i = Integer.valueOf("0");
		  List<Pair<Location, List<T>>> Rnodes= new LinkedList<Pair<Location, List<T>>>();
		  Pair<List<Pair<Location,List<T>>>, Integer> pai= new  Pair<List<Pair<Location, List<T>>> ,Integer>(Rnodes,i);
		  findRange(p, lowerLeft, upperRight, pai);
		  return pai;
	}

	private void findRange(TreeLocatorNode<T>p,Location lowerLeft, Location upperRight, Pair<List<Pair<Location,List<T>>>, Integer> pai)
	{
		      if(p==null)
		      	return;
		      else {
		    	  pai.second++;
		      if((lowerLeft.x<=p.loc.x&&p.loc.x<=upperRight.x)&&(lowerLeft.y<=p.loc.y&&p.loc.y<=upperRight.y))
		    	{Pair<Location, List<T>> np =new Pair<Location, List<T>>(p.loc,p.data);
					pai.first.insert(np);
		      findRange(p.c1,lowerLeft,upperRight,pai);
		      findRange(p.c2,lowerLeft,upperRight,pai);
		      findRange(p.c3,lowerLeft,upperRight,pai);
		      findRange(p.c4,lowerLeft,upperRight,pai);}
		      else if(upperRight.x<p.loc.x&&upperRight.y<=p.loc.y)
		    	  findRange(p.c1,lowerLeft,upperRight,pai);
		    	
		      else if(upperRight.x<=p.loc.x&&lowerLeft.y>p.loc.y)
		    	  findRange(p.c2,lowerLeft,upperRight,pai);
		      
		      else if(lowerLeft.x>p.loc.x&&lowerLeft.y>=p.loc.y)
		    	  findRange(p.c3,lowerLeft,upperRight,pai);
		      
		      else if(lowerLeft.x>=p.loc.x&&lowerLeft.y<p.loc.y)
		    	  findRange(p.c4,lowerLeft,upperRight,pai);
		      
		      else if(upperRight.y<p.loc.y)
		      { findRange(p.c1,lowerLeft,upperRight,pai);
		    	  findRange(p.c4,lowerLeft,upperRight,pai);  }
		      
		      else if(lowerLeft.y>p.loc.y)
		      { findRange(p.c2,lowerLeft,upperRight,pai);
		    	  findRange(p.c3,lowerLeft,upperRight,pai);  }
		      
		      else if(upperRight.x<p.loc.x)
		      { findRange(p.c1,lowerLeft,upperRight,pai);
		    	  findRange(p.c2,lowerLeft,upperRight,pai);  }
		      
		      else if(lowerLeft.x<p.loc.x)
		      { findRange(p.c2,lowerLeft,upperRight,pai);
		    	  findRange(p.c3,lowerLeft,upperRight,pai);  }
}
		 
	}
}

	
	
	

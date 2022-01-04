
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	Map<K, Location> map;
	Locator<K>locs;
	
	public TreeLocatorMap()
	{
		map = new BST<K, Location>();
		locs= new TreeLocator<K>();
	}
	@Override
	public Map<K, Location> getMap() {
		return map;
	}

	@Override
	public Locator<K> getLocator() {
		return locs;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer> pai=map.insert(k, loc);
		if(pai.first==true)
		locs.add(k, loc);
		return pai;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair<Boolean, Integer> pai=map.find(k);
		if(pai.first==true)
			{
			locs.remove(k,map.retrieve() );
			locs.add(k, loc);
			map.update(loc);
			}
		return pai;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean, Integer> pai=map.find(k);
		Pair<Location, Integer> pai2;
		if(pai.first==true)
		 pai2=new Pair<Location, Integer>(map.retrieve(),pai.second);	
		else
		 pai2=new Pair<Location, Integer>(null,pai.second);
		return pai2;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		 Boolean found = Boolean.valueOf(false);
		Pair<Boolean, Integer> pai=map.find(k);
		Pair<Boolean, Integer> pai1=new Pair<Boolean,Integer>(found,pai.second);
		 if(pai.first==true)
		 if(locs.remove(k,map.retrieve()).first==true&&map.remove(k).first==true)
			 {found=true;
			 pai1=new Pair<Boolean, Integer>(found,pai.second);
			 }
		  return pai1;
	}

	@Override
	public List<K> getAll() {
		 List <K> list  = map.getAll();
		return list;
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
	    List<K> KL = new LinkedList<K>();
		List<Pair<Location, List<K>>>list=locs.inRange(lowerLeft, upperRight).first;
		list.findFirst();
		while(list.last()==false){
		Pair<Location, List<K>> p =list.retrieve();
		List<K> l =p.second;
		l.findFirst();
		while(l.last()==false&&l.empty()==false)
		{K k=l.retrieve();
			if(doseExist(KL ,k)==false)
				KL.insert(k);
			   l.findNext();}
		K k=l.retrieve();
		if(doseExist(KL ,k)==false)
			KL.insert(k);
		 list.findNext();
		}
		Pair<Location, List<K>> p =list.retrieve();
		List<K> l =p.second;
		l.findFirst();
		while(l.last()==false&&l.empty()==false)
		{K k=l.retrieve();
			if(doseExist(KL ,k)==false)
				KL.insert(k);
			   l.findNext();}
		K k=l.retrieve();
		if(doseExist(KL ,k)==false)
			KL.insert(k);
		int i =locs.inRange(lowerLeft, upperRight).second;
	
		Pair<List<K>, Integer> NP=new Pair<List<K>, Integer>(KL,i);
		
		return NP;
	}
	
	
	
	private boolean doseExist(List<K> p,K d) {
		if (p.empty())
			return false;
			p.findFirst();
		while(p.last()==false)
		{if(p.retrieve().compareTo(d)==0)
				return true;
			p.findNext();	}
		if(p.retrieve().compareTo(d)==0)
			return true;
		return false;
		
		
	}

}
	
	
	


package org.civex.utils.collections;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.civex.utils.IObjectFilter;
import org.civex.utils.KeyNumericPair;
import org.civex.utils.ObjectHashComparator;

public class FrequencyBag<T,K extends KeyNumericPair<T>> extends AbstractCollection<T>
{
	private final List<K> l;
	private final Comparator<T> c;
	private final IObjectFilter<List<K>,T> filter;
	private final K valDefault; 
	
	
	public FrequencyBag(Comparator<T> comp)
	{
		this(comp,new ArrayList<K>(),null);
	}
	
	public FrequencyBag()
	{
		this(new ObjectHashComparator.DirectHashComparator<T>(),new ArrayList<K>(),null);
	}
	
	public FrequencyBag(List<K> list)
	{
		this(new ObjectHashComparator.DirectHashComparator<T>(),list,null);
	}
	
	public FrequencyBag(final Comparator<T> c, List<K> list, IObjectFilter<List<K>,T> filter)
	{
		this.c=c;
		this.l=list;
		this.filter=filter!=null?filter:null;
		valDefault=createPairObject(null);
	}
	
	
	
	protected List<K> getCollImpl() {return l;}
	protected IObjectFilter<List<K>,T> getCollectionFilter(){return filter;}
	
	
	@Override
	public Object[] toArray() 
	{
		final int sz=size();
		final Object o[]=new Object[sz];
		for (int ii=0;ii<sz;ii++) o[ii]=l.get(ii).getKey();
		return o;
	}


	@Override
	public boolean add(T e) 
	{
		if (filter!=null && (!filter.filter(e))) return false;
		return put(e); 
	}


	@Override
	public void clear() 
	{
		this.valDefault.setValue(0);
		for (final KeyNumericPair<T> pair:l) pair.setValue(0);
	}


	protected boolean put(final T o)
	{
		final int sz=l.size();
		for (int ii=0;ii<sz;ii++)
		{
			final KeyNumericPair<T> pair=l.get(ii);
			if (0!=c.compare(o,pair.getKey())) continue;
			pair.inc();
			reconcile(pair,o);
			return true;
		}
		if (putNew(o)) return true;
		valDefault.inc();
		return false;
	}
	
	@Override
	public boolean contains(Object o) 
	{
		return l.contains(o);
	}

	protected void reconcile(KeyNumericPair<T> pair, T o) 
	{}



	protected boolean putNew(final T o)
	{
		final K pair=createPairObject(o);
		l.add(pair);
		reconcile(pair,o);
		return true;
	}
	
	
	
	protected K createPairObject(T o)
	{
		return (K)new KeyNumericPair<T>(o,1);
	}
	
	public T getByIndex(int index)
	{
		return l.get(index).getKey();
	}
	
	public K getIndexRow(int index)
	{return l.get(index);}
	
	
	@Override
	public boolean remove(Object o) 
	{
		for (int ii=l.size()-1;ii>=0;ii--)
		{
			final K pair=l.get(ii);
			//if (c.compare(o, o2))
		}
		return false;
			
	}


	@Override
	public Iterator<T> iterator() 
	{
		return null;
	}

	@Override
	public int size() 
	{
		return l.size();
	}
	
	public int getTotalCounter()
	{
		int counter=0;
		final int sz=l.size();
		for (int ii=0;ii<sz;ii++) counter+=l.get(ii).getValueAsInt();
		return counter;
	}
	
	
	public int getMissedCounter()
	{
		return valDefault.getValueAsInt();
	}
	

	public T getModeElement()
	{
		return null;
	}
	
	public T getMedianElement()
	{
		return null;
	}
	
	public T getBestToAverageElement()
	{
		final int sz=l.size();
		double v=getTotalCounter();
		double avg=v/sz;
		double diff=Double.MAX_VALUE;
		int el=-1;
		for (int ii=1;ii<sz;ii++) 
			{
				final double dd=Math.min(diff,l.get(ii).diffAbs(avg));
				if (dd>diff) continue;
				el=ii;
				diff=dd;
			}
		return l.get(el).getKey();
	}
	
	
	public double getAverageByBasket()
	{
		final int sz=l.size();
		double v=getTotalCounter();
		return v/sz;
	}
	
	public double getStdDev()
	{
		final int sz=l.size();
		final double avg=getAverageByBasket();
		double r=0;
		for (int ii=0;ii<sz;ii++) 
			{
				r+=l.get(ii).diffSqr(avg);
			}
		return Math.sqrt(r/(sz*sz));
	}
	
	public K getRandomBagElement()
	{
			final double vv=Math.random();
			final int sz=getTotalCounter();
			final int v=(int)(vv*sz);
			int cnt=0;
			for (int ii=0;ii<sz;ii++)
			{
				final K el=l.get(ii);
				final int next=cnt+el.getValueAsInt();
				if (KeyNumericPair.between(v,cnt,next)) return el;
				cnt=next;
			}
			return null;
			
	}
	
	

}

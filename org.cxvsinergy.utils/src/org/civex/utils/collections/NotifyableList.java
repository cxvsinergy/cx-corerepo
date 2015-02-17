package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.civex.utils.INotificationListener;
import org.civex.utils.INotificationListener.OP;

public class NotifyableList<T> extends AbstractList <T>
{
	private final List<INotificationListener<List<T>, T>> listNotifiers=new ArrayList<INotificationListener<List<T>, T>>(10);
	private List<T> stgList=new ArrayList<T>();
	// list properties
	protected boolean uniquetList=false;
	protected boolean sortedList=false;
	protected int 	  maxSize=Integer.MAX_VALUE;
		
	
	@Override
	public final void add(int index, T element) 
	{
		stgList.add(index, element);
		fireNotification(element, OP.ADD);
	}

	@Override
	public final boolean add(T element) 
	{
		add(this.size(), element);
		return true;
	}

	@Override
	public final boolean addAll(int index, Collection<? extends T> c) 
	{
		fireNotification(null, OP.TX_START);
		for(final T element:c) add(index++,element);
		fireNotification(null, OP.TX_END);
		return true;
	}

	@Override
	public T get(int index) 
	{
		return stgList.get(index);
	}

	@Override
	public int size() 
	{
		return stgList.size();
	}

	@Override
	public void clear() 
	{
		stgList.clear();
		fireNotification(null, OP.CLEAR);
	}

	@Override
	public T remove(int index) 
	{
		final T o=stgList.remove(index);
		if (o!=null) fireNotification(o, OP.REMOVE);
		return o;
	}

	/**indicated that this list is sorted list**/
	public boolean isSorted()
	{
		return sortedList;
	}
	
	/**indicated that this list has only unique values**/
	public boolean isUniqueList()
	{
		return uniquetList;
	}
	
	/**indicated that this list is available**/
	public boolean isAvailable()
	{
		return true;
	}
	
	public void addNotificationListener(INotificationListener<List<T>, T> listener)
	{
		if (listNotifiers.contains(listener)) return;
		listNotifiers.add(listener);
	}
	
	public void removeNotificationListener(INotificationListener<List<T>, T> listener)
	{
		listNotifiers.remove(listener);
	}
	
	
	protected void fireNotification(T element, INotificationListener.OP op)
	{
		for (int ii=listNotifiers.size()-1;ii>=0;ii--) 
			{
				listNotifiers.get(ii).onElementChanged(this, element, op);
			}
	}
}

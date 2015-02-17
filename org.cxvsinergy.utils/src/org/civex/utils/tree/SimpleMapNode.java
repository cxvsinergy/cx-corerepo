package org.civex.utils.tree;

import java.util.HashMap;
import java.util.Map;

import org.civex.utils.collections.CollectionList;

public class SimpleMapNode<T> implements ITreeNode<T>
{
	final private Map<CharSequence,SimpleMapNode<T>> map=new HashMap<CharSequence,SimpleMapNode<T>>();
	final private T parent, object;
	
	public SimpleMapNode(T parent, T object )
	{
		this.parent=parent;
		this.object=object;
	}
	public SimpleMapNode<T> addChildNode(String id, SimpleMapNode<T> node)
	{
		map.put(id, node);
		return node;
	}
	
	public SimpleMapNode<T> addChildNode(String id, T child)
	{
		return addChildNode(id, new SimpleMapNode<T>(object,child));
	}
	
	public SimpleMapNode<T> addChildNode(SimpleMapNode<T> child)
	{
		return addChildNode(genNodeUniqueId(child),child);
	}
	
	public SimpleMapNode<T> addChildNode(T child)
	{
		return addChildNode(new SimpleMapNode<T>(object,child));
	}
	
	
	protected String genNodeUniqueId(SimpleMapNode<T> child)
	{
		return "n-"+object.hashCode()+"-"+child.getNodeObject().hashCode();
	}
	
	@Override
	public T getParentNode()
	{
		return parent;
	}

	public T getNodeObject()
	{
		return object;
	}
	
	@Override
	public T getNamedChildNode(CharSequence id)
	{
		return map.get(id).getNodeObject();
	}

	
	@Override
	public T getOrderedChildNode(int index)
	{
		return new CollectionList<SimpleMapNode<T>>(map.values()).get(index).getNodeObject();
	}

	@Override
	public int getChildrenNumber()
	{
		return map.size();
	}
	
	public StringBuilder dumpString(StringBuilder bld)
	{
		bld.append(object);
		bld.append("\n{");
		
		int sz=getChildrenNumber();
		for (int ii=0;ii<sz;ii++)
		{
			bld.append('\n');
			bld.append(getOrderedChildNode(ii));
		}		
		return bld.append("\n}");
		
	}
	@Override
	public String toString()
	{
		return dumpString(new StringBuilder(200)).toString();
	}
	@Override
	public boolean equals(Object obj)
	{
		if (obj==this) return true;
		if (obj==null || obj instanceof ITreeNode) return false;
		final ITreeNode<?> other=(ITreeNode<?>)obj;
		return object.equals(other.getNodeObject())  && (parent==other.getParentNode());
	}
	
	
}

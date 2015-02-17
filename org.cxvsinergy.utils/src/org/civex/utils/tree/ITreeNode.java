package org.civex.utils.tree;

public abstract interface ITreeNode<K>
{
	public K 	getParentNode();
	public K	getNodeObject();
	public K	getNamedChildNode(CharSequence id);
	public K	getOrderedChildNode(int index);
	public int	getChildrenNumber();
	
}

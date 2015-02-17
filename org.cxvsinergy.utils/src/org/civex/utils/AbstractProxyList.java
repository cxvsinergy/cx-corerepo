package org.civex.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class AbstractProxyList<E> implements List<E>
{
	final private List<E> list;

	public AbstractProxyList(List<E> list)
	{
		this.list=list;
	}
	
	public boolean isProxyObject()
	{
		return true;
	}

	protected List<E> getImpl()
	{
		return list;
	}
	
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public Iterator<E> iterator() {
		return list.iterator();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public boolean add(E e) {
		return list.add(e);
	}

	public boolean remove(Object o) {
		return list.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return list.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		return list.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public void clear() {
		list.clear();
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public E get(int index) {
		return list.get(index);
	}

	public E set(int index, E element) {
		return list.set(index, element);
	}

	public void add(int index, E element) {
		list.add(index, element);
	}

	public E remove(int index) {
		return list.remove(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
	
	public void exchangeElements(int e1, int e2)
	{
		E el1=list.get(e1);
		E el2=list.get(e2);
		list.set(e1, el2);
		list.set(e2, el1);
	}
	
	public void removeWithFilter(IObjectFilter<?,E> filter)
	{}
	
	protected boolean hasIndexInRange(int index)
	{
		return index>=0 && index<size();
	}
	
	public E getRandomElement(Random r)
	{
			final int vv=r.nextInt(size());
			return this.get(vv);
	}
	
	public E removeRandomElement(Random r)
	{
		final int vv=r.nextInt(size());
		return remove(vv);
	}
	
	
	public static class BasicListIterator<K> implements Iterator<K>
	{
		protected int ptr;
		final private List<K> list;
		
		public  BasicListIterator(List<K> l)
		{
			this.list=l;
		}
		@Override
		public boolean hasNext() 
		{
			return ptr<list.size();
		}

		@Override
		public K next() 
		{
			return list.get(ptr++);
		}

		@Override
		public void remove() 
		{
				list.remove(ptr--);
		}
		
		protected void reset()
		{
			this.ptr=0;
		}
	}
	
	
	
}

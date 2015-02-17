package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.civex.utils.AbstractProxyList;

public class SeqList
{
	public static SeqIntegerList createCardDeckList() { return new SeqIntegerList(51);}
	public static SeqIntegerList createRouletteList() { return new SeqIntegerList(36);}
	
	public interface INumericList
	{
		public double getAsDouble(int index);	
	}
	
	public static <K> K pickRandom(List<K> list)
	{
		return pickRandom(list,new Random());
	}
	
	public static <K> K pickRandom(List<K> list, Random r)
	{
		return list.get(r.nextInt(list.size()));
	}
	
	public static class SeqIntegerList extends AbstractList<Integer> implements INumericList
	{
		private int first;
		private int last;
		private int step;
		
		public SeqIntegerList(int max)
		{
			this(0,max,1);
		}
		
		public SeqIntegerList(int min,int max)
		{
			this(min,max,1);
		}
		
		public SeqIntegerList(int min, int max, int step)
		{
			this.first=min;
			this.last=max;
			this.step=step;
		}
		@Override
		public Integer get(int index) 
		{
			return Integer.valueOf(first+index*step);
		}
	
		@Override
		public int size() 
		{
			return (last-first+1)/step;
		}

		@Override
		public double getAsDouble(int index) 
		{
			return (last-first+1)/step;
		}

		@Override
		public Iterator<Integer> iterator() 
		{
			return new AbstractProxyList.BasicListIterator<Integer>(this);
		}
		
		public boolean hasUniqueElements() {return true;}
		public boolean isOrderedList() 	   {return true;}
		public boolean isHomogenousList()  {return true;}
	}
	
	
	public static class SeqDoubleList extends AbstractList<Double> implements INumericList
	{
		private double first;
		private double last;
		private double step;
		
		public SeqDoubleList(double max)
		{
			this(0,max,1);
		}
		
		public SeqDoubleList(double min,double max)
		{
			this(min,max,1);
		}
		
		public SeqDoubleList(double min, double max, double step)
		{
			this.first=min;
			this.last=max;
			this.step=step;
		}
		@Override
		public Double get(int index) 
		{
			return Double.valueOf(first+index*step);
		}
	
		@Override
		public int size() 
		{
			return (int)((last-first+1)/step);
		}

		@Override
		public double getAsDouble(int index) 
		{
			return first+index*step;
		}
	
		@Override
		public Iterator<Double> iterator() 
		{
			return new AbstractProxyList.BasicListIterator<Double>(this);
		}
		
		public boolean hasUniqueElements() {return true;}
		public boolean isOrderedList() 	   {return true;}
		public boolean isHomogenousList()  {return true;}
		
	}
}

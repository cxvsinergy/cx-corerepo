package org.civex.utils;

public class IdentifiedNumber<K> extends AbstractNumber implements IdentifiedObject<K>, Comparable<IdentifiedNumber<K>>
{
	private static final long serialVersionUID = 1L;
	final private double v;
	final private K id;
	
	public IdentifiedNumber(K id, double v)
	{
		this.id=id;
		this.v=v;
	}
	
	@Override
	public K getObjectId() 
	{
		return id;
	}

	@Override
	public long getLongHash() 
	{
		return 0;
	}

	@Override
	public long getObjectVersion() 
	{
		return 0;
	}

	@Override
	public Class<?> getObjectClassifier() 
	{
		return id.getClass();
	}

	@Override
	public boolean isDefined()  {return true;}

	@Override
	public double doubleValue() {return v;}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj==null) return false;
		if (obj==this) return true;
		if (obj instanceof IdentifiedNumber)
		{
			final IdentifiedNumber<?> other=(IdentifiedNumber<?>)obj;
			return other.id.equals(this.id);
		}
		if (!(obj instanceof Number)) return false;
		return equalsDouble((Number)obj);
	}
	
	
	
	
	@Override
	public int compareTo(IdentifiedNumber<K> o) 
	{
		int r=Integer.compare(id.hashCode(), o.id.hashCode());
		if (r!=0) return r;
		return compareDouble(o);
	}

	public IdentifiedNumberKeyValuePair<K> asKeyValuePair() 
	{
		return new IdentifiedNumberKeyValuePair<K>();
	}
	
	
	public  class IdentifiedNumberKeyValuePair<F>  extends AbstractKeyValuePair<F,Number>
	{
		public IdentifiedNumberKeyValuePair() {}
		@Override
		public Number getValue() 
		{
			return IdentifiedNumber.this;
		}

		@SuppressWarnings("unchecked")
		@Override
		public F getKey() {
			return (F)id;
		}
		
	}
}

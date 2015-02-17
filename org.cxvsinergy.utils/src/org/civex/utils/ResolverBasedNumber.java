package org.civex.utils;

public class ResolverBasedNumber<K> extends IdentifiedNumber<K>
{
	private static final long serialVersionUID = 1L;
	final private IObjectResolver<ResolverBasedNumber<K>,Number,AbstractNumber> resolver;
	private IRange<AbstractNumber> range;
	
	public ResolverBasedNumber(K id,  IObjectResolver<ResolverBasedNumber<K>,Number,AbstractNumber> resolver, double v) 
	{
		super(id, v);
		this.resolver=resolver;
	}

	@Override
	public double doubleValue() 
	{
		return asNumber() .doubleValue();
	}

	@Override
	public AbstractNumber asNumber() 
	{
		final AbstractNumber r=resolver.resolveObjectValue(this, super.doubleValue());
		if (range==null || range.betweenInclusive(r)) return r;
		return range.getRangeMean();
	}

	@Override
	public boolean immutable() { return false;}
	
	public double getBasedValue() {return super.doubleValue();}
	
	public void setRange(IRange<AbstractNumber> range)
	{
		this.range=range;
	}
	
}

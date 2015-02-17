package org.civex.utils;

public class CurrencyNumber<T> extends IdentifiedNumber<T>
{
	private static final long serialVersionUID = 1L;
	final private String ccy;
	
	public CurrencyNumber(T id, String ccy, double v)
	{
		super(id, v);
		this.ccy=ccy;
	}


	public String getCcy() { return ccy;}


	@Override
	protected StringBuilder dumpString(StringBuilder bld)
	{
		bld.append(doubleValue());
		bld.append(' ');
		bld.append(getCcy());
		return bld;
	}


	

}

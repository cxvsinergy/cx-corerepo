package org.cxvsinergy.webjdbc.web;

public class NamedParameter 
{
	final private int index;
	final private String name;
	final  private Object  defaultValue;
	
	
	public NamedParameter(int index, String name, Object defaultValue) 
	{
		super();
		this.index = index;
		this.name = name;
		this.defaultValue = defaultValue;
	}


	public String getIndexParamName()
	{
		return "param"+getParameterIndex();
	}
	
	public int getParameterIndex() {return index;}


	public String getParameterName() {return name;}


	public Object getDefaultValue() {return defaultValue;}


	@Override
	public boolean equals(Object obj) 
	{
		if (obj==this) return true;
		if (obj==null || obj.hashCode()!=this.hashCode()) return false;
		final NamedParameter other=(NamedParameter) obj; 
		return getParameterName().equals(other.getParameterName());
	}


	@Override
	public int hashCode() 
	{
		return name.hashCode()+37;
	}

	@Override
	public String toString() 
	{
		StringBuilder bld=new StringBuilder(100);
		return bld.append(getParameterName()).toString();
	}
	
	
	
}

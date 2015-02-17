package org.civex.utils;

import org.civex.utils.numeric.INumberChangedListener;
import org.civex.utils.numeric.NumberChangedListenerAdapter;

public abstract class AbstractMutableNumber extends AbstractNumber 
{

	private static final long serialVersionUID = 1L;
	protected INumberChangedListener listener=NumberChangedListenerAdapter.STUB;
	
	public abstract void set(double v);
	public  void add(double v)  {set(doubleValue()+v);}
	public  void sub(double v)  {set(doubleValue()-v);}
	public  void mul(double v)  {set(doubleValue()*v);}
	public  void div(double v)  {set(doubleValue()/v);}
	
	
	
	protected void numberChanged(double newNumber)
	{
		listener.onNumberChanged(this,newNumber);
	}
	
	
	public void addNumberChangedListener(INumberChangedListener listener)
	{
		this.listener=listener;
	}
	
	public void removeNumberChangedListener(INumberChangedListener listener)
	{
		
	}
	
	
	public void mul(Number v) {mul(v.doubleValue());}
	public void add(Number v) {add(v.doubleValue());}
	public void div(Number v) {div(v.doubleValue());}
	public void sub(Number v) {sub(v.doubleValue());}

	
	public double divScalar(double v)
	{
		return doubleValue()/v;
	}
}

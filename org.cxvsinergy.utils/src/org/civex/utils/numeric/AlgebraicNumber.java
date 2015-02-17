package org.civex.utils.numeric;

import java.util.ArrayList;
import java.util.List;

import org.civex.utils.AbstractMutableNumber;
import org.civex.utils.KeyNumericPair;

public class AlgebraicNumber extends AbstractMutableNumber
{
	final private List<KeyNumericPair<Number>> numbers=new ArrayList<KeyNumericPair<Number>>(10);
	final private int F_SUB=0x101;
	final private int F_ADD=0x102;
	final private int F_MUL=0x103;
	final private int F_DIV=0x104;
	final private int F_EXP=0x201;
	final private int F_LN=0x202;
	final private int F_LG=0x203;
	final private int F_POWER=0x301;
	final private int F_INVERSE=0x302;
	final private int F_SQRSUM=0x302;

	final private int F_BRACKET_START=0x105;
	final private int F_BRACKET_END=0x106;

	

	final private SimpleNumber lastResult=new SimpleNumber();

	@Override
	public void add(double v) 
	{
		numbers.add(new KeyNumericPair(v,F_ADD));
	}

	@Override
	public void sub(double v) 
	{
		numbers.add(new KeyNumericPair(v,F_SUB));
	}

	@Override
	public void div(double v) {
		numbers.add(new KeyNumericPair(v,F_DIV));
	}

	@Override
	public void mul(double v) {
		numbers.add(new KeyNumericPair(v,F_MUL));
	}
	
	public void mul(Number v) {
		numbers.add(new KeyNumericPair(v,F_MUL));
	}

	public void add(Number v) 
	{
		numbers.add(new KeyNumericPair(v,F_ADD));
	}

	
	public void div(Number v) {
		numbers.add(new KeyNumericPair(v,F_DIV));
	}

	@Override
	public void sub(Number v) {
		numbers.add(new KeyNumericPair(v,F_MUL));
	}
	
	@Override
	public void set(double v) 
	{
	}

	@Override
	public double doubleValue() 
	{
		lastResult.set(0);
		compute(0,lastResult);
		return lastResult.doubleValue();
	}


	protected int compute(final int index, final AbstractMutableNumber rr)
	{
		double result=0;
		int ii=0;
		for (ii=index;ii<numbers.size();ii++)
		{
			final KeyNumericPair<Number> pair=numbers.get(ii);
			final int op=pair.getValueAsInt();
			switch(op)
			{
			case F_SUB: result-=pair.getKey().doubleValue(); continue;
			case F_ADD: result+=pair.getKey().doubleValue(); continue;
			case F_MUL: result*=pair.getKey().doubleValue(); continue;
			case F_DIV: result/=pair.getKey().doubleValue(); continue;
			//case F_BRACKET_START:  ii=compute(ii+1,rr);
			//case F_BRACKET_END:    rr.set(result); return ii+1; 
			}
		}
		rr.set(result);
		return ii;
	}
	
	
	public void startBrackets()
	{
		
	}
	
	
	public void endBrackets()
	{
		
	}
	
	
	public void dumpParameters(final List<Number> coll)
	{
		for (int ii=0;ii<numbers.size();ii++)
		{
			coll.add(numbers.get(ii).getKey());
		}
	}
	
	
	public void resetFormula()
	{
		numbers.clear();
		lastResult.set(0);
	}
	
	
	public String printFormula()
	{
		final StringBuilder sb=new StringBuilder(100);
		for (int ii=0;ii<numbers.size();ii++)
		{
			final KeyNumericPair<Number> pair=numbers.get(ii);
			final int op=pair.getValueAsInt();
			switch(op)
			{
			case F_SUB: sb.append('-').append(pair.getKey().doubleValue());continue;
			case F_ADD: sb.append('+').append(pair.getKey().doubleValue());continue;
			case F_MUL: sb.append('*').append(pair.getKey().doubleValue());continue;
			case F_DIV: sb.append('/').append(pair.getKey().doubleValue()); continue;
			//case F_BRACKET_START:  ii=compute(ii+1,rr);
			//case F_BRACKET_END:    rr.set(result); return ii+1; 
			}
		}
		return sb.toString();
	};
}

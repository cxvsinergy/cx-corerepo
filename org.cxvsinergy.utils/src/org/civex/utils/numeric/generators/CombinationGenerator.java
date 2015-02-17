package org.civex.utils.numeric.generators;

import java.util.List;

public class CombinationGenerator extends AbstractSeqGenerator<int[]> 
{
	final protected int k;
	final protected int n;
	final private int seq[];
	final protected long total;
	protected  long combLeft;
	
	public CombinationGenerator(int n, int k)
	{
		this.k=k;
		this.n=n;
		total=calculateTotal(n,k);
		seq=new int[k];
		reset();
	}
	
	public int getNumberOfElements()
	{
		return n;
	}
	
	public int getNumberOfCombinations()
	{
		return k;
	}
	
	protected long calculateTotal(int n, int k)	{
		final long kf=calculateFactorial(k);
		final long nminuskf=calculateDeltaFactorial(n, (n-k));
		return nminuskf/kf;
	}
	
	public static long calculateFactorial(final int n)
	{
		long r=1;
		for (int ii=2;ii<=n; ii++) r*=ii;
		return r;
	}
	
	public static long calculateDeltaFactorial(final int n, final int k)
	{
		if (n<k) throw new RuntimeException("n should be greater then k");
		long r=1;
		for (int ii=k+1;ii<=n;ii++)
		{
			r*=ii;
			// we can use some optimization for large n and k;
			if (r<=0) throw new RuntimeException("Stack overflow, too big number");
		}
		return r;
	}

protected int[] generateNext()
{
	if (combLeft==total)
	{
		combLeft--;
		return seq;
	}
	int i=k-1;
	while(seq[i]==n-k+i) i--;
	seq[i]+=1;
	for (int j=i+1;j<k;j++) seq[j]=seq[i]+j-i;
	combLeft--;
	return seq;
}

public long getTotal()
{
	return total;
}


public void reset()
{
	for (int ii=0;ii<seq.length;ii++) seq[ii]=ii;
	combLeft=total;
}


public boolean hasNext()
{
	return combLeft>0;
}

/**that is not optimal**/
public static void getElements(int[] seq, final List<?> set, final List<?> values)
{
	values.clear();
	for (int ii=0;ii<seq.length;ii++)
	{
		((List<Object>)values).add(set.get(seq[ii]));
	}
	return;
}

public void debug(int[] v)
{
  for (int ii=0;ii<v.length;ii++) System.out.print(v[ii]+",");
  System.out.println();
}

}
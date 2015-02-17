package org.civex.utils.numeric.generators;

public class PermutationGenerator extends CombinationGenerator 
{
	final private long permutNumber;
	final private int seq[];
	final private int seq2[];
	private long permutLeft;
	final private int n_k;
	final private int k1;
	final private int k2;
	
	
	public PermutationGenerator(int n, int k) {
		super(n, k);
		permutNumber=calculateFactorial(k);
		seq=new int[k];
		seq2=new int [k];
		k1=k-1;
		k2=k-2;
		n_k=n-k;
		reset();
	}
	
	public void reset()
	{
		combLeft=total;
		permutLeft=-1;
	}
	
	public long getTotal()
	{
		return total*permutNumber;
	}
	
	public boolean hasNext()
	{
		return combLeft>0 || permutLeft>0;
	}
	
	
	protected int[] generateNext()
	{
		if (permutLeft>0)
		{
			int j=k2;
			while(seq2[j]>seq2[j+1]) j--;
			// find index k such that a[k] is smallest integer
			// greater than a[j] to the right of a[j]
			int k=k1;
			while(seq2[j]>seq2[k]) k--;
			// interchange a[j] and a[k]
			int temp=seq2[k];
			seq2[k]=seq2[j];
			seq2[j]=temp;
			//
			// put the tail of permutation after jth position in increasing order
			
			int r=k1;
			int s=j+1;
			//
			while(r>s)
			{
				temp=seq2[s];
				seq2[s++]=seq2[r];
				seq2[r--]=temp;
			}
			permutLeft--;
			return seq2;
		}
		// generate initial combination
		int i=k1;
		while(seq[i]==n_k+i) i--;
		seq[i]++;
		final int pri=seq[i]-i;
		for(int j=i+1;j<k;j++) seq[j]=pri+j;
		//copies initial combination to the permutation set
		System.arraycopy(seq, 0,seq2,0,k);
		permutLeft=permutNumber-1;
		combLeft--;
		return seq2;
	}

}

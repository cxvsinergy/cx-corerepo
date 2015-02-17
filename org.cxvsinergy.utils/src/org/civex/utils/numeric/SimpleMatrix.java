package org.civex.utils.numeric;



public class SimpleMatrix extends AbstractMatrix
{
	final private double buff[];
	
	public SimpleMatrix(int rows, int cols)
	{
		super(rows,cols);
		buff=new double[rows*cols];
	}

	@Override
	public double getValue(int i, int j)
	{
		return buff[j*getCols()+i];
	}

	@Override
	public void setValue(int i, int j, double v)
	{
		buff[j*getCols()+i]=v;
	}
	
	
	
	public  static class SingleNumberMatrix extends AbstractMatrix
	{
		final private double value;
		
		public SingleNumberMatrix(int rows, int cols,  double v)
		{
			super(rows,cols);
			this.value=v;
		}

		@Override
		public double getValue(int i, int j)
		{
			return value;
		}

		@Override
		public void setValue(int i, int j, double v)
		{
			throw new java.lang.ArithmeticException("Single Number matrix can not be changed");
			
		}
	}
	
	public  static class ScalarMatrix extends AbstractMatrix
	{
		final private double value;
		
		public ScalarMatrix(int sz, double v)
		{
			super(sz,sz);
			this.value=v;
		}

		@Override
		public double getValue(int i, int j)
		{
			return i==j?value:0;
		}

		@Override
		public void setValue(int i, int j, double v)
		{
			throw new java.lang.ArithmeticException("Identity matrix, can not be changed");
		}
		
		public boolean isScalar() {return true;}
	}
	
	public  static class IdentityMatrix extends ScalarMatrix
	{
		public IdentityMatrix(int sz)
		{
			super(sz,1D);
		}		
	}
	
	public  static class DiagMatrix extends AbstractMatrix
	{
		final private double buff[]; 
		
		public DiagMatrix(double[] els)
		{
			this(els.length);
			for (int ii=0;ii<els.length;ii++) setValue(ii,ii,els[ii]);
		}
		
		public DiagMatrix(int sz)
		{
			super(sz,sz);
			buff=new double[sz];
		}
		
		@Override
		public double getValue(int i, int j)
		{
			return i==j?buff[i]:0;
		}

		@Override
		public void setValue(int i, int j, double v)
		{
			if (i!=j) throw new java.lang.ArithmeticException("Diag matrix, can not be changed");
			buff[i]=v;			
		}
		
	}
	
	
	public  static class TriangularMatrix extends AbstractMatrix
	{
		final private double buff[]; 
		final private boolean upper;
		
		public TriangularMatrix(double vals[])
		{
			this(vals,true);
		}
		
		public TriangularMatrix(double vals[], boolean upper)
		{
			this(calcSqEq(vals.length),upper);
			System.arraycopy(vals, 0, buff, 0, vals.length);
		}
		
		private static int calcSqEq(int l)
		{
			double d=Math.sqrt(1+4*l)/2;
			double r1=Math.abs(-0.5+d);
			double r2=Math.abs(-0.5-d);
			return (int)Math.max(r1, r2);
		}
		
		public TriangularMatrix(int sz, boolean upper)
		{
			super(sz,sz);
			buff=new double[(sz*sz-sz)/2+sz];
			this.upper=upper;
		}
		
		@Override
		public double getValue(int i, int j)
		{
			if (upper) return i>=j?buff[calcArrayIndex(i,j)]:0;
			return i<=j?buff[0]:0;
		}

		@Override
		public void setValue(int i, int j, double v)
		{
			//if (i!=j) throw new java.lang.ArithmeticException("Triag matrix, can not be changed");
			buff[calcArrayIndex(i,j)]=v;			
		}

		
		protected int calcArrayIndex(int i, int j)
		{
			final int cols=getCols();
			int r=j*(cols-1)+i;
			for (int ii=1;ii<j;ii++) r-=ii;
			return r;
		}
	}
}

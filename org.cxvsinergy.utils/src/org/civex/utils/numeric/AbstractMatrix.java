package org.civex.utils.numeric;

public abstract class AbstractMatrix implements INumericMatrix
{
	final private int rows;
	final private int cols;
	
	public AbstractMatrix(int rows, int cols)
	{
		this.rows=rows;
		this.cols=cols;
	}
	@Override
	public abstract double getValue(int i, int j);
	

	@Override
	public abstract void setValue(int i, int j, double v);

	
	public void addValue(int i, int j, double v)
	{
		final double vv=getValue(i, j);
		setValue(i, j, vv+v);
	}

	public void mulValue(int i, int j, double v)
	{
		final double vv=getValue(i, j);
		setValue(i, j, vv*v);
	}
	
	@Override
	public int getCols()
	{
		return cols;
	}

	@Override
	public int getRows()
	{
		return rows;
	}

	@Override
	public int getDet()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(double v)
	{
		for (int ii=0;ii<getCols();ii++)
			for (int jj=0;jj<getRows();jj++) addValue(ii, jj, v);		
	}

	@Override
	public void mul(double v)
	{
		for (int ii=0;ii<getCols();ii++)
			for (int jj=0;jj<getRows();jj++) mulValue(ii, jj, v);
	}

	@Override
	public void fill(double v)
	{
		for (int ii=0;ii<getCols();ii++)
			for (int jj=0;jj<getRows();jj++) setValue(ii, jj, v);	
		
	}

	@Override
	public void fillRow(int row, double v)
	{
		for (int ii=0;ii<getCols();ii++) setValue(ii, row, v);	
		
	}

	@Override
	public void fillCol(int col, double v)
	{
		for (int jj=0;jj<getRows();jj++) setValue(col, jj, v);	
		
	}

	@Override
	public INumericMatrix dotmul(INumericMatrix matrix)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INumericMatrix transposed()
	{
		return new TransposedMatrix(getCols(), getRows());
	}

	@Override
	public INumericMatrix submatrix(int r, int c, int rl, int cl)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INumericMatrix mul(INumericMatrix matrix)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INumericMatrix add(INumericMatrix matrix)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INumericList asColList(int c)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INumericList asRowList(int r)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	protected int getLinearSize()
	{
		return getCols()*getRows();
	}
	
	
	
	
	@Override
	public String toString()
	{
		final StringBuilder bld=new StringBuilder(getLinearSize()*10);
		for (int jj=0;jj<getRows();jj++)
		{
			for (int ii=0;ii<getCols();ii++) 
			{
				bld.append(getValue(ii, jj)).append(' '); 
			}
			bld.append('\n');
		}
		return bld.toString();
	}



	public  class TransposedMatrix extends AbstractMatrix
	{

		public TransposedMatrix(int rows, int cols)
		{
			super(rows, cols);
		}

		@Override
		public double getValue(int i, int j)
		{
			return AbstractMatrix.this.getValue(j, i);
		}

		@Override
		public void setValue(int i, int j, double v)
		{
			AbstractMatrix.this.setValue(j, i,v);
			
		}
		
		
	}
}

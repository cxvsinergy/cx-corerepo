package org.civex.utils.numeric;


public interface INumericMatrix 
{
	public double getValue(int i, int j);
	public void setValue(int i, int j, double v);
	public int getCols();
	public int getRows();
	public int getDet();
	public void add(double v);
	public void mul(double v);
	public void fill(double v);
	public void fillRow(int index,double v);
	public void fillCol(int index,double v);
	public INumericMatrix dotmul(INumericMatrix matrix);
	//
	public INumericMatrix transposed();
	public INumericMatrix submatrix(int r, int c, int rl, int cl);
	public INumericMatrix mul(INumericMatrix matrix);
	public INumericMatrix add(INumericMatrix matrix);
	public INumericList asColList(int c);
	public INumericList asRowList(int r);
	
}

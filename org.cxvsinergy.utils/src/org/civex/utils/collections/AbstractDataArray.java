package org.civex.utils.collections;

import java.util.Arrays;

public abstract class AbstractDataArray<T> implements IDataArray<T> 
{
   final protected int sizes[];
   final protected T arr[];
   
   public AbstractDataArray(int sz)
   {
	   this(new int[]{sz});
   }

   public AbstractDataArray(int dimension, int sz)
   {
	   this(buildDimArray(dimension,sz));
   }
   
   public AbstractDataArray(int dims[])
   {
	 sizes=new int[dims.length];
	 System.arraycopy(dims, 0, this.sizes, 0, dims.length);
	 int size=calcAbsSize(sizes);
	 arr=create(size);
   }
   
   protected static int calcAbsSize(final int arr[])
   {
	   int r=1;
	   for (int ii=0;ii<arr.length;ii++) r*=arr[ii];
	   return r;
   }
   
   protected static int[] buildDimArray(int dim, int sz)
   {
	   final int r[]=new  int[dim];
	   Arrays.fill(r, sz);
	   return r;
   }
   
   protected abstract T[] create(int size);
   
   public int dimensions()
   {
	   return sizes.length;
   }
   
   public int size(final int dim) { return sizes[dim];}
   public int size()	{return arr.length;}
   public T getAbsoluteElement(int index)
   {
	   return arr[index];
   }
   
   public void setAbsoluteElement(int index, T v)
   {
	   arr[index]=v;
   }
   
   public final T getElement(int... pos)
   {
	   return getAbsoluteElement(this.getAbsoluteIndex(pos));
   }
   
   public void setElement(T v, int... pos)
   {
	   setAbsoluteElement(getAbsoluteIndex(pos),v);
   }
   
   public int getAbsoluteIndex(int... pos)
   {
	   int plane=1,result=0;
	   for (int ii=1;ii<pos.length;ii++) result+=(plane*=size(ii-1))*pos[ii];
	   return result+pos[0];
   }
   
   public IDataArray<T> getSubArray(int dim)
   {
	   return new SubArray<T>(this,dim);
   }
   
   public void setAll(final T v)
   {
	   Arrays.fill(arr, v);
   }
   
   public boolean requestDimensionIndex(final int index, final int[] dim)
   {
	   if (dim.length!=dimensions()) throw new RuntimeException("non-equal dimension");
	   dim[0]=1;
	   for(int ii=1;ii<dim.length;ii++) dim[ii]=dim[ii-1]*size(ii-1);
	   int result=index;
	   for(int ii=dim.length-1;ii>=0; ii--)
	   {
		    final int v=result/dim[ii];
		    result=result%dim[ii];
		    dim[ii]=v;
	   }
	   return true;
   }
   
   class SubArray<S> implements IDataArray<T>
   {
	   final int excludedIndex;
	   final IDataArray<T> parent;
 
	   public  SubArray(IDataArray<T> parent, int excluded)
	   {
		   this.parent=parent;
		   this.excludedIndex=excluded;
	   }
	   
	   public final int dimensions()
	   {
		   return parent.dimensions()-1;
	   }
	   
	   public final int size(int dimension)
	   {
		   return parent.size(dimension<excludedIndex?dimension:dimension+1);
	   }
	   
	   public int size()
	   {
		   return parent.size()/parent.size(excludedIndex);
	   }
	   
	   public T getAbsoluteElement(int index)
	   {
		   throw new RuntimeException("Ubiquous elements");
	   }
	   
	   public void setAbsoluteElement(final int index, final T v)
	   {
		 final int c[]	=new int[dimensions()];
		 final int cv[]	=new int[parent.dimensions()];
		 requestDimensionalIndex(index,c);
		 for(int ii=0;ii<c.length;ii++) cv[ii<excludedIndex?ii:ii+1]=c[ii];
		 for (int ii=0;ii<parent.size(excludedIndex);ii++)
		 {
			 cv[excludedIndex]=ii;
			 parent.setElement(v,cv);
		 }
	   }
	   
	
	   public int getAbsoluteIndex(int... pos)
	   {
		   int plane=1, result=0;
		   for (int ii=1;ii<pos.length;ii++) result+=(plane*=size(ii-1))*pos[ii];
		   return result+pos[0];
	   }
	   
	   public T getElement(int... pos)
	   {
		 return getAbsoluteElement(getAbsoluteIndex(pos));   
	   }
	   
	   public void setElement(T v, int...pos)
	   {
		   setAbsoluteElement(getAbsoluteIndex(pos),v);
	   }
	   
	   
	   public IDataArray<T> getSubArray(int dim)
	   {
		    return new SubArray<T>(this,dim);
	   }
	   
	   public boolean requestDimensionalIndex(int index, int[] dim)
	   {
		if (dim.length!=dimensions()) throw new RuntimeException("non-equal dimension");
		dim[0]=1;
		for (int ii=1;ii<dim.length;ii++) dim[ii]=dim[ii-1]*size(ii-1);
		int result=index;
		for (int ii=dim.length-1;ii>=0;ii--)
		{
			final int v=result/dim[ii];
			result=result%dim[ii];
			dim[ii]=v;
		}
		return true;
	   }
   }
   
   
}

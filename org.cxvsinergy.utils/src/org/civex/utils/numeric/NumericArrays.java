package org.civex.utils.numeric;

public class NumericArrays 
{
   public static int[] remove(int[] arr, int index)
   {
	   final int[] arr2=new int[arr.length-1];
	   for (int ii=0;ii<arr.length;ii++)
	   {
		   if (ii==index) continue;
		   if (ii<index) arr2[ii]=arr[ii]; else arr2[ii-1]=arr[ii];
	   }
	   return arr;
   }
}

package org.civex.demo;

public class PokerPlayer 
{
	  public int k1=-1;
	  public int k2=-1;
	  
	  public boolean hasPair()
	  {
		  return k1%13==k2%13;
	  }
	  
	  
	  public boolean hasPair(int... vv)
	  {
		  final int counter[]={0,0,0,0,0,0,0,0,0,0,0,0,0};
		  counter[k1%13]++;
		  counter[k2%13]++;
		  for (final int v:vv) 
			  {
			  		counter[v%13]++;
			  		if (counter[v%13]==2) return true;
			  }
		  return false;
	  }
	  
	  public boolean hasTwoPairs(int... vv)
	  {
		  final int counter[]={0,0,0,0,0,0,0,0,0,0,0,0,0};
		  counter[k1%13]++;
		  counter[k2%13]++;
		  int pairs=0;
		  for (final int v:vv) 
			  {
			  		counter[v%13]++;
			  		if (counter[v%13]==2) pairs++;
			  		if (pairs==2) return true;
			  }
		  return false;
	  }
	  
	  	  
	  public boolean hasTripplet(int... vv)
	  {
		  final int counter[]={0,0,0,0,0,0,0,0,0,0,0,0,0};
		  counter[k1%13]++;
		  counter[k2%13]++;
		  for (final int v:vv) 
			  {
			  		counter[v%13]++;
			  		if (counter[v%13]==3) return true;
			  }
		  return false;
	  }
	  
	  public boolean hasFourOfTheKind(int... vv)
	  {
		  final int counter[]={0,0,0,0,0,0,0,0,0,0,0,0,0};
		  counter[k1%13]++;
		  counter[k2%13]++;
		  for (final int v:vv) 
			  {
			  		counter[v%13]++;
			  		if (counter[v%13]==4) return true;
			  }
		  return false;
	  }
	  
	  public int hasFlash(int... v)
	  {
		  final int counter[]={0,0,0,0};
		  counter[k1/13]++;
		  counter[k2/13]++;
		  for (int vv:v) counter[vv/13]++;
		  for (int ii=0;ii<4;ii++) if (counter[ii]==5) return ii;
		  return -1;
	  }
	  
	  public boolean hasStreet(int...v)
	  {
		  return false;
	  }
	
}

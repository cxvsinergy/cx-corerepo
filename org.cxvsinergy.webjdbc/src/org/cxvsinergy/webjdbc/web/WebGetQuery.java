package org.cxvsinergy.webjdbc.web;


//?table=abc&op=update&
//?sql=abc&rsout
//?sql=abc&stmt=ert&<-set a prepared statement& 
public class WebGetQuery 
{
	final protected String query;
	
	public WebGetQuery(String query)
	{
		this.query=query;
	}
	
	public int getQueryParamCount() {return 0;}
	
	public NamedParameter getParameter(int index) { return null;}
	
	
}

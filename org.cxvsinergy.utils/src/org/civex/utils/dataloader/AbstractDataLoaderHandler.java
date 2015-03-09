package org.civex.utils.dataloader;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;



/**
 * 
 * field completed indicated that the all the data has been loaded
 * @param <K>
 */
public abstract class AbstractDataLoaderHandler<K> implements IDataLoaderHandler 
{
	final static protected Logger log=Logger.getLogger(AbstractDataLoaderHandler.class.getName());
	/**indicated that all the data has been proceeded with this handler**/
	protected volatile int completed;
	
	public boolean onSetColumnValue(CharSequence s, int line, int columnIndex, int start,int end) throws ParseException 
	{
		return onSetFieldValue(line,resolveFieldDescriptor(columnIndex),columnIndex,s,start,end);
	}


	protected boolean onSetFieldValue(int line,K column, int columnIndex,CharSequence s,int start,int end) throws ParseException 
	{
		return true;
	}
	
	@Override
	public void onDeclareField(int index, CharSequence name, int start, int end) 
	{
		resolveFieldDescriptor(index, name, start, end);
	}
	
	protected K resolveFieldDescriptor(int index)
	{
		return null;
	}
	
	protected K resolveFieldDescriptor(int index, CharSequence name, int start, int end)
	{
		return null;
	}

	@Override
	public int onNextRecord(int line) { return 1;}

	@Override
	public void onStartRead() {	}

	@Override
	public void onEndRead(int lines) 
	{
		completed=1;
	}

	public void reset()
	{
		completed=0;
	}
	
	public boolean isCOmpleted()
	{
		return completed==1;
	}
	
	protected static final int findChar(CharSequence s, int start, int end, char ch)
	{
		// TODO not optimal, should be do it only to the end
		final int pos=s.toString().indexOf(ch, start);
		return  pos>=end?-1:pos;
	}
	
	protected String resolveString(int colIndex, CharSequence s, int start, int end)
	{
		return s.subSequence(start, end).toString();
	}
	
	protected double resolveDouble(int colIndex, CharSequence s, int start, int end) 
	{
		return Double.parseDouble(resolveString(colIndex,s,start,end));
	}
	

	protected long resolveLong(int colIndex, CharSequence s,int start, int end) 
	{
		return Long.parseLong(resolveString(colIndex,s,start,end));
	}


	protected long resolveInt(int colIndex,CharSequence s, int start, int end) 
	{
		return Integer.parseInt(resolveString(colIndex,s,start,end));
	}

	protected Date resolveDateString(int colIndex,CharSequence s, int start, int end) 
	{
		return new Date();
	}
	
	protected long resolveTimeString(int colIndex,CharSequence s,int start, int end) 
	{
		return Long.parseLong(resolveString(colIndex,s,start,end));
	}

	public <N> List<N> getCollectedRecords()
	{
		return Collections.emptyList();
	}
	
	public <N> N getLastRecord()
	{
		final List<N> t=getCollectedRecords();
		return !t.isEmpty()?t.get(t.size()-1):null; 
				
	}
}

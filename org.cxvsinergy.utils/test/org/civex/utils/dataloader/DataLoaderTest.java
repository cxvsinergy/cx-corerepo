package org.civex.utils.dataloader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import junit.framework.TestCase;

import org.civex.utils.collections.AutoGeneratedList;
import org.civex.utils.datetime.LocalDateTime;
import org.civex.utils.finmath.SimpleMarketPrice;

public class DataLoaderTest extends TestCase
{
		public void testDataLoaderBasic() throws IOException
		{
			CSVDataLoader loader=new CSVDataLoader(); 
			YahooDataHandler h=new YahooDataHandler();
			loader.readResource("yahoo.test", DataLoaderTest.class, h);
		}
}




class YahooDataHandler extends AbstractDataLoaderHandler
{
	final private List<SimpleMarketPrice> coll=new AutoGeneratedList<SimpleMarketPrice>(new SimpleMarketPrice());
	@Override
	public int onNextRecord(int line)
	{
		if(!coll.isEmpty()) System.out.println(coll.get(coll.size()-1));
		coll.get(coll.size());	
			
		return line;
	}
	@Override
	public boolean onSetColumnValue(CharSequence s, int line, int columnIndex, int start, int end)
			throws ParseException
	{
		final int index=coll.size()-1;
		final SimpleMarketPrice px=coll.get(index);
		System.out.println("AAA "+ start+"  "+end+"   "+columnIndex);
		switch (columnIndex)
		{
		case 0:
			px.setTimestamp(LocalDateTime.TIME_NYC.parseDateOnly(this.resolveString(columnIndex, s, start, end)));		
		case 1:
			px.setPxOpen(resolveDouble(columnIndex, s, start, end)); break;
		case 2:
			px.setPxHigh(resolveDouble(columnIndex, s, start, end)); break;
		case 3:
			px.setPxLow(resolveDouble(columnIndex, s, start, end)); break;
		case 4:
			px.setPxClose(resolveDouble(columnIndex, s, start, end)); break;
		case 5:
			px.setVolume(resolveLong(columnIndex, s, start, end)); break;
		case 6: // adjustable close
			//px.setVolume(resolveLong(columnIndex, s, start, end));
				
		}

		return true; 
	}
	
	
	
}
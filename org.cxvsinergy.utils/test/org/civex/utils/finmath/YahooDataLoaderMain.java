package org.civex.utils.finmath;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.civex.utils.app.AppUtilsHelper;
import org.civex.utils.app.ApplicationMain;

public class YahooDataLoaderMain extends ApplicationMain
{

	public static void main(String[] args)
	{
		runMain(args, YahooDataLoaderMain.class);
	}

	@Override
	public void runInstance(String[] args) throws IOException
	{
		final String url = "http://real-chart.finance.yahoo.com/table.csv?";
		final String path = resolveParam(args, "path", ".");
		final File fpath = new File(path);
		final List<String> symbols=AppUtilsHelper.readCollectionFromParam(Arrays.asList(args),"symbols",",","SPY");
		for (String symbol:symbols)
		{
			if (symbol.trim().length()==0) continue;
			final String query = "s=" + symbol + "&d=2&e=8&f=2017&g=d&a=10&b=26&c=2010&ignore=.csv";
			File tfile = new File(fpath, "yahoo-" + symbol + ".dat");
			printMessage(0, "Loading data for symbol "+symbol+" ...", (Object[])null);
			try
			{
				AppUtilsHelper.copyFromURL(url+query,tfile);				
			}
			catch(IOException x)
			{
				printMessage(3, x.getMessage(), x);
			}
			
			

		}
			System.exit(0);
	}
	
	
	
	
	

	@Override
	protected boolean validateParameters(String[] args)
	{
		return true;
	}

	
	
	
}

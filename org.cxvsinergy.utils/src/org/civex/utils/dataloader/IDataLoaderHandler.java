package org.civex.utils.dataloader;

import java.text.ParseException;

/**
 *  A callback interface defined for notifier supplier when a new chunk data is loaded
 * @author nikehome
 *
 */
public interface IDataLoaderHandler 
{
	//public void onSetRecordBlobValue(final CharSequence s, int line, int col, int start, int end) throws ParseException;
	//public void onSetRecordLinkValue(final CharSequence s, int line, int col, int start, int end) throws ParseException;
	public boolean onSetColumnValue(final CharSequence s, int line, int col, int start, int end) throws ParseException;
	public void onDeclareField(int index, final CharSequence name, int start, int end);
	public int  onNextRecord(int line);
	public void onStartRead();
	public void onEndRead(int lines);
}

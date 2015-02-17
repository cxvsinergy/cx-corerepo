package org.civex.messaging.proto;

public interface HostCellController 
{
	public void updatePerformance(int v);
	public void sleepUntil(long time);
	public void muteChannel(int index);
	public void setVolume(int channel, int level);
	public void switchMonitor(int index, boolean state);
	public void openResource(int winID,String url);
	//
	public void playMedia(String url, int winID);
	public void pause(int winID);
	public void resume(int winID);
	public void moveMedia(int winID, int p1, int p2);
	public void setSubtitles(String url,int winID,boolean state);
	public void sayPhrase(int channel,String profileID, String s, int speed);
	public void setAlarm(int link, int time, int off, int channel,int volume, String url);
	public void resetAlarm(int link);
	
	
}

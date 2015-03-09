package org.civex.utils.resources;

/**This interface describes interaction between UI and loader functionality itself**/
public interface IDataLoaderHandler 
{
	 /**notifies that loader fetched another chunk of data from the resource**/
     public void onBlockReadingCompleted(ResourceDescriptor resourceDesc, long sz);
     public void onDownloadStart(ResourceDescriptor resourceDesc);
     public void onDownloadEnd(ResourceDescriptor resourceDesc);
     public void addLoadingResource(ResourceDescriptor resourceDesc);
     public void removeLoadedResource(ResourceDescriptor resourceDesc);
     public void setWorkingPath(String workingPath);
}

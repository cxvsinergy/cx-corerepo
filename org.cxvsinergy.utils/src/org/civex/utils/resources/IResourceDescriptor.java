package org.civex.utils.resources;

import java.util.Map;

/**********************************************************************
 * Copyright (c) 2014 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
public interface IResourceDescriptor
{
	public IResourceDescriptor getLocalResource();
	public String getResourceId();
	public String getResourceUri();
	public long getResourceSize();
	public long getResourceTimestamp();
	public String getResourceVersion();
	public Map<String, ?> getResourceAttributes();
}

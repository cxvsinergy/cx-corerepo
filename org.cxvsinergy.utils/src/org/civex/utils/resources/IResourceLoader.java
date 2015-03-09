package org.civex.utils.resources;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public interface IResourceLoader
{
	
	public void   loadContent(IResourceDescriptor desc, OutputStream stream) throws IOException;
	public void   loadContent(IResourceDescriptor desc, Writer wr) throws IOException;
	public void   writeContent(IResourceDescriptor desc, Object rdr) throws IOException;
	public void   writeContent(IResourceDescriptor desc, Reader rdr) throws IOException;
	public boolean   requestResourceList(IResourceDescriptor desc, List<IResourceDescriptor> coll);
	public boolean   requestEntriesList(IResourceDescriptor desc, List<IResourceDescriptor> coll);
	/**gets resource attributes**/
	public Map<String,?> getResourceAttributes(IResourceDescriptor desc);
	/**gets a local resource, if loader can find one**/
	public IResourceDescriptor requestLocalResource(IResourceDescriptor desc);
	public boolean isReadableResource(IResourceDescriptor desc);
	public boolean isWriteableResource(IResourceDescriptor desc);
}

package org.civex.utils;

import java.io.Serializable;

public abstract class IdentifiedObjectWrapper<T,K> implements Serializable, IdentifiedObject<T> 
{
	private static final long serialVersionUID = 1L;
	protected final T objid;
	
	public IdentifiedObjectWrapper( T objid)
	{
		this.objid=objid;
	}
	
	
	public abstract K getUnderlinedObject();
	/* (non-Javadoc)
	 * @see org.civex.utils.IdentifiedObject#getObjectId()
	 */
	@Override
	public T getObjectId()
	{
		return objid;
	}
	
	/* (non-Javadoc)
	 * @see org.civex.utils.IdentifiedObject#getObjectClassifier()
	 */
	@Override
	public Class<?> getObjectClassifier()
	{
		return objid.getClass();
	}

	@Override
	public int hashCode() 
	{
		return  objid.hashCode()+47;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (hashCode()!=obj.hashCode()) return false;
		if (!(obj instanceof IdentifiedObjectWrapper)) return false;
		IdentifiedObjectWrapper<?,?> other=(IdentifiedObjectWrapper<?,?>)obj;
		return objid.equals(other.objid);
	}

	@Override
	public String toString() 
	{
		return objid.toString();
	}
	
	protected long getObjCreatedTimestamp()
	{
		return 0;
	}
	
	protected long getObjUpdatedTimestamp()
	{
		return 0;
	}
	
	protected Object getObjectOwner()
	{
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.civex.utils.IdentifiedObject#isDefined()
	 */
	@Override
	public boolean isDefined()
	{
		return this.getObjectId()!=null;
	}
}

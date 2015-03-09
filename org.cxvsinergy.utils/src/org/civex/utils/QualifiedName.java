package org.civex.utils;

import java.io.Serializable;
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
public class QualifiedName implements Comparable<QualifiedName>, Serializable
{
	protected static final CharSequence EMPTYNS="";
	private static final long serialVersionUID = 1L;
	final private CharSequence namespace;
	final private CharSequence name;
	
	public QualifiedName(CharSequence name)
	{
		this(EMPTYNS,name);
	}
	
	public QualifiedName(final CharSequence namespace, final CharSequence name)
	{
		this.namespace=namespace;
		this.name=name;
	}
	
	public QualifiedName(final QualifiedName other, final CharSequence name)
	{
		this.namespace=other.getNamespace();
		this.name=name;
	}
	
	
	public CharSequence getNamespace() {return namespace;}
	public CharSequence getName() {return name;}

	
	public boolean hasName(final CharSequence ch)
	{
		return name.toString().compareTo(ch.toString())==0;
	}
	

	@Override
	public int hashCode()
	{
		return namespace.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int compareTo(QualifiedName o)
	{
		if(o==this) return 0;
		if(o==null) return 1;
		final QualifiedName other=(QualifiedName)o;
		int r=0;
		if ((r=compareCharSequence(namespace,other.namespace))!=0) return r;
		return compareCharSequence(name,other.name);
	}

	protected int compareCharSequence(CharSequence c1, CharSequence c2)
	{
		return c1.toString().compareTo(c2.toString());
	}
	
	@Override
	public String toString()
	{
		return name.toString();
	}
	
	
	
	
}

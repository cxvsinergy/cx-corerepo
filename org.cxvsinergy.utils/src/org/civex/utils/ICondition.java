package org.civex.utils;

public interface ICondition<M,E> 
{
	public final static int STATE_DEFINED=0x100;
	public final static int STATE_EXPIRED=STATE_DEFINED | 200;
	public final static int STATE_CHANGING=STATE_DEFINED | 0x800;
	public final static int STATE_TRUE=1  | STATE_DEFINED;
	public final static int STATE_FALSE=2 |STATE_DEFINED;
	public final static int STATE_ANY = STATE_DEFINED|STATE_CHANGING|STATE_EXPIRED|STATE_TRUE|STATE_FALSE;
	
	
	public boolean isConditionDefined();
	public int checkCondition(M m,E el, int state);
	public boolean hasCondition(M model,E el,int state);
	public Object getConditionImpl();
	public boolean isCompositeCondition();
	public int getConditionLevel();
	public ICondition<M,E>  getInverseCondition();
}

package org.civex.utils;

public interface INotificationListener<M,T>
{
	enum OP{ADD, REMOVE, CLEAR, CHANGED, RESET, TX_START, TX_END, TX_ABORTED};
	public void onElementChanged(M model, T element, OP operation);
}

package org.civex.utils;

public interface IObjectResolver<M,T,Z>
{
  public Z resolveObjectValue(M model, T obj);
  public void upsertObjectValue(M model, T obj, Z v);
}

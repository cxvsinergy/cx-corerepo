package org.civex.utils;

public interface IObjectResolver<M,T,Z>
{
  public Z resolveObjectValue(M model, T obj);
  public boolean upsertObjectValue(M model, T obj, Z v);
}

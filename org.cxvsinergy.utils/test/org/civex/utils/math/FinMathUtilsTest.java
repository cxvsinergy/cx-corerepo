package org.civex.utils.math;

import junit.framework.TestCase;

import org.civex.utils.fin.FinMathUtils;

public class FinMathUtilsTest extends TestCase
{
public void testMe()
{
	double v1=FinMathUtils.calcAssetDeltaCcy(1,3050, 3330, 1.24, 1.27,0.05,0.5);
	double v2=FinMathUtils.calcAssetDeltaCcy(1,3050, 3330, 1.24, 1.27);
	System.out.println(v1+"  "+v2+"  ");
}












}

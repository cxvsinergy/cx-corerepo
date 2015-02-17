package org.civex.utils.math;

import java.util.Random;

import org.civex.testfwk.ObjectTestCase;
import org.civex.utils.collections.FrequencyBag;
import org.civex.utils.numeric.func.FuncMethodDescriptor;
import org.civex.utils.numeric.func.FuncMethodRepeatableDescritptor;
import org.civex.utils.numeric.func.FuncResultCollector;
import org.civex.utils.numeric.func.StatsFuncResultCollector;
import org.civex.utils.numeric.func.StatsFuncResultCollector2;
import org.civex.utils.numeric.func.numfarg;

public class OptimizerUtilsTest extends ObjectTestCase
{

	public void testOpt1() throws Exception
	{
		FuncResultCollector<Double> fr=new FuncResultCollector<Double>(FuncResultCollector.MIN_ABS_CMP);
		FuncMethodDescriptor<Double> m=new FuncMethodDescriptor<Double>(RandomCalc.class,"calcMe");
		m.addResultCollector(fr);
		m.iterate();
		assertEq(-55,fr.getBestResult());
		
	}

	
	public void testFactorial() throws Exception
	{
		StatsFuncResultCollector sr=new  StatsFuncResultCollector();
		FuncMethodRepeatableDescritptor<Double> m=new FuncMethodRepeatableDescritptor<Double>(new RandomCalc(),"calcFact");
		
		m.setIterationCounterParamIndex(1);
		m.setLastResultParamIndex(2);
		m.setLastResult(1D);
		m.setMaxInvocation(5);
		m.addResultCollector(sr);
		m.iterate();
		assertEq(120,sr.getMax());
		this.performCloneableTest(sr, false);
	}


	public void testOpt3() throws Exception
	{
		FuncMethodRepeatableDescritptor<Double> m=new FuncMethodRepeatableDescritptor<Double>(new RandomCalc(),"calcUniformRandom");
		StatsFuncResultCollector<Double> sr=new  StatsFuncResultCollector();
				
		m.setIterationCounterParamIndex(1);
		m.setLastResultParamIndex(2);
		m.setLastResult(1D);
		m.setMaxInvocation(100);
		m.addResultCollector(sr);
		m.iterate();
		assertBetween(sr.getMean(),0.45,0.55);
		assertEquals(100,sr.getIterationCounter());
		assertTrue(sr.getMin()>=0);
		assertTrue(sr.getMax()<=1);
	}

	public void testOp4() throws Exception
	{
		FuncMethodRepeatableDescritptor<Double> m=new FuncMethodRepeatableDescritptor<Double>(new RandomCalc(),"calcUniformRandom2");
		StatsFuncResultCollector2<Double> sr=new  StatsFuncResultCollector2<Double>();
				
		m.setIterationCounterParamIndex(1);
		m.setLastResultParamIndex(2);
		m.setLastResult(1D);
		m.setMaxInvocation(100);
		m.addResultCollector(sr);
		m.iterate();
		assertEquals(100,sr.getIterationCounter());
		FrequencyBag l=sr.getIteratedResults();
		for (int ii=0;ii<l.size();ii++)
		{
			System.out.println(l.getIndexRow(ii));	
		}
		
	}

	
	
	public static class RandomCalc
	{
		
		
		public static double calcMe(@numfarg(min=0,max=3,step=0.1) double v1,
				@numfarg(min=2,max=5,step=0.1) double v2,
				@numfarg(min=7,max=9,step=0.1) double v3)
			{return (v2-10)*(v2+6);}
		
			protected Random r=new Random();
			
			public double calcFact(@numfarg(min=1,max=1,step=1)double v0, double v1, double v2, 
								   @numfarg(min=1,max=1,step=1)double v3)
			{
				
				v1++;
				double r=v1*v2;
				//System.out.println(r+" A="+v1+" B="+v2+" C="+v3);
				return r;
			}
			
			public double calcUniformRandom(
					@numfarg(min=1,max=1,step=1) double v1,
												 double v2, 
												 double v3,
					@numfarg(min=1,max=1,step=1) double v4)
			{
					
					return r.nextDouble();  
			}
			
			public double calcUniformRandom2(
					@numfarg(min=1,max=1,step=1) double v1,
					double v2, double v3,
					@numfarg(min=1,max=100,step=1) double v4)
			{
					return r.nextDouble();  
			}
			
			public double calcRateParity()
			{
				return 0;
				//(1+r1)^t1/xchr2=(1+r2)^t2;
				//1.11/xchr=1.05
			}
	}	
	
}



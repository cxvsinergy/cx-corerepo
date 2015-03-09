package org.civex.utils.generators;

import junit.framework.TestCase;

import org.civex.utils.numeric.generators.UniformRandomIntGenerator;

public class UniformRandomIntGeneratorTest extends TestCase
{
    public void testGen1()
    {
    	UniformRandomIntGenerator g=new UniformRandomIntGenerator(10,11);
    	for (int ii=0;ii<100;ii++)
    	{
    		double d=g.next();
    		System.out.println(d);
    	}
    }
}

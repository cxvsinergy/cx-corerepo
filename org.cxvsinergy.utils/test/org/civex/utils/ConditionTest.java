package org.civex.utils;

import junit.framework.TestCase;

import org.civex.utils.conditions.LesserGreaterNumericCondition;
import org.civex.utils.conditions.RangeBetweenCondition;

public class ConditionTest extends TestCase 
{
  public void testMe()
  {
	  ICondition<Object,Double> cond1=new RangeBetweenCondition<Object,Double>(5,10);
	  
	  ICondition<Object,Double>  ge=new LesserGreaterNumericCondition<Object,Double>(18,false,false);
	  ICondition<Object,Double>  le=new LesserGreaterNumericCondition<Object,Double>(18,true,false);
	  ICondition<Object,Double>  eq=new RangeBetweenCondition<Object,Double>(5,5);
	  //
	  assertTrue(cond1.hasCondition(null, 23.0, ICondition.STATE_FALSE));
	  assertTrue(cond1.hasCondition(null, 6.00, ICondition.STATE_TRUE));
	  assertTrue(cond1.getInverseCondition().hasCondition(null, 6.00, ICondition.STATE_FALSE));
	  assertTrue(cond1.getInverseCondition().hasCondition(null, 1.00, ICondition.STATE_TRUE));
	  assertTrue(eq.hasCondition(null, 5.00, ICondition.STATE_TRUE));
	  assertTrue(le.hasCondition(null, 17.00, ICondition.STATE_TRUE));
	  assertTrue(le.hasCondition(null, 19.00, ICondition.STATE_FALSE));
	  assertTrue(le.hasCondition(null, 18.00, ICondition.STATE_TRUE));
	  assertTrue(ge.hasCondition(null, 17.00, ICondition.STATE_FALSE));
	  assertTrue(ge.hasCondition(null, 19.00, ICondition.STATE_TRUE));
	  assertTrue(le.hasCondition(null, 18.00, ICondition.STATE_TRUE));
	  
  }
}

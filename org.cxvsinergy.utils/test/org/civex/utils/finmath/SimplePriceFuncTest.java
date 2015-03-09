package org.civex.utils.finmath;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.civex.utils.datetime.LocalDateTime;
import org.civex.utils.tests.ObjectTestCase;

public class SimplePriceFuncTest extends ObjectTestCase
{
		public void testJavaObject()
		{
			SimpleMarketPrice px1=new SimpleMarketPrice(100,10,1);
			SimpleMarketPrice px2=new SimpleMarketPrice(100,10,1);
			SimpleMarketPrice px3=new SimpleMarketPrice(100,10,2);
			performCompareTest(px1, px2, px3);
			performCollectionTest(px1, px2, px3);
			performObjectTest(px1, px2, px3);
		}
		
		public void testFunc1()
		{
			BiFunction<IMarketPrice,IMarketPrice,IMarketPrice> sum;//=(SimpleMarketPrice px1, SimpleMarketPrice px2) -> { return null;}
			sum=(IMarketPrice px1, IMarketPrice px2) -> { return null;};
			
			Predicate <IMarketPrice> hilo=(IMarketPrice p)->{ return 1>2;};
			Supplier s;
	
		}
		
		
		public void testFuncConverter()
		{
			SimpleMarketPrice px1=new SimpleMarketPrice(100,10,1);
			BiFunction<ProxyMarketPrice,Double, Double> fvfunc=(ProxyMarketPrice p, Double v)->{return v*(1+0.04);};
			BiFunction<ProxyMarketPrice,Double, Double> exch=(ProxyMarketPrice p, Double v)->{return v*1.08;}; 
			
			SimpleMarketPrice px3=new SimpleMarketPrice(100,10,1);
			
	
		}
		

public static List<Predicate> filterObjects (List<Predicate> employees, Predicate<Predicate> predicate) {
	

	
	Random r=new Random();
	
	r.ints()
	    .limit(5)
	    .forEach(System.out::println);
        return employees.stream().filter( predicate ).collect(Collectors.<Predicate>toList());
    }
}

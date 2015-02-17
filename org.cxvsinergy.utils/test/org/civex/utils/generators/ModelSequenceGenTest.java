package org.civex.utils.generators;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.civex.utils.collections.StringList;

public class ModelSequenceGenTest extends TestCase
{
  public void testMe()
  {
	  List<String> color=new StringList("red;yellow;green;white;blue");
	  List<String> size=new StringList("tiny;small;big;large");
	  List<String> shape=new StringList("round;flat;oval;box;");
	  List<String> age=new StringList("10;20;30;40;"); 
	  List<String> mat=new StringList("iron;wood;copper;paper");
	  
	  final List<List<?>> states=new ArrayList();
	  states.add(color);
	  states.add(size);
	  states.add(shape);
	  states.add(age);
	  states.add(mat);
	  
  }
}

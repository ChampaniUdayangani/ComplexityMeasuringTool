package com.CNC;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CNCTester {
	CNCCalculator CNCCalculatorInstance;
	int actualWeight;
	
	 @Test
	   public void testStatementNesting() {
		 ArrayList<String> statements = new ArrayList<String>(Arrays.asList
				   ("public class Test {",

					   "public static void main(String args[]) {",
					      "int x = 30;",
					      "int y = 10;",

					      "if( x == 30 ) {",
					         "if( y == 10 ) {",
					            "System.out.print(\"X = 30 and Y = 10\");",
							"if(y==5){",
								"System.out.println(\"ff\");",
								"n=o;",

							"}",
					         "}",
					      "}",
					   "}",
					"}"
							));
			
		   CNCCalculatorInstance = new CNCCalculator();
		   CNCCalculatorInstance.nestingFile(statements);
		   actualWeight = CNCCalculatorInstance.getWeightValue();
		   assertEquals(14, actualWeight);
	   }

	 @Test
	   public void testIfStatementNotNesting() {
		 ArrayList<String> statements = new ArrayList<String>(Arrays.asList
				   ("public static void main(String[] args){",

						"int number1 =0;",
						"int a,b;",
						"int input1;",
						"Scanner scn = new Scanner(system.in);",
						"System.out.print(\"Input number :\");",
						"input1 = scn.nextInt();",

						"findNumber num = new findNumber(input1);",
						"num.displayDetails();",

					"}"
							));
			
		   CNCCalculatorInstance = new CNCCalculator();
		   CNCCalculatorInstance.nestingFile(statements);
		   actualWeight = CNCCalculatorInstance.getWeightValue();
		   assertEquals(0, actualWeight);
	   }
}

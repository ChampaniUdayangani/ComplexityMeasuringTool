package com.CTC;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.exception.ExceptionHandler;

public class CTCTester {
	CTCCalculator CTCCalculatorInstance;
	int actualWeight;
	
	
   @Test
   public void testIfWithoutOperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectConditionalControlStructure("if ((number == 0)");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(1, actualWeight);
   }
   
   @Test
   public void testIfEsleWithoutOperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectConditionalControlStructure("else if((number == 0)");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(1, actualWeight);
   }
   
   @Test
   public void testConditionalControlStructureWithOROperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectConditionalControlStructure("if ((number == 0) || (number == 1))");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(2, actualWeight);
   }
   
   @Test
   public void testConditionalControlStructureWithANDOperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectConditionalControlStructure("if ((number == 0) && (number == 1))");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(2, actualWeight);
   }
   
   @Test
   public void testWhileWithoutOperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectIterativeControlStructure("while (x==5)");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(2, actualWeight);
   }
   
   @Test
   public void testWhileWithOperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectIterativeControlStructure("while ((count<45) && (status=='available'))");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(4, actualWeight);
   }
   
   @Test
   public void testForWithoutOperator() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectIterativeControlStructure("for(int iteration=3; iteration<Array.length; iteration++)");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(2, actualWeight);
   }
   

   @Test
   public void testCatchStatement() {
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectCatch("catch (Exception ex)");
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(1, actualWeight);
   }
   
   @Test
   public void testSwitchCase() {
	   ArrayList<String> statements = new ArrayList<String>(Arrays.asList
			   ("int day = 4;", 
				"switch (day) {", 
				"case 1:",
				"System.out.println(\"Monday\");",
				"break;",
				"case 2:",
				"System.out.println(\"Tuesday\");",
				"break;",
				"case 3:",
				"System.out.println(\"Wednesday\");",
				"break;",
				"case 4:",
				"System.out.println(\"Thursday\");",
				"break;",
				"case 5:",
				"System.out.println(\"Friday\");",
				"break;",
				"case 6:",
				"System.out.println(\"Saturday\");",
				"break;",
				"case 7:",
				"System.out.println(\"Sunday\");",
				"break;"
				));
		
	   CTCCalculatorInstance = new CTCCalculator();
	   CTCCalculatorInstance.detectSwitch(statements);
	   actualWeight = CTCCalculatorInstance.getWeightValue();
	   assertEquals(7, actualWeight);
   }
}

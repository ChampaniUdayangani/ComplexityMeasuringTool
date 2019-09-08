package com.CS;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CSTester {
	CSCalculator CSCalculatorInstance;
	int actualWeight;
	
	
   @Test
   public void testReference_Dereference() {
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectReference_Dereferences (" (int n1 = * p1)");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(2, actualWeight);
   }
   

   @Test
   public void testKeywords() {
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectKeyWords("Eclips obj = new Eclips() ");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(2, actualWeight);
   }
   
   @Test
   public void testArithmaticOperators() {
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.arithmaticOperators("(3-2)");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1, actualWeight);
   }

   
   @Test
   public void testRelationalOperators() {
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.relationalOperators("3 > 2");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1, actualWeight);
   }
   
   @Test
   public void testMiscellaneouosOperators(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectMiscellaneouosOperators(" function abc(x,y)");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1,actualWeight);   
   }
   
   @Test
   public void testQuotations(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectQuotation("\"i am programmer\" \"i am student\"");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(2,actualWeight);   
   }
   
   @Test
   public void testMethods(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectMethods(" public void vehicle(){} ");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1,actualWeight);   
   }
   
   @Test
   public void testClass(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectClass(" public class animal{} ");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1,actualWeight);   
   }

   @Test
   public void testVariables(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectVariable(" int x; ");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1,actualWeight);   
   }
   
   @Test
   public void testNumericValues(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectNumericValues(" 3500 ");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(1,actualWeight);   
   }

   @Test
   public void testKeyword(){
	   CSCalculatorInstance = new CSCalculator();
	   CSCalculatorInstance.detectKeywords(" public class elephant implements mammels{} ");
	   actualWeight = CSCalculatorInstance.getWeightValue();
	   assertEquals(3,actualWeight);   
   }   
}

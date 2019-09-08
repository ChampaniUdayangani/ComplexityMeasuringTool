package com.CNC;

import java.util.ArrayList;

public class CNCCalculator {
	
	private static CNCCalculator uniqueInstance;
	ArrayList<String> programStatements;
	int weightValue;
	int nested = 0;
	int total =0;
	
	private static final  String ifClass = "class";
	
	private static final String ifOpened = "{";
	private static final String ifClosed = "}";
	
	private static final String acceesSpecifier_1 = "private";
	private static final String acceesSpecifier_2 = "public";
	private static final String acceesSpecifier_3 = "protected";
	    
	private static final String variableType_1 = "int";
	private static final String variableType_2 = "String";
	private static final String variableType_3 = "double";
	private static final String variableType_4 = "void";
	
	public static CNCCalculator getInstance(ArrayList<String> programStatements, int currentWeigth) {
		if(uniqueInstance == null) {
			uniqueInstance = new CNCCalculator(programStatements, currentWeigth);
		}
		return uniqueInstance;
	}
	
	
	public int getWeightValue() {
		return weightValue;
	}

	CNCCalculator(ArrayList<String> programStatements, int currentWeightValue){
		this.programStatements = programStatements;
		this.weightValue = currentWeightValue;
	}
	
	CNCCalculator(){}
	
	//Function to detect the level of nesting of control structures
	protected void nestingFile(ArrayList<String> programStatements) {
		 int nested =0;
		 for(int count=0;count<programStatements.size();count++) {
			 if(!((programStatements.get(count).contains(ifClass))||(programStatements.get(count).contains(acceesSpecifier_1)) || (programStatements.get(count).contains(acceesSpecifier_2)) || (programStatements.get(count).contains(acceesSpecifier_3)) )) {
		   		  if(programStatements.get(count).contains(ifOpened)) {
		   			nested++;
		   			weightValue = weightValue + nested;
		   			System.out.println(programStatements.get(count)+"           :"+nested);
		   		  }
		   		  else if(programStatements.get(count).contains(ifClosed)) {
		   			nested = 0;
		   			continue;
		   					
		   		  }
		   		  else {
		   				weightValue = weightValue + nested;
		   				System.out.println(programStatements.get(count)+"           :"+  nested);
		   		}
		 }
		   	
		 }
	}
	public void calculateCNC() {
		this.nestingFile(this.programStatements);
		
	}

}

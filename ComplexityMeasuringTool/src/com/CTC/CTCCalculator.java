package com.CTC;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CTCCalculator {
	private static CTCCalculator uniqueInstance;
	ArrayList<String> programStatements;
	int weightValue;
	Pattern pattern;
	
	private static final String operators = "(\\|\\||&&)";
	private static final String whileStructure = "while ?\\(.+?\\)";
	private static final String forStructure = "for ?\\(.*?;.*?;.*?\\)";
	private static final String conditionalControlStructures = "(if|else if)";
	private static final String switchCases = "(case\\s?)";
	
	//Get Singleton instance
	public static CTCCalculator getInstance(ArrayList<String> programStatements, int currentWeight) {
		if(uniqueInstance == null) {
			uniqueInstance = new CTCCalculator(programStatements, currentWeight);
		}
		return uniqueInstance;
	}
	
	//Get weight value
	public int getWeightValue() {
		return weightValue;
	}

	CTCCalculator(ArrayList<String> programStatements, int currentWeightValue){
//		this.programStatements = programStatements;
		this.weightValue = currentWeightValue;
	}
	
	CTCCalculator(){}
	
	//Function to detect Conditional Control Structures
	protected void detectConditionalControlStructure(String programStatement) {
		if(programStatement.contains("if")) {
			pattern = Pattern.compile(conditionalControlStructures);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=1;
				System.out.println(programStatement +"          : "+1);
			} 
			this.detectOperator(programStatement, 1);
		}
		
		
	}
	
	//Function to detect Iterative Control Structures
	protected void detectIterativeControlStructure(String programStatement) {
		if(programStatement.contains("while")) {
			pattern = Pattern.compile(whileStructure);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=2;
				System.out.println(programStatement +"          : "+2);
			}
			this.detectOperator(programStatement, 2);
		}
		else if(programStatement.contains("for")) {
			pattern = Pattern.compile(forStructure);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=2;
				System.out.println(programStatement +"          : "+2);
			}
			this.detectOperator(programStatement, 2);
		}
		
			
	}
	
	//Function to detect operators
	protected void detectOperator(String programStatement, int weight) {
			pattern = Pattern.compile(operators);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue = weightValue+weight;	
				System.out.println(programStatement +"(Operator)      : "+weight);
			}
			
	}
	
	
	//Function to detect catch
	protected void detectCatch(String programStatement) {
		if(programStatement.contains("catch")) {
			pattern= Pattern.compile("catch");
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=1;
				System.out.println(programStatement +"          : "+1);
			} 
		}
		
	}
	
	//Function to detect switch 
	protected void detectSwitch(ArrayList<String> programStatements) {
		for(int i=0;i<programStatements.size();i++) {
			if(programStatements.get(i).contains("switch")) {
				for(int count=i; count<programStatements.size();count++) {
					if(programStatements.get(count).contains("case")) {
							pattern= Pattern.compile(switchCases);
							Matcher m = pattern.matcher(programStatements.get(count));
							while (m.find()){
								weightValue +=1;
								System.out.println(programStatements.get(count) +"          : "+1);
							}
							
						}
					i=count;
				}
			}
		}
	}
	
	//Factory function
	public int calculateCTC(String progStatement) {
//		for(int count=0; count<programStatements.size(); count++) {
//			this.detectConditionalControlStructure(this.programStatements.get(count));
//			this.detectIterativeControlStructure(this.programStatements.get(count));
//			this.detectCatch(this.programStatements.get(count));
//			this.getWeightValue();
//		}
//		this.detectSwitch(programStatements);
//		return weightValue;
		weightValue = 0;
		this.detectConditionalControlStructure(progStatement);
		this.detectIterativeControlStructure(progStatement);
		this.detectCatch(progStatement);
		return weightValue;
	}
}

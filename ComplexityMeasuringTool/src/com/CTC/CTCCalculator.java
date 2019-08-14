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
	
	
	public static CTCCalculator getInstance(ArrayList<String> programStatements, int currentWeigth) {
		if(uniqueInstance == null) {
			uniqueInstance = new CTCCalculator(programStatements, currentWeigth);
		}
		return uniqueInstance;
	}
	
	
	public int getWeightValue() {
		return weightValue;
	}

	CTCCalculator(ArrayList<String> programStatements, int currentWeightValue){
		this.programStatements = programStatements;
		this.weightValue = currentWeightValue;
	}
	
	CTCCalculator(){}
	
	protected void detectConditionalControlStructure(String programStatement) {
		if(programStatement.contains("if")) {
			pattern = Pattern.compile("if");
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=1;
			} 
			
			this.detectOperator(programStatement, 1);
		}
		
		
		
	}
	
	protected void detectIterativeControlStructure(String programStatement) {
		if(programStatement.startsWith("while")) {
			pattern = Pattern.compile(whileStructure);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=2;
			}
			this.detectOperator(programStatement, 2);
		}
		else if(programStatement.startsWith("for")) {
			pattern = Pattern.compile(forStructure);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=2;
			} 
			this.detectOperator(programStatement, 2);
		}
			
	}
	
	protected void detectOperator(String programStatement, int weight) {
		
			pattern = Pattern.compile(operators);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue = weightValue+weight;	
			}
	}
	
	protected void detectCatch(String programStatement) {
		if(programStatement.contains("catch")) {
			pattern= Pattern.compile("catch");
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=1;
			} 
		}
	}
	
	protected void detectSwitch(ArrayList<String> programStatements) {
		for(int i=0;i<programStatements.size();i++)
			if(programStatements.get(i).contains("switch")) {
				for(int count=i; count<programStatements.size();count++) {
					if(programStatements.get(count).contains("case")) {
							pattern= Pattern.compile(switchCases);
							Matcher m = pattern.matcher(programStatements.get(count));
							while (m.find()){
								weightValue +=1;
							}
							
						}
					i=count;
				}
			}
	}
	
	public void calculateCTC() {
		for(int count=0; count<programStatements.size(); count++) {
			this.detectConditionalControlStructure(this.programStatements.get(count));
			this.detectIterativeControlStructure(this.programStatements.get(count));
			this.detectCatch(this.programStatements.get(count));
		}
		this.detectSwitch(programStatements);
		
	}
}

package com.CTC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CTCCalculator {
	private static CTCCalculator uniqueInstance;
	String programCode;
	String [] programStatement;
	int weightValue;
	Pattern pattern;
	
	private static final String operators = "(\\|\\||&&)";
	private static final String iterativeControlStructures = "(while|do while|for)";
	private static final String conditionalControlStructures = "(if|else if)";
	private static final String switchCases = "(case\\s?)";
	
	
	public static CTCCalculator getInstance(String progCode, int currentWeigth) {
		if(uniqueInstance == null) {
			uniqueInstance = new CTCCalculator(progCode, currentWeigth);
		}
		return uniqueInstance;
		
	}
	
	public int getWeightValue() {
		return weightValue;
	}

	CTCCalculator(String programCode, int currentWeightValue){
		this.programCode = programCode;
		this.programStatement = programCode.split("\n");
		this.weightValue = currentWeightValue;
	}
	
	
	protected void detectControlStructure(String programStatement) {
		if(programStatement.contains("if")) {
			pattern = Pattern.compile("if");
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=1;
			} 
		}
		
		
	}
	
	protected void detectIterativeControlStructure(String programStatement) {
			pattern = Pattern.compile(iterativeControlStructures);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue +=2;
			} 
	}
	
	protected void detectOperator(String programStatement) {
		
			pattern = Pattern.compile(operators);
			Matcher m = pattern.matcher(programStatement);
			while (m.find()){
				weightValue ++;	
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
	
	protected void detectSwitch(String programStatement[]) {
		for(int i=0;i<programStatement.length;i++)
			if(programStatement[i].contains("switch")) {
				for(int count=i; count<programStatement.length;count++) {
					if(programStatement[count].contains("case")) {
							pattern= Pattern.compile(switchCases);
							Matcher m = pattern.matcher(programStatement[count]);
							while (m.find()){
								weightValue +=1;
							}
							
						}
					i=count;
				}
			}
	}
	
	public void calculateCTC() {
		for(int count=0; count<programStatement.length; count++) {
			this.detectControlStructure(this.programStatement[count]);
			this.detectIterativeControlStructure(this.programStatement[count]);
			this.detectOperator(this.programStatement[count]);
			this.detectCatch(this.programStatement[count]);
		}
		this.detectSwitch(programStatement);
		
	}
}

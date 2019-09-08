package com.CI;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.CTC.CTCCalculator;

public class CICalculator {
	private static CICalculator uniqueInstance;
	private static final String inheritedClass = "(public class)\\s([a-zA-Z]+)\\s(extends)\\s([a-zA-Z]+)";
	private static final String normalClass = "(public class)\\s([a-zA-Z]+)";
	ArrayList<String> programStatements;
	int weightValue;
	Pattern pattern;
	String [] statementParts;
	ArrayList<String> baseClassToBe;
	int i =0;
	
	
	public static CICalculator getInstance(ArrayList<String> programStatements) {
		if(uniqueInstance == null) {
			uniqueInstance = new CICalculator(programStatements);
		}
		return uniqueInstance;
	}
	
	
	public CICalculator(ArrayList<String> programStatements) {
		this.programStatements = programStatements;
		baseClassToBe = new ArrayList<String> ();
	}
	
	public void calCCI(ArrayList<String> programStatements) {
		for(int count=0;count<programStatements.size();count++) {
			
			if(programStatements.get(count).contains("class") && programStatements.get(count).contains("extends")) {
				pattern = Pattern.compile(inheritedClass);
				Matcher m = pattern.matcher(programStatements.get(count));
				if (m.find()){
					System.out.println(programStatements.get(count));
					
					statementParts = programStatements.get(count).split(" ");
					if(!(baseClassToBe.size() == 0)) {
						for(int x=0; x<baseClassToBe.size(); x++) {
							if(baseClassToBe.get(x).equals(statementParts[2])){ 
								weightValue ++;
							}
						}
					}
					//Weight for the Object class
					if (baseClassToBe.size() == 0) {
						weightValue ++;
					}
					
					baseClassToBe.add(statementParts[4].replaceAll("\\s", ""));
					System.out.println(baseClassToBe);
					
					
				} 
			}
			
			else if (programStatements.get(count).contains("class")) {
				pattern = Pattern.compile(normalClass);
				Matcher m = pattern.matcher(programStatements.get(count));
				if (m.find()){
					weightValue = 1;
				}
				
			}
		}
		
		weightValue++;
		System.out.println(weightValue);
	}
	
	public int calculateCI() {
		weightValue = 0;
		this.calCCI(this.programStatements);
		return weightValue;
	}
}

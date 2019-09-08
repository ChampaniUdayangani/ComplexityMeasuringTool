package com.CS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSCalculator {
	private static CSCalculator uniqueInstance;
	ArrayList<String> programStatements;
	int weightValue;
	Pattern pattern;
	
	private static final String reference = "(*)";
	private static final String dereference = "(&)";
	
	
	private static final String detectReference_Dereference = "(\\*|\\&)";
	private static final String detectKeyWords = "(new|delete|throw|throws)";
	private static final String arithmaticOperators = "[+\\-*/.]";
	private static final String relationalOperators = "(<|>|<=|>=|==|!=)";
	private static final String logicalOperators = "(\\|\\||&&)";
	//bitwise operators
	
	private static final String miscellaneouosOperators = "[,|_>|.|::]";
	private static final String assignmentOperators = "\\+=|\\-=|\\*=|\\/=|=|>>>=|&=|%=|<<=|>>=|\\^=|\\+=";
	private static final String quotations = "\"[^\"]*\"";
	private static final String method ="\\w+ +\\w+ \\w+ *\\([^\\)]*\\) *\\{";
	private static final String classChecker = "\\w+ +\\w+ \\w+ *\\ *\\{";
	private static final String variablesChecker = " [a-zA-Z$_],|[a-zA-Z0-9$_]*;"; 
	private static final String numericValues = "[0-9]";
	private static String key = "abstract|for|continue|switch|assert|default|package|synchronized|strictfp|volatile|const|float|native|super|while|boolean" +
			"|do|if|private|this|break|double|implements|protected|byte|else|import|public|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long"; 

	

	public static CSCalculator getInstance(ArrayList<String> programStatements, int currentWeigth) {    //Create the singleton Object
		if(uniqueInstance == null) {
			uniqueInstance = new CSCalculator(programStatements, currentWeigth);
		}
		return uniqueInstance;
		
	}
	
	public int getWeightValue() {
		return weightValue;
	}

	CSCalculator(ArrayList<String> programStatements, int currentWeightValue){
		this.programStatements = programStatements;
		this.weightValue = currentWeightValue;
	}
	
	CSCalculator(){}																				//testing part
	

	
	protected void detectReference_Dereferences(String programStatement) {				// return the compiled version of the regular expression in to the pattern
		pattern = Pattern.compile(detectReference_Dereference);
		Matcher m = pattern.matcher(programStatement);									// regular expression
		while (m.find()){
			weightValue +=2;															// find the pattern in the expression
		} 
	}
	public void detectKeyWords(String programStatement) {
		pattern = Pattern.compile(detectKeyWords);
		Matcher m = pattern.matcher(programStatement);
		while (m.find()){
			weightValue +=2;
		} 
	}
	
	protected void arithmaticOperators(String programStatement) {
		pattern = Pattern.compile(arithmaticOperators);
		Matcher m = pattern.matcher(programStatement);
		while (m.find()){
			weightValue +=1;
		} 
	}
	protected void relationalOperators(String programStatement) {
		pattern = Pattern.compile(relationalOperators);
		Matcher m = pattern.matcher(programStatement);
		while (m.find()){
			weightValue +=1;
		} 
	}
	protected void logicalOperators(String programStatement) {
		pattern = Pattern.compile(logicalOperators);
		Matcher m = pattern.matcher(programStatement);
		while (m.find()){
			weightValue +=1;
		} 
	}
	
	
	protected void detectReference() {
		for(int count=0;count<programStatements.size();count++) {
			if((programStatements.get(count).startsWith("*"))) {
				pattern = Pattern.compile(reference);
				Matcher m= pattern.matcher(programStatements.get(count));
				if(m.find()){
					programStatements.remove(count);
					count--;
				} 
			}
		}
	}
	
	protected void detectDereference() {
		for(int count=0;count<programStatements.size();count++) {
			if((programStatements.get(count).contains("&"))) {	
				pattern = Pattern.compile(dereference);
				Matcher matcher= pattern.matcher(programStatements.get(count));
				if(matcher.find()){
					//to do
				} 
				
			}
		}
	}
	
	
	
		//QUOTATIONS
		public void detectQuotation(String programStatement) {
			pattern = Pattern.compile(quotations);
			Matcher m = pattern.matcher(programStatement);
			while(m.find()) {
				weightValue++;
			}
		}
		//NUMERIC VALUES
		public void detectNumericValues(String programStatement) {
			pattern = Pattern.compile(numericValues);
			Matcher m = pattern.matcher(programStatement);
			if(m.find()) {
				weightValue++;
				
			}	
		}
		//JAVA KEYWORDS
		public void detectKeywords(String programStatement){
			pattern = Pattern.compile(key);
			Matcher m = pattern.matcher(programStatement);
			while(m.find()){
				weightValue++;
			}
		}
		//MISCELLANEOUS OPERATORS
		public void detectMiscellaneouosOperators(String programStatement){
			pattern = Pattern.compile(miscellaneouosOperators);
			Matcher m = pattern.matcher(programStatement);
			while(m.find()){
				weightValue++;
			}
		}
		//VARIABLES
		public void detectVariable(String programStatement){
			pattern = Pattern.compile(variablesChecker);
			Matcher m = pattern.matcher(programStatement);
			while(m.find()){
				weightValue++;
			}	
		}
		//CLASSES
		public void detectClass(String programStatement){
			pattern = Pattern.compile(classChecker);
			Matcher m = pattern.matcher(programStatement);
			while(m.find()){
				weightValue++;
			}	
		}
		//ASSIGNMENT OPERATORS
		public void detectAssignmentOperators(String programStatement){
			pattern = Pattern.compile(assignmentOperators);
			Matcher m = pattern.matcher(programStatement);
			while(m.find()){
				weightValue++;
			}	
		}
		//METHODS
		public void detectMethods (String programSatement){
			pattern = Pattern.compile(method);
			Matcher m = pattern.matcher(programSatement);
			while(m.find()){
				weightValue++;
			}
		}
	
	public void calculateCS() {
		for(int count=0; count<programStatements.size(); count++) {
			
			//Kinish
//			this.detectDereference();
//			this.detectReference();
//			this.detectKeyWords(programStatements.get(count));
//			this.arithmaticOperators(programStatements.get(count));
//			this.relationalOperators(programStatements.get(count));
//			this.logicalOperators(programStatements.get(count));
//			this.detectReference_Dereferences(programStatements.get(count));
			
		
			//Shihan
			this.detectQuotation(programStatements.get(count));
			this.detectNumericValues(programStatements.get(count));
			this.detectKeywords(programStatements.get(count));
			this.detectMiscellaneouosOperators(programStatements.get(count));
			this.detectVariable(programStatements.get(count));
			this.detectClass(programStatements.get(count));
			this.detectAssignmentOperators(programStatements.get(count));
			this.detectMethods(programStatements.get(count));
			}
		
		}
}

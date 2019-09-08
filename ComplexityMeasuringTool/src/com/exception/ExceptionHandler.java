package com.exception;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionHandler {
	
	Pattern pattern;
	Matcher matcher;
	private static final String comment = "(//|/*)";
	private static final String quotation = "(\"|\')";
	private static final String classStatement = "public class";
	private static final String startBrackets = "\\{";
	private static final String closeBrackets = "\\}";
	private boolean ignoreStatus;
	public ExceptionHandler(){
		ignoreStatus = false;
	
	}
	
	
	public boolean detectStartBrackets(String programStatement) {
		ignoreStatus = false;
		pattern = Pattern.compile(startBrackets);
		matcher= pattern.matcher(programStatement.replaceAll("\\s", ""));
		if(matcher.find()){
			ignoreStatus = true;
			return ignoreStatus;
		} 
		return ignoreStatus;
	}
	
	public boolean detectCloseBrackets(String programStatement) {
		ignoreStatus = false;
		pattern = Pattern.compile(closeBrackets);
		matcher= pattern.matcher(programStatement);
		if(matcher.find()){
			ignoreStatus = true;
			return ignoreStatus;
		} 
		return ignoreStatus;
	}
	
	public boolean detectComment(String programStatement) {
		ignoreStatus = false;
		
		if((programStatement.startsWith("//")) || (programStatement.startsWith("/*")) || (programStatement.startsWith("*"))) {
			pattern = Pattern.compile(comment);
			matcher= pattern.matcher(programStatement);
			if(matcher.find()){
				ignoreStatus = true;
				return ignoreStatus;
			} 
		}

		return ignoreStatus;
	}
	
//	public boolean detectQuotations() {
//		ignoreStatus = false;
//		for(int count=0;count<programStatements.size();count++) {
//			if((programStatements.get(count).contains("\"")) || (programStatements.get(count).contains("\'"))) {	
//				pattern = Pattern.compile(quotation);
//				matcher= pattern.matcher(programStatements.get(count));
//				if(matcher.find()){
//					//to do
//				} 
//				
//			}
//		}
//	}
	
	public boolean detectEmptyLine(String programStatement) {
		ignoreStatus = false;
		
		if((programStatement.isEmpty())) {	
			ignoreStatus = true;
			return ignoreStatus;
		} 
		
		return ignoreStatus;
	}
	
	public boolean detectClass(String programStatement) {
		ignoreStatus = false;
		
		if((programStatement.contains("class"))) {	
			pattern = Pattern.compile(classStatement);
			matcher= pattern.matcher(programStatement);
			if(matcher.find()){
				ignoreStatus = true;
				return ignoreStatus;
			} 
		} 
		
		return ignoreStatus;
	}
	
//	public ArrayList<String> handleExceptions() {
//		this.detectClass();
//		this.detectComment();
//		this.detectEmptyLine();
//		
//		for(int count=0; count<programStatements.size();count++) {
//			System.out.println(programStatements.get(count));
//		}
//		return programStatements;
//	}

}

package com.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionHandler {
	String programStatement;
	Pattern pattern;
	Matcher matcher; 
	boolean isValidStatement;
	
	private static final String comment = "(//|/*|\\*)";
	private static final String quotation = "(\"|\')";
	
	public ExceptionHandler(String programStatement){
		this.programStatement = programStatement;
		this.isValidStatement = true;
	}
	public void detectComment() {
		if((programStatement.contains("//")) || (programStatement.contains("/*")) || (programStatement.contains("*"))) {
			pattern = Pattern.compile(comment);
			matcher= pattern.matcher(programStatement);
			if(matcher.find()){
				this.isValidStatement = false;
			} 
			
		}
	}
	
	public void detectQuotations() {
		if((programStatement.contains("\"")) || (programStatement.contains("\'"))) {
			pattern = Pattern.compile(quotation);
			matcher= pattern.matcher(programStatement);
			if(matcher.find()){
				this.isValidStatement = false;
				System.out.println(isValidStatement);
			} 
			
		}
	}

}

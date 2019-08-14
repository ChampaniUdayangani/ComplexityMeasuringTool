package com.exception;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionHandler {
	String programLines[];
	ArrayList<String> programStatements;
	Pattern pattern;
	Matcher matcher;
	
	
	private static final String comment = "^(//|/*)";
	private static final String quotation = "(\"|\')";
	
	public ExceptionHandler(String programCode){
		this.programStatements = new ArrayList<String>();
		this.programLines = programCode.split("\n");
		
		for(int count=0; count<programLines.length; count++) {
			programStatements.add(programLines[count]);
		}
		
	}
	
	protected void detectComment() {
		for(int count=0;count<programStatements.size();count++) {
			if((programStatements.get(count).contains("//")) || (programStatements.get(count).contains("/*")) || (programStatements.get(count).contains("*"))) {
				pattern = Pattern.compile(comment);
				matcher= pattern.matcher(programStatements.get(count));
				if(matcher.find()){
					programStatements.remove(count);
					count--;
				} 
			}
		}
	}
	
	protected void detectQuotations() {
		for(int count=0;count<programStatements.size();count++) {
			if((programStatements.get(count).contains("\"")) || (programStatements.get(count).contains("\'"))) {	
				pattern = Pattern.compile(quotation);
				matcher= pattern.matcher(programStatements.get(count));
				if(matcher.find()){
					programStatements.remove(count);
				} 
				
			}
		}
	}
	
	protected void detectEmptyLine() {
		for(int count=0;count<programStatements.size();count++) {
			if((programStatements.get(count).isEmpty())) {	
				programStatements.remove(count);
			} 
		}
	}
	
	public ArrayList<String> handleExceptions() {
		this.detectComment();
		this.detectEmptyLine();
		for(int i=0;i<programStatements.size();i++) {
			System.out.println(programStatements.get(i));
		}
		return programStatements;
	}

}

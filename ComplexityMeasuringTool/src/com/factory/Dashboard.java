package com.factory;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.CTC.CTCCalculator;
import com.exception.ExceptionHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;

public class Dashboard {
	private String programCode;
	private int weightValue = 0;
	
	private CTCCalculator CTCCalculatorInstance;
	private ExceptionHandler exceptionHandler;
	
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnCalculateComplexity = new JButton("Calculate Complexity");
		frame.getContentPane().add(btnCalculateComplexity, BorderLayout.SOUTH);
		
		JTextArea txtrEnterYourProgram = new JTextArea();
		txtrEnterYourProgram.setText("Enter Your Program code here");
		frame.getContentPane().add(txtrEnterYourProgram, BorderLayout.CENTER);
		
		btnCalculateComplexity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				programCode = txtrEnterYourProgram.getText();
				
				CTCCalculatorInstance = CTCCalculator.getInstance(programCode, weightValue);
				
				
				CTCCalculatorInstance.calculateCTC();
				weightValue = CTCCalculatorInstance.getWeightValue();
				System.out.println(weightValue);
				
				
			}
		});
	}
	
	

}

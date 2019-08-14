package com.factory;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.CTC.CTCCalculator;
import com.exception.ExceptionHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Dashboard {
	private String programCode;
	private int weightValue = 0;
	private ArrayList<String> progStatements = new ArrayList<String>();
	
	private CTCCalculator CTCCalculatorInstance;
	private ExceptionHandler exceptionHandler;
	
	private JFrame frame;
	private JTextField ctc_textField;
	private JTextField cs_textField;
	private JTextField cnc_textField;
	

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
		frame.setBounds(100, 100, 450, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cnc_textField = new JTextField();
		cnc_textField.setColumns(10);
		cnc_textField.setBackground(UIManager.getColor("Button.background"));
		cnc_textField.setBounds(149, 338, 116, 22);
		frame.getContentPane().add(cnc_textField);
		
		cs_textField = new JTextField();
		cs_textField.setColumns(10);
		cs_textField.setBackground(UIManager.getColor("Button.background"));
		cs_textField.setBounds(149, 284, 116, 22);
		frame.getContentPane().add(cs_textField);
		
		ctc_textField = new JTextField();
		ctc_textField.setBackground(UIManager.getColor("Button.background"));
		ctc_textField.setBounds(149, 311, 116, 22);
		frame.getContentPane().add(ctc_textField);
		ctc_textField.setColumns(10);
		
		JTextArea programcode_textArea = new JTextArea();
		programcode_textArea.setBounds(0, 0, 432, 213);
		frame.getContentPane().add(programcode_textArea);
		
		JButton btnNewButton = new JButton("Calculate Weight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				programCode = programcode_textArea.getText();
				
				ExceptionHandler eh = new ExceptionHandler(programCode);
				progStatements = eh.handleExceptions();
				
				
				CTCCalculatorInstance = CTCCalculator.getInstance(progStatements, weightValue);
				CTCCalculatorInstance.calculateCTC();
				weightValue = CTCCalculatorInstance.getWeightValue();
				ctc_textField.setText(Integer.toString(weightValue));
				
				
			}
		});
		btnNewButton.setBounds(109, 226, 211, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblCs = new JLabel("CS ");
		lblCs.setBounds(53, 288, 56, 16);
		frame.getContentPane().add(lblCs);
		
		JLabel lblCtc = new JLabel("CTC");
		lblCtc.setBounds(53, 315, 56, 16);
		frame.getContentPane().add(lblCtc);
		
		JLabel lblCnc = new JLabel("CNC");
		lblCnc.setBounds(53, 342, 56, 16);
		frame.getContentPane().add(lblCnc);
		
		JTextArea container_textArea = new JTextArea();
		container_textArea.setBackground(UIManager.getColor("Button.disabledShadow"));
		container_textArea.setBounds(12, 264, 408, 116);
		frame.getContentPane().add(container_textArea);
	}
}

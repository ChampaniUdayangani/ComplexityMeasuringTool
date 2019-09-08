package com.factory;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.CI.CICalculator;
import com.CNC.CNCCalculator;
import com.CS.CSCalculator;
import com.CTC.CTCCalculator;
import com.exception.ExceptionHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;

public class Dashboard {
	private String programCode;
	private int weightValue = 0;
	private ArrayList<String> progStatements = new ArrayList<String>();
	
	private CTCCalculator CTCCalculatorInstance;
	private CSCalculator CSCalculatorInstance;
	private CNCCalculator CNCCalculatorInstance;
	private ExceptionHandler exceptionHandler;
	private CICalculator CICalculatorInstance;
	
	private JFrame frame;
	private JTable table;
	
	private DefaultTableModel model;
	

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
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblNewLabel = new JLabel("Complexity Tool");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		JButton btnUploadFile = new JButton("Upload File");
		btnUploadFile.setPreferredSize(new Dimension(151, 25));
		btnUploadFile.setIcon(null);
		btnUploadFile.setToolTipText("Upload java or C++ code file");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenFile of = new OpenFile();
				try {
					of.pickme();
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
				textArea.setText(of.sb.toString());
			}
		});
		
		
		table = new JTable();
		Object [] column = {"Line No", "Program Statements", "Tokens identified under the size factor", "Cs", "Ctc", "Cnc", "Ci", "TW", "Cps", "Cr"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		
		Object [] row = new Object[10];
		
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setPreferredWidth(349);
		table.getColumnModel().getColumn(2).setPreferredWidth(241);
		table.getColumnModel().getColumn(3).setPreferredWidth(37);
		table.getColumnModel().getColumn(4).setPreferredWidth(37);
		table.getColumnModel().getColumn(5).setPreferredWidth(37);
		table.getColumnModel().getColumn(6).setPreferredWidth(37);
		table.getColumnModel().getColumn(7).setPreferredWidth(37);
		table.getColumnModel().getColumn(8).setPreferredWidth(37);
		table.getColumnModel().getColumn(9).setPreferredWidth(37);
		scrollPane.setViewportView(table);
		
		
		
		JButton btnCalculateComplexity = new JButton("Calculate Complexity");
		btnCalculateComplexity.setIcon(new ImageIcon(Dashboard.class.getResource("/icons/calculator.png")));
		btnCalculateComplexity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCalculateComplexity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] programStatements = textArea.getText().split("\n");
				progStatements = new ArrayList<String>(Arrays.asList(programStatements));
				
				//Exception Handler
				ExceptionHandler exceptionHandler = new ExceptionHandler();
				
				//Calculate CTC value
				CTCCalculatorInstance = CTCCalculator.getInstance(progStatements, weightValue);
				CICalculatorInstance = CICalculator.getInstance(progStatements);
				
				//Calculate CI value
				weightValue = CICalculatorInstance.calculateCI();
				
				for(int lineCount=0; lineCount<programStatements.length; lineCount++ ) {
					
					row [0]= lineCount+1;
					row [1]= programStatements[lineCount] ;
					row [4]= CTCCalculatorInstance.calculateCTC(programStatements[lineCount]);
					
					if(exceptionHandler.detectClass(programStatements[lineCount]) == true) {
						row [6]= 0;
					}
					else if(exceptionHandler.detectCloseBrackets(programStatements[lineCount]) == true) {
						row [6]= 0;
					}
					else if(exceptionHandler.detectEmptyLine(programStatements[lineCount]) == true) {
						row [6]= 0;
					}
					else {
						row [6]= weightValue;
					}
					
					
					model.addRow(row);
				}
				
				
			}
		});
		
		
		btnUploadFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 767, GroupLayout.PREFERRED_SIZE)
							.addGap(73)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(806)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(474)
							.addComponent(btnCalculateComplexity, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(170)
							.addComponent(btnUploadFile, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUploadFile, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCalculateComplexity))
					.addGap(55))
		);
		
		
		frame.getContentPane().setLayout(groupLayout);
	}
}

package bsu.rfe.java.group10.lab2.Slavinsky.varC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	private JTextField TextX = new JTextField("0", 10);
	private JTextField TextY = new JTextField("0", 10);
	private JTextField TextZ = new JTextField("0", 10);
	private JTextField textResult = new JTextField("", 15);
	
	private int varID = 'x';
	private int formulaID = 1;
	private boolean isMplus = false;
	
	
	
	private ButtonGroup formulasButtons = new ButtonGroup();
	private ButtonGroup variablesButtons = new ButtonGroup();
	
	private Box hboxFormulaType = Box.createHorizontalBox();
	private Box hboxFormulaDraw = Box.createHorizontalBox();
	private Box hboxVariable = Box.createHorizontalBox();
	private Box hboxVariablesImput = Box.createHorizontalBox();
	private Box hboxMButtons = Box.createHorizontalBox();
	private Box hboxResult = Box.createHorizontalBox();
	private Box hboxCalcButtons = Box.createHorizontalBox();
	
	private Image formulaImage;
	private Image formula1;
	private Image formula2;
	
	
	private JPanel formulaIlustration = new JPanel() {
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(formulaImage, 0, 0, 450, 100, null);
		}
	};
	
	
	//Methods
	private void clearVariable(int id) {
		if (id == 'x') {
			TextX.setText("0");
		}
		else if(id == 'y') {
			TextY.setText("0");
		}
		else if(id == 'z') {
			TextZ.setText("0");
		}
	}
	
	private void clearAllFields() {
		TextX.setText("0");
		TextY.setText("0");
		TextZ.setText("0");
		textResult.setText("");
	}
	
 	private double calc1() throws ArithmeticException{
 		if(y == 0 || x == 0)
 			throw new ArithmeticException("Деление на 0");
 		if(y < 0)
 			throw new ArithmeticException("Корень из отрицательного числа"); 
 		if(x == -1)
 			throw new ArithmeticException("Логарифм нуля"); 
		return Math.pow(Math.log(Math.pow(1 + x, 2)) + Math.cos(Math.PI * Math.pow(z, 3)), Math.sin(y)) + 
				Math.pow(Math.exp(Math.pow(x, 2)) + Math.cos(Math.exp(z)) + Math.sqrt(1 / y), 1 / x);
	}
	
	private double calc2() throws ArithmeticException{
 		if( x == 0)
 			throw new ArithmeticException("Деление на 0");
 		if(x < 0)
 			throw new ArithmeticException("Корень из отрицательного числа"); 
 		if(y == -1)
 			throw new ArithmeticException("Логарифм нуля"); 
		return Math.pow(Math.cos(Math.PI * Math.pow(x, 3)) + Math.log(Math.pow(1 + y, 2)), 1/4) *
				(Math.exp(Math.pow(z, 2)) + Math.sqrt(1 / x) + Math.cos(Math.exp(y)));
	}
	
	private void createFormulaRadioButton(String buttonName, final int ID) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.formulaID = ID;
				
				if(ID == 1) {
					formulaImage = formula1;
				}
				else {
					formulaImage = formula2;
				}
				formulaIlustration.updateUI();
			}
		});
		formulasButtons.add(button);
		hboxFormulaType.add(button);
	}
	
	private void createVarRadioButton(String VarName, final int ID) {
		JRadioButton button = new JRadioButton(VarName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.varID = ID;
			}
		});
		variablesButtons.add(button);
		hboxVariable.add(button);
	}
	
	public MainFrame() {
		super("Вычисление формул");
		//Window position and size
		Toolkit tools = Toolkit.getDefaultToolkit();
		
		setLocation((tools.getScreenSize().width - WIDTH) / 2, 
				(tools.getScreenSize().height - HEIGHT) / 2);
		
		setSize(520, 583);
		
		//get formulas images
		File file = new File("W:/Java projects/Lab2/images/formula1.bmp");
		try {
			formula1 = ImageIO.read(file);
		}
		catch(Exception e) {}

		file = new File("W:/Java projects/Lab2/images/formula2.bmp");
		try {
			formula2 = ImageIO.read(file);
		}
		catch(Exception e) {}
		formulaImage = formula1;
		
		//create formulas drawing
		hboxFormulaDraw.add(Box.createHorizontalGlue());
		FlowLayout flowLayout = (FlowLayout) formulaIlustration.getLayout();
		formulaIlustration.setPreferredSize(new Dimension(450, 100));
		formulaIlustration.setMaximumSize(new Dimension(450, 100));
		formulaIlustration.setAlignmentX(0.5f);
		hboxFormulaDraw.add(formulaIlustration);
		hboxFormulaDraw.add(Box.createHorizontalGlue());
		formulaIlustration.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		//create buttons lines
		hboxFormulaType.add(Box.createHorizontalGlue());
		createFormulaRadioButton("Формула 1", 1);
		createFormulaRadioButton("Формула 2", 2);
		formulasButtons.setSelected(
				formulasButtons.getElements().nextElement().getModel(), true);
		hboxFormulaType.add(Box.createHorizontalGlue());
		
		hboxVariable.add(Box.createHorizontalGlue());
		createVarRadioButton("X: ", 'x');
		createVarRadioButton("Y: ", 'y');
		createVarRadioButton("Z: ", 'z');
		variablesButtons.setSelected(
				variablesButtons.getElements().nextElement().getModel(), true);
		hboxVariable.add(Box.createHorizontalGlue());
		
		TextX.setMaximumSize(TextX.getPreferredSize());
		TextY.setMaximumSize(TextY.getPreferredSize());
		TextZ.setMaximumSize(TextZ.getPreferredSize());
		hboxVariablesImput.add(Box.createHorizontalGlue());
		hboxVariablesImput.add(new JLabel("X:"));
		hboxVariablesImput.add(TextX);
		hboxVariablesImput.add(Box.createHorizontalStrut(10));
		hboxVariablesImput.add(new JLabel("Y:"));
		hboxVariablesImput.add(TextY);
		hboxVariablesImput.add(Box.createHorizontalStrut(10));
		hboxVariablesImput.add(new JLabel("Z:"));
		hboxVariablesImput.add(TextZ);
		hboxVariablesImput.add(Box.createHorizontalGlue());
		
		
		
		JButton Mplus = new JButton("M+");
		JButton MC = new JButton("MC");
		
		Mplus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isMplus)
					isMplus = true;
				else
					isMplus = false;
			}
		});
		MC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearVariable(varID);	
			}
		});
		
		hboxMButtons.add(Box.createHorizontalGlue());
		hboxMButtons.add(MC);
		hboxMButtons.add(Box.createHorizontalStrut(20));
		hboxMButtons.add(Mplus);
		hboxMButtons.add(Box.createHorizontalGlue());
		
		
		
		JButton CalcButton = new JButton("Вычислить");
		CalcButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					x = Double.parseDouble(TextX.getText());
					y = Double.parseDouble(TextY.getText());
					z = Double.parseDouble(TextZ.getText());
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Неверный формат записи числа", 
							"Ошибка в записи числа", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					double answer = 0;
					
					String ind = "";
					if(isMplus) {
						ind = "    M";
						if(varID == 'x')
							answer = x;
						else if(varID == 'y')
							answer = y;
						else if(varID == 'z')
							answer = z;
					}
					
					if(formulaID == 1)
						answer += calc1();
					else if(formulaID == 2)
						answer += calc2();
					
					textResult.setText(Double.toString(answer) + ind);
				}
				catch(ArithmeticException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка выражения",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton ClearButton = new JButton("Очистить поля");
		ClearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllFields();
			}
		});
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		hboxCalcButtons.add(Box.createHorizontalGlue());
		hboxCalcButtons.add(CalcButton);
		hboxCalcButtons.add(Box.createHorizontalStrut(20));
		hboxCalcButtons.add(ClearButton);
		hboxCalcButtons.add(Box.createHorizontalGlue());
		
		
		textResult.setMaximumSize(textResult.getPreferredSize());
		textResult.setEditable(false);
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(new JLabel("Результат: "));
		hboxResult.add(textResult);
		hboxResult.add(Box.createHorizontalGlue());
		
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(hboxFormulaType);
		contentBox.add(hboxFormulaDraw);
		contentBox.add(hboxVariablesImput);
		contentBox.add(hboxVariable);
		contentBox.add(hboxMButtons);
		contentBox.add(hboxResult);
		contentBox.add(hboxCalcButtons);
		
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox);
	}
	
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		}

}

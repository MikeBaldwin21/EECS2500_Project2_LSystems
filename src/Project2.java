/*
 * Written by: Mike Baldwin
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project2 extends JFrame implements ActionListener {
	private CustomCanvas canvasPanel;
	private JTextField startTextField;
	private JTextArea rulesTextArea;
	private JTextField iterationsTextField;
	private JTextField angleTextField;
	private JCheckBox instantDrawCheckBox;
	private JSlider lineLengthSlider;

	public static void main(final String[] args) {
		new Project2();
	}

	private Project2() {
		setTitle("EECS2500 - Project 2 - L Systems");
		initUI();
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initUI() {
		//Construct the main panel that encompasses all
		JPanel mainPanel = new JPanel(new GridBagLayout());
		getContentPane().add(mainPanel);

		//Construct the menu panel that holds all the user configurable settings
		JPanel menuPanel = new JPanel(new GridBagLayout());
		menuPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 5)));

		//Construct and setup the canvas properties
		canvasPanel = new CustomCanvas();
		canvasPanel.setBackground(Color.white);
		canvasPanel.setPreferredSize(new Dimension(550, 550));
		canvasPanel.setBorder(new LineBorder(Color.black));

		//Main constraints object used
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		//START SYMBOL
		menuPanel.add(new JLabel("Start Symbol"), constraints);
		menuPanel.add((startTextField = new JTextField()), constraints);

		//RULES
		menuPanel.add(new JLabel("Rule"), constraints);
		rulesTextArea = new JTextArea(4, 10);
		rulesTextArea.setLineWrap(true);
		menuPanel.add(new JScrollPane(rulesTextArea), constraints);

		//ITERATIONS
		menuPanel.add(new JLabel("Iterations"), constraints);
		iterationsTextField = new JTextField();
		menuPanel.add(iterationsTextField, constraints);

		//ANGLE
		menuPanel.add(new JLabel("Angle"), constraints);
		angleTextField = new JTextField();
		menuPanel.add(angleTextField, constraints);

		//LINE LENGTH
		menuPanel.add(new JLabel("Line Length"), constraints);
		lineLengthSlider = new JSlider(1, 15);
		menuPanel.add(lineLengthSlider, constraints);

		//INSTANT DRAW
		menuPanel.add(new JLabel("Instant Draw"), constraints);
		instantDrawCheckBox = new JCheckBox();
		menuPanel.add(instantDrawCheckBox, constraints);

		//Draw Button
		JButton drawButton = new JButton("Draw");
		drawButton.addActionListener(this);
		menuPanel.add(drawButton, constraints);

		mainPanel.add(menuPanel);
		mainPanel.add(canvasPanel);
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		try {
			String start = startTextField.getText().replaceAll("\\s", "");
			String[] rules = rulesTextArea.getText().split("\\n");
			int iterations = Integer.parseInt(iterationsTextField.getText());
			int angle = Integer.parseInt(angleTextField.getText());
			int lineLength = lineLengthSlider.getValue();
			boolean instantDraw = instantDrawCheckBox.isSelected();
			canvasPanel.draw(new LSystem(start, rules, iterations).toString(), angle, lineLength, instantDraw);
		} catch (NumberFormatException exception) {
			JOptionPane.showMessageDialog(canvasPanel, "Incorrect Input.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}

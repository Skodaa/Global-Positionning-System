package ihm.gui;

import java.awt.Container; 
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.map.Case;
import engine.process.CalcManager;

public class MenuGUI extends JFrame{
	
	private final static Dimension preferredSize = new Dimension(500, 500);
	
	private JButton calculButton = new JButton("Calculer");
	
	private JTextField departFieldX = new JTextField();
	private JTextField departFieldY = new JTextField();
	private JTextField arriveeFieldX = new JTextField();
	private JTextField arriveeFieldY = new JTextField();
	
	private JCheckBox busBox = new JCheckBox("Bus");
	private JCheckBox trainBox = new JCheckBox("Train");
	private JCheckBox boatBox = new JCheckBox("Bateau");
	
	private JCheckBox shortBox = new JCheckBox("Plus court");
	private JCheckBox fastBox = new JCheckBox("Plus rapide");
	private JCheckBox comfortBox = new JCheckBox("Plus confortable");
	private JCheckBox touristicBox = new JCheckBox("Trajet Touristique");
	
	private int depart;
	private int arrivee;
	
	private CalcManager manager;
	
	public MenuGUI(String title, CalcManager manager) {
		
		super(title);
		
		this.manager = manager;
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setPreferredSize(preferredSize);
		
		JLabel departLabel = new JLabel("Point de départ :");
		add(departLabel);
		departLabel.setBounds(10,10,200,20);
		add(departFieldX);
		departFieldX.setBounds(210, 10, 50, 20);
		add(departFieldY);
		departFieldY.setBounds(275, 10, 50, 20);
		
		JLabel arriveeLabel = new JLabel("Point d'arrivée :");
		add(arriveeLabel);
		arriveeLabel.setBounds(10,40,200,20);
		add(arriveeFieldX);
		arriveeFieldX.setBounds(210, 40, 50, 20);
		add(arriveeFieldY);
		arriveeFieldY.setBounds(275, 40, 50, 20);
		
		JLabel filterLabel = new JLabel("Filtrer les moyens de transports :");
		add(filterLabel);
		filterLabel.setBounds(135,90, 200, 20);
		
		add(busBox);
		busBox.setBounds(30, 120, 150, 30);
		add(trainBox);
		trainBox.setBounds(200, 120, 150, 30);
		add(boatBox);
		boatBox.setBounds(370, 120, 150, 30);
		
		JLabel rideTypeLabel = new JLabel("Filtrer les moyens de transports :");
		add(rideTypeLabel);
		rideTypeLabel.setBounds(135, 170, 200, 20);
		
		add(shortBox);
		shortBox.setBounds(30, 180, 150, 30);
		add(fastBox);
		fastBox.setBounds(30, 210, 150, 30);
		add(comfortBox);
		shortBox.setBounds(30, 240, 150, 30);
		add(touristicBox);
		shortBox.setBounds(30, 270, 150, 30);
		
		add(calculButton);
		calculButton.setBounds(350, 400, 100, 40);
		calculButton.addActionListener(new saveOperations());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
	}
	
	private class saveOperations implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			manager.setX1(departFieldX.getText());
			manager.setY1(departFieldY.getText());
			manager.setX2(arriveeFieldX.getText());
			manager.setY2(arriveeFieldX.getText());
			
			dispose();
		}
		
	}
}

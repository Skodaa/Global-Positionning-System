package ihm.gui;

import java.awt.BorderLayout; 
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileSystemView;

import engine.config.GPSConfiguration;
import engine.item.Lieu;
import engine.map.Map;
import engine.process.CalcManager;
import engine.process.GPSBuilder;


public class MainGUI extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private final static Dimension preferredSize = new Dimension(GPSConfiguration.WINDOW_WIDTH, GPSConfiguration.WINDOW_HEIGHT);
	private final static Dimension fieldMenuSize = new Dimension(60,20);
	
	private Container contentPane;

	private Map map;
	private DisplayMap carte;
	
	private CalcManager manager;
	private CalcManager cm = new CalcManager(map);
	private ArrayList<Lieu> lieux = cm.getLieu();
	private Lieu start;
	private Lieu finish;
	
	private JLabel distanceJLabel = new JLabel("");
	
	
	
	
	public MainGUI(String title) {
		super(title);
		init();
	}
	
	private void init() {
		
		createMenuBar();
		
		try {
			map = GPSBuilder.buildMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		manager = GPSBuilder.buildInit(map);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(GPSConfiguration.WINDOW_WIDTH, GPSConfiguration.WINDOW_HEIGHT));
		
		carte = new DisplayMap(map,manager);
		carte.setPreferredSize(preferredSize);
		contentPane.add(carte,BorderLayout.CENTER);
		
		contentPane.add(distanceJLabel,BorderLayout.SOUTH);
		distanceJLabel.setVisible(false);
		
		pack();
		setPreferredSize(preferredSize);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.addMenuListener(new fileOperations());
		
		JMenu itineraireMenu = new JMenu("Paramètres itinéraire");
		
		JMenu startSubMenu = new JMenu("Départ/Arrivée");
		JMenu startLabel = new JMenu("Point de départ :");
		JTextField startFieldX = new JTextField();
		startFieldX.setPreferredSize(fieldMenuSize);
		JTextField startFieldY = new JTextField();
		startFieldY.setPreferredSize(fieldMenuSize);
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(int i = 0; i < lieux.size(); i++) {
			model.add(i,lieux.get(i).getName());
		}
		
		JList<String> startL = new JList<String>(model);
		startL.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String SelectedS = startL.getSelectedValue();
				for(Lieu lieu : lieux) {
					if(lieu.getName()== SelectedS) {
						
						String xs = lieu.getX();
						String ys = lieu.getY();
						startFieldX.setText(xs);
						startFieldY.setText(ys);
					}
				}
			}
		});
	
		JMenu finishLabel = new JMenu("Point d'arrivée :");
		JTextField finishFieldX = new JTextField();
		finishFieldX.setPreferredSize(fieldMenuSize);
		JTextField finishFieldY = new JTextField();
		finishFieldY.setPreferredSize(fieldMenuSize);
		
		
		JList<String> endL = new JList<String>(model);
		endL.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String SelectedE = endL.getSelectedValue();
				for(Lieu lieu2 : lieux) {
					if(lieu2.getName()== SelectedE) {
						
						String xe = lieu2.getX();
						String ye = lieu2.getY();
						finishFieldX.setText(xe);
						finishFieldY.setText(ye);
					}
				}
			}
		});

		JMenu transportSubMenu = new JMenu("Type de transport");
		JCheckBoxMenuItem busBox = new JCheckBoxMenuItem("Bus");
		JCheckBoxMenuItem trainBox = new JCheckBoxMenuItem("Train");
		JCheckBoxMenuItem boatBox = new JCheckBoxMenuItem("Bateau");
		
		JMenu tripSubMenu = new JMenu("Type de trajet");
		JCheckBoxMenuItem shortBox = new JCheckBoxMenuItem("Plus Court");
		JCheckBoxMenuItem longBox = new JCheckBoxMenuItem("Plus Rapide");
		JCheckBoxMenuItem confortBox = new JCheckBoxMenuItem("Plus confortable");
		JCheckBoxMenuItem touristicBox = new JCheckBoxMenuItem("Trajet touristique");
		
		
		JButton saveButton = new JButton("Enregistrer");
		saveButton.addActionListener(new saveOperations(startFieldX,startFieldY,finishFieldX,finishFieldY,distanceJLabel));
		
		startSubMenu.add(startLabel);
		startLabel.add(startL);
		finishLabel.add(endL);
		startSubMenu.add(startFieldX);
		startSubMenu.add(startFieldY);
		startSubMenu.add(finishLabel);
		startSubMenu.add(finishFieldX);
		startSubMenu.add(finishFieldY);
		itineraireMenu.add(startSubMenu);
		
		transportSubMenu.add(busBox);
		transportSubMenu.add(trainBox);
		transportSubMenu.add(boatBox);
		itineraireMenu.add(transportSubMenu);
		
		tripSubMenu.add(shortBox);
		tripSubMenu.add(longBox);
		tripSubMenu.add(confortBox);
		tripSubMenu.add(touristicBox);
		itineraireMenu.add(tripSubMenu);
		
		itineraireMenu.addSeparator();
		itineraireMenu.add(saveButton);
		
		menuBar.add(itineraireMenu);
		
		setJMenuBar(menuBar);
		
	}
	
	private class fileOperations implements MenuListener {

		@Override
		public void menuSelected(MenuEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
			fileChooser.setDialogTitle("Choisissez un fichier");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			File fichier = null;
			if(fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				fichier = fileChooser.getSelectedFile();
			}
			System.out.println("Voici le fichier choisi : " + fichier.getAbsolutePath());
			
			// Faire la fonctionnalité de mise a jour de la carte
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class saveOperations implements ActionListener {
		private JTextField x1;
		private JTextField y1;
		private JTextField x2;
		private JTextField y2;
		private JLabel distanceJLabel;
		
		public saveOperations(JTextField startFieldX, JTextField startFieldY, JTextField finishFieldX, JTextField finishFieldY, JLabel distanceJLabel) {
			this.x1 = startFieldX;
			this.y1 = startFieldY;
			this.x2 = finishFieldX;
			this.y2 = finishFieldY;
			this.distanceJLabel = distanceJLabel;
		}

		public void actionPerformed(ActionEvent e) {
			manager.setX1(x1.getText());
			manager.setY1(y1.getText());
			manager.setX2(x2.getText());
			manager.setY2(y2.getText());
			manager.calcTempTraject(true);
			distanceJLabel.setText("Distance : " + Integer.toString(manager.getDistance()*100) + "m" + "Temps : " + String.valueOf(manager.getTemp()) + "minutes");
			distanceJLabel.setVisible(true);
			contentPane.remove(carte);
			carte = new DisplayMap(map,manager);
			contentPane.add(carte,BorderLayout.CENTER);
			carte.revalidate();
			carte.repaint();
			contentPane.validate();
			contentPane.repaint();
		
		}
	}
	
	private class itineraireOperations implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MenuGUI menu = new MenuGUI("Menu Itinéraire", manager);
		}
	}
	
	public void run() {
		carte.repaint();
	}

}

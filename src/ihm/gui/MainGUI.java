package ihm.gui;

import java.awt.BorderLayout;  
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileSystemView;

import engine.config.GPSConfiguration;
import engine.exception.WrongParametersException;
import engine.item.Lieu;
import engine.map.Case;
import engine.map.Map;
import engine.process.CSVReader;
import engine.process.CalcManager;
import engine.process.GPSBuilder;


public class MainGUI extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private final static Dimension preferredSize = new Dimension(GPSConfiguration.WINDOW_WIDTH, GPSConfiguration.WINDOW_HEIGHT);
	private final static Dimension fieldMenuSize = new Dimension(60,20);
	
	private Container contentPane;

	private Map map;
	private DisplayMap carte;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu itineraireMenu;
	private JMenu startSubMenu;
	private JMenu startLabel;
	private JTextField startFieldX;
	private JTextField startFieldY;
	private JMenu finishLabel;
	private JTextField finishFieldX;
	private JTextField finishFieldY;
	private JMenu transportSubMenu;
	
	private CalcManager manager = new CalcManager(map);
	private CSVReader csv = new CSVReader(GPSConfiguration.LIEU_CSV);
	private ArrayList<Lieu> lieux = csv.getLieu();
	private DefaultListModel<String> model;
	private JList<String> startL;
	private JList<String> endL;
	private JCheckBoxMenuItem busBox;
	private JCheckBoxMenuItem trainBox; 
	private JCheckBoxMenuItem boatBox; 
	private JMenu tripSubMenu;
	private JRadioButtonMenuItem shortButton;
	private JRadioButtonMenuItem longButton;
	private JRadioButtonMenuItem confortButton;
	private JRadioButtonMenuItem touristicButton;
	private ButtonGroup traject;
	private JButton saveButton;
	private JLabel distanceJLabel = new JLabel("");
	private Case start;
	private Case finish;
	private boolean selected = true;
	
	
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
		
		MouseControls mouseControls = new MouseControls();
		carte.addMouseListener(mouseControls);
		
		pack();
		setPreferredSize(preferredSize);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenuBar() {
menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.addMenuListener(new fileOperations());
		
		itineraireMenu = new JMenu("Paramètres itinéraire");
		
		startSubMenu = new JMenu("Départ/Arrivée");
		startLabel = new JMenu("Point de départ :");
		startFieldX = new JTextField();
		startFieldX.setPreferredSize(fieldMenuSize);
		startFieldY = new JTextField();
		startFieldY.setPreferredSize(fieldMenuSize);
		model = new DefaultListModel<String>();
		for(int i = 0; i < lieux.size(); i++) {
			model.add(i,lieux.get(i).getName());
		}
		
		startL = new JList<String>(model);
		startL.addListSelectionListener(new ListSelectionOperation(1));
		
	
		finishLabel = new JMenu("Point d'arrivée :");
		finishFieldX = new JTextField();
		finishFieldX.setPreferredSize(fieldMenuSize);
		finishFieldY = new JTextField();
		finishFieldY.setPreferredSize(fieldMenuSize);
		
		
		endL = new JList<String>(model);
		endL.addListSelectionListener(new ListSelectionOperation(0));
		

		transportSubMenu = new JMenu("Type de transport");
		busBox = new JCheckBoxMenuItem("Bus");
		trainBox = new JCheckBoxMenuItem("Train");
		boatBox = new JCheckBoxMenuItem("Bateau");
		
		
		tripSubMenu = new JMenu("Type de trajet");
		shortButton = new JRadioButtonMenuItem("Plus Court",selected);
		longButton = new JRadioButtonMenuItem("Plus Rapide");
		confortButton = new JRadioButtonMenuItem("Plus confortable");
		touristicButton = new JRadioButtonMenuItem("Trajet touristique");
		traject = new ButtonGroup();
		
		
		
		saveButton = new JButton("Enregistrer");
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
		
		traject.add(shortButton);
		traject.add(longButton);
		traject.add(confortButton);
		traject.add(touristicButton);
		tripSubMenu.add(shortButton);
		tripSubMenu.add(longButton);
		tripSubMenu.add(confortButton);
		tripSubMenu.add(touristicButton);
		
		
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
	
	private class ListSelectionOperation implements ListSelectionListener{
		private int type;
		
		
		public ListSelectionOperation(int type) {
			this.type = type;
		}
		
		public void valueChanged(ListSelectionEvent e) {
			
			if(type == 1) {
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
			else {
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
			if((x1.getText()!="")&&(y1.getText()!="")&&(x2.getText()!="")&&(y2.getText()!="")) {
				start = new Case(Integer.valueOf(x1.getText()),Integer.valueOf(y1.getText()));
				finish = new Case(Integer.valueOf(x2.getText()),Integer.valueOf(y2.getText()));
			}
			manager.setStart(start);
			manager.setFinish(finish);
			
			boolean selectedT = trainBox.isSelected();
			boolean selectedTC = shortButton.isSelected();
			boolean selectedTR = longButton.isSelected();
			
			try {
		        
				if(selectedTC) {
					if (selectedT) {
			        	manager.calcLowerTraject(true);
			        } else {
			        	manager.calcLowerTraject(false);
			        }
				}
				if(selectedTR) {
					if (selectedT) {
			        	manager.calcTempTraject(true);
			        } else {
			        	manager.calcTempTraject(false);
			        }
				}
				
				distanceJLabel.setText("Distance : " + Integer.toString(manager.getDistance()*100) + "m" + "    Temps : " + String.valueOf(manager.getTemp()) + "minutes");
				distanceJLabel.setVisible(true);
				start = finish = null;
				finishFieldX.setText("");
				finishFieldY.setText("");
				startFieldX.setText("");
				startFieldY.setText("");
				startL.clearSelection();
				endL.clearSelection();
				contentPane.remove(carte);
				carte = new DisplayMap(map,manager);
				contentPane.add(carte,BorderLayout.CENTER);
				carte.revalidate();
				carte.repaint();
				MouseControls mouseControls = new MouseControls();
				carte.addMouseListener(mouseControls);
				contentPane.validate();
				contentPane.repaint();
				manager.resetStartEnd();
			} catch (WrongParametersException e1) {
				System.err.println(e1.getMessage());
				JOptionPane.showMessageDialog(contentPane, "Erreur selection point de départ/arrivée");
			}
		}
	}
	
	private class MouseControls implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.println("non");

			Case position = new Case(y/GPSConfiguration.BLOCK_SIZE,x/GPSConfiguration.BLOCK_SIZE);
			if(start != null) {
				finish = position;
				finishFieldX.setText(Integer.toString(finish.getLigne()));
				finishFieldY.setText(Integer.toString(finish.getColonne()));
				System.out.println("finish");
			}
			else{
				start = position;
				startFieldX.setText(Integer.toString(start.getLigne()));
				startFieldY.setText(Integer.toString(start.getColonne()));
				System.out.println("depart");
			}
			try {
				manager.setStartFinishPoint(position);
			}catch(WrongParametersException e1) {
				System.err.println(e1.getMessage());
			}
			// faire une ligne de commande pour colorier la case sélectionnée
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}
	
	public void run() {
		carte.repaint();
	}

}

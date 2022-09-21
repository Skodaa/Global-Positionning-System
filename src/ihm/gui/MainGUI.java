package ihm.gui;

import java.awt.BorderLayout;    
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import engine.config.GPSConfiguration;
import engine.exception.TrajetNotFoundException;
import engine.exception.WrongParametersException;
import engine.item.Lieu;
import engine.map.Carte;
import engine.map.Case;
import engine.process.CSVReader;
import engine.process.CalculeTrajetManager;
import engine.process.GPSBuilder;


public class MainGUI extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private final static Dimension preferredSize = new Dimension(GPSConfiguration.WINDOW_WIDTH, GPSConfiguration.WINDOW_HEIGHT);
	private final static Dimension fieldMenuSize = new Dimension(60,20);
	
	private Container contentPane;

	private Carte map;
	private DisplayMap dCarte;
	private JPanel infoPanel;
	private DisplayHelp helpPanel;
	
	private JMenuBar menuBar;
	private JMenu itineraireMenu;
	private JMenu startSubMenu;
	private JMenu startLabel;
	private JTextField startFieldX;
	private JTextField startFieldY;
	private JMenu finishLabel;
	private JTextField finishFieldX;
	private JTextField finishFieldY;
	private JMenu transportSubMenu;
	
	private JTextField x1;
	private JTextField y1;
	private JTextField x2;
	private JTextField y2;
	private JLabel departLabel = new JLabel();
	private JLabel arriveeLabel = new JLabel();
	private JLabel distanceLabel = new JLabel();
	private JLabel tempsLabel = new JLabel();
	
	private CalculeTrajetManager manager = new CalculeTrajetManager(map);
	private CSVReader csv = new CSVReader();
	private ArrayList<Lieu> lieux = csv.getAllLieu();
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(GPSConfiguration.WINDOW_WIDTH, GPSConfiguration.WINDOW_HEIGHT));
		
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
		
		JLayeredPane lp = getLayeredPane();
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		dCarte = new DisplayMap(map,manager);
		dCarte.setSize(preferredSize);
		contentPane.add(dCarte,BorderLayout.CENTER);
		
		infoPanel = new JPanel();
		infoPanel.add(departLabel);
		infoPanel.add(arriveeLabel);
		infoPanel.add(distanceLabel);
		infoPanel.add(tempsLabel);
		infoPanel.setSize(200,80);
		infoPanel.setLocation(1080,23);
		infoPanel.setVisible(false);
		lp.add(infoPanel,Integer.valueOf(2));
		
		helpPanel = new DisplayHelp();
		helpPanel.setSize(350,400);
		helpPanel.setLocation(0,23);
		helpPanel.setVisible(false);
		lp.add(helpPanel,Integer.valueOf(2));
		
		MouseControls mouseControls = new MouseControls();
		dCarte.addMouseListener(mouseControls);
		
		pack();
		setPreferredSize(preferredSize);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		
		
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
		shortButton = new JRadioButtonMenuItem("Plus Court");
		longButton = new JRadioButtonMenuItem("Plus Rapide",selected);
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
		
		menuBar.add(Box.createHorizontalGlue());
		JMenu helpMenu = new JMenu("aide et légendes");
		helpMenu.addMenuListener(new helpOperations2());
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
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
		
		public saveOperations(JTextField startFieldX, JTextField startFieldY, JTextField finishFieldX, JTextField finishFieldY, JLabel distanceJLabel) {
			x1 = startFieldX;
			y1 = startFieldY;
			x2 = finishFieldX;
			y2 = finishFieldY;
		}

		public void actionPerformed(ActionEvent e) {
			if((!x1.getText().isEmpty())&&(!y1.getText().isEmpty())&&(!x2.getText().isEmpty())&&(!y2.getText().isEmpty())) {
				start = new Case(Integer.valueOf(x1.getText()),Integer.valueOf(y1.getText()));
				finish = new Case(Integer.valueOf(x2.getText()),Integer.valueOf(y2.getText()));

			manager.setStart(start);
			manager.setFinish(finish);
			
			boolean selectedT = trainBox.isSelected();
			boolean selectedB = boatBox.isSelected();
			boolean selectedBu = busBox.isSelected();
			boolean selectedTC = shortButton.isSelected();
			boolean selectedTR = longButton.isSelected();
			boolean selectedTCom = confortButton.isSelected();
			boolean selectedTour = touristicButton.isSelected();
			
			try {
		        
				if(selectedTC) {
					if (selectedT) {
						if(selectedB) {
							if(selectedBu) {
								manager.trajet(true,true,true,"Distance");
							}
							else {
								manager.trajet(true,true,false,"Distance");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(true,false,true,"Distance");
							}
							else {
								manager.trajet(true,false,false,"Distance");
							}
						}
			        	
			        } 
					else {
						if(selectedB) {
							if(selectedBu) {
								manager.trajet(false,true,true,"Distance");
							}
							else {
								manager.trajet(false,true,false,"Distance");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(false,false,true,"Distance");
							}
							else {
								manager.trajet(false,false,false,"Distance");
							}
						}
			        }
				}
				if(selectedTR) {
					if (selectedT) {
						if(selectedB) {
							if(selectedBu) {
								manager.trajet(true,true,true,"Rapide");
							}
							else {
								manager.trajet(true,true,false,"Rapide");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(true,false,true,"Rapide");
							}
							else {
								manager.trajet(true,false,false,"Rapide");
							}
						}
			        	
			        } else {
			        	if(selectedB) {
			        		if(selectedBu) {
								manager.trajet(false,true,true,"Rapide");
							}
							else {
								manager.trajet(false,true,false,"Rapide");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(false,false,true,"Rapide");
							}
							else {
								manager.trajet(false,false,false,"Rapide");
							}
						}
			        }
				}
				if(selectedTCom) {
					if (selectedT) {
						if(selectedB) {
							if(selectedBu) {
								manager.trajet(true,true,true,"Comfort");
							}
							else {
								manager.trajet(true,true,false,"Comfort");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(true,false,true,"Comfort");
							}
							else {
								manager.trajet(true,false,false,"Comfort");
							}
						}
			        	
			        } 
					else {
			        	if(selectedB) {
			        		if(selectedBu) {
								manager.trajet(false,true,true,"Comfort");
							}
							else {
								manager.trajet(false,true,false,"Comfort");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(false,false,true,"Comfort");
							}
							else {
								manager.trajet(false,false,false,"Comfort");
							}
						}
			        }
				}
				if(selectedTour) {
					if (selectedT) {
						if(selectedB) {
							if(selectedBu) {
								manager.trajet(true,true,true,"Touristique");
							}
							else {
								manager.trajet(true,true,false,"Touristique");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(true,false,true,"Touristique");
							}
							else {
								manager.trajet(true,false,false,"Touristique");
							}
						}
			        	
			        } 
					else {
			        	if(selectedB) {
			        		if(selectedBu) {
								manager.trajet(false,true,true,"Touristique");
							}
							else {
								manager.trajet(false,true,false,"Touristique");
							}
						}
						else {
							if(selectedBu) {
								manager.trajet(false,false,true,"Touristique");
							}
							else {
								manager.trajet(false,false,false,"Touristique");
							}
						}
			        }
				}
			
				departLabel.setText("Départ : " + startFieldX.getText() + "," + startFieldY.getText() + " |");
				arriveeLabel.setText("Arrivée : " + finishFieldX.getText() + "," + finishFieldY.getText());
				distanceLabel.setText("Distance : " + Integer.toString(manager.getDistance()*100) + "m");
				tempsLabel.setText("Temps : " + String.valueOf(manager.getTemp()) + "minutes");
				infoPanel.setVisible(true);
				start = finish = null;
				finishFieldX.setText("");
				finishFieldY.setText("");
				startFieldX.setText("");
				startFieldY.setText("");
				startL.clearSelection();
				endL.clearSelection();
				contentPane.remove(dCarte);
				dCarte = new DisplayMap(map,manager);
				contentPane.add(dCarte,BorderLayout.CENTER);
				dCarte.revalidate();
				dCarte.repaint();
				MouseControls mouseControls = new MouseControls();
				dCarte.addMouseListener(mouseControls);
				contentPane.validate();
				contentPane.repaint();
				manager.resetStartEnd();
			} catch (WrongParametersException e1) {
				System.err.println(e1.getMessage());
				JOptionPane.showMessageDialog(contentPane, "Erreur selection point de départ/arrivée");
			} catch(TrajetNotFoundException e2) {
				System.err.println(e2.getMessage());
				JOptionPane.showMessageDialog(contentPane, "Erreur : Trajet impossible");
			}
		}else if(((x1.getText().isEmpty()) && (y1.getText().isEmpty()))&&((x2.getText().isEmpty())&&(y2.getText().isEmpty()))) {
			JOptionPane.showMessageDialog(contentPane,"Aucun point de départ et d'arrivée sélectionné");

		}else if((x1.getText().isEmpty()) && (y1.getText().isEmpty())) {
			JOptionPane.showMessageDialog(contentPane,"Point de départ non selectionné");

		}else {
			JOptionPane.showMessageDialog(contentPane,"Point d'arrivée non selectionné");
		}
		}
	}
	
	private class helpOperations2 implements MenuListener {

		@Override
		public void menuSelected(MenuEvent e) {
			if(helpPanel.isVisible()) {
				helpPanel.setVisible(false);
			}else {
				helpPanel.setVisible(true);
			}
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
	
	private class MouseControls implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			Case position = map.getCase(x/GPSConfiguration.BLOCK_SIZE,y/GPSConfiguration.BLOCK_SIZE);
		
			if(position.contientRoute() || position.contientPort() || position.contientStationBus() || position.contientStationTrain()) {
				
				Case position2 = new Case(y/GPSConfiguration.BLOCK_SIZE,x/GPSConfiguration.BLOCK_SIZE);
				
				if(start != null) {
						finish = position2;
						finishFieldX.setText(Integer.toString(finish.getLigne()));
						finishFieldY.setText(Integer.toString(finish.getColonne()));
						System.out.println("finish");

				}
				else{
					start = position2;
					startFieldX.setText(Integer.toString(start.getLigne()));
					startFieldY.setText(Integer.toString(start.getColonne()));
					System.out.println("depart");
				}
			}else {
				JOptionPane.showMessageDialog(contentPane,"Veuillez selectionnez uniquement une route, un arret de bus, une gare ou un port");
			}
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
		dCarte.repaint();
	}

}

package ihm.gui;

import java.awt.Graphics; 
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DisplayHelp extends JPanel{

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		try {
            Image img = ImageIO.read(new File("src/ihm/images/legende.PNG"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur image de fond: " +e.getMessage());
       }
	}

}

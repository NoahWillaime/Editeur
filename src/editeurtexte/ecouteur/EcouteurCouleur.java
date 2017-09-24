package editeurtexte.ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import editeurtexte.modele.ModeleT;

public class EcouteurCouleur implements ActionListener {
	private Observable o;
	private String colorname;
	
	public EcouteurCouleur(Observable o, String colorname) {
		this.o = o;
		this.colorname = colorname;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		((ModeleT)o).setColor(colorname);
	}

}

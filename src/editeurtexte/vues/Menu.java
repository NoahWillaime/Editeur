package editeurtexte.vues;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import editeurtexte.ecouteur.EcouteurAjouter;
import editeurtexte.ecouteur.EcouteurCouleur;
import editeurtexte.modele.ModeleT;

public class Menu extends JMenuBar implements Observer {
	private JMenu style;
	private ArrayList<JMenuItem> jmi;
	private JMenuItem ajouter;
	
	public Menu(Observable o) {
		super();
		this.jmi = new ArrayList<JMenuItem>();
		this.style = new JMenu("Style");
		this.style.setMnemonic(KeyEvent.VK_B);
		this.ajouter = new JMenuItem("Ajouter");
		this.ajouter.addActionListener(new EcouteurAjouter((ModeleT)o));
		this.style.add(ajouter);
		this.add(style);
		o.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg1) {
		if(((ModeleT)o).getSizeHM() > jmi.size()) {
			String namec = ((ModeleT)o).getLastColor();
			this.jmi.add(new JMenuItem(namec));
			int index = jmi.size()-1;
			jmi.get(index).addActionListener(new EcouteurCouleur(o, namec));
			this.style.add(jmi.get(index));
		}
	}

}

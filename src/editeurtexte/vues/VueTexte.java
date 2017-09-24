package editeurtexte.vues;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import editeurtexte.modele.ModeleT;

public class VueTexte extends JPanel implements Observer {
	private JTextArea jta;
	
	public VueTexte(Observable o) {
		super();
		jta = new JTextArea();
		jta.setEditable(false);
		jta.setPreferredSize(new Dimension(300, 300));
		this.add(jta);
		o.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String s = o.toString();
		jta.setText(s);
		jta.setForeground(((ModeleT)o).getApplyColor());
	}
}

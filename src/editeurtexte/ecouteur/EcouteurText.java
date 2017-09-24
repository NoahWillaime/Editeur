package editeurtexte.ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JTextField;

import editeurtexte.modele.ModeleT;

public class EcouteurText implements ActionListener {
	private Observable o;
	private JTextField jtf;
	
	public EcouteurText(Observable o, JTextField jtf){
		this.o = o;
		this.jtf = jtf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		((ModeleT)o).addLine(jtf.getText());
	}

}

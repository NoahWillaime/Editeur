package editeurtexte.vues;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import editeurtexte.ecouteur.EcouteurText;

public class PanneauControle extends JPanel implements Observer {
	private JTextField jtf;
	private JButton jb;
	
	public PanneauControle(Observable o) {
		super();
		this.setLayout(new BorderLayout());
		jtf = new JTextField();
		jb = new JButton("Valider");
		jb.addActionListener(new EcouteurText(o, jtf));
		this.add(jtf, BorderLayout.CENTER);
		this.add(jb, BorderLayout.EAST);
		o.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		jtf.setText("");
	}

}

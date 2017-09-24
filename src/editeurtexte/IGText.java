package editeurtexte;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import editeurtexte.modele.ModeleT;
import editeurtexte.vues.Menu;
import editeurtexte.vues.PanneauControle;
import editeurtexte.vues.VueTexte;

public class IGText extends JFrame{
	public IGText() {
		super("IText");
		ModeleT m = new ModeleT();
		VueTexte vt = new VueTexte(m);
		this.add(vt, BorderLayout.CENTER);
		PanneauControle p = new PanneauControle(m);
		Menu me = new Menu(m);
		this.add(me, BorderLayout.NORTH);
		this.add(p,  BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 400));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new IGText();
	}
}

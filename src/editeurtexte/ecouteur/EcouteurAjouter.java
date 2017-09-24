package editeurtexte.ecouteur;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import editeurtexte.modele.ModeleT;

public class EcouteurAjouter implements ActionListener {
	private ModeleT o;
	private JFileChooser jf;
		
	public EcouteurAjouter(ModeleT o) {
		this.o = o;
		jf = new JFileChooser();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Color c = JColorChooser.showDialog(null, "Choix de la nouvelle couleur à ajouter", Color.RED);
		jf.addChoosableFileFilter(new FileNameExtensionFilter("*.jar", "jar"));
		int reponse = jf.showOpenDialog((Component)e.getSource());
		if (reponse == JFileChooser.APPROVE_OPTION){
			File fichier = jf.getSelectedFile();
			System.out.println(fichier.getAbsolutePath());
			String classe = JOptionPane.showInputDialog("Nom de la class ?");
			this.o.addColor(fichier, classe);
			//o.addColor(classe);		
		}
	}

}

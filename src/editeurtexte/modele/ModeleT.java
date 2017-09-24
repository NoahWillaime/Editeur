package editeurtexte.modele;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import editeurtexte.Itexte;

public class ModeleT extends Observable implements Itexte {
	private ArrayList<String> text;
	private Map<String, Color> hm ;
	private Boolean ajouterC;
	private Boolean changeC;
	private String LastColor;
	private Color ColorApply;
	
	public ModeleT() {
		this.text = new ArrayList<String>();
		this.hm = new HashMap<>();
		this.ajouterC = false;
		this.changeC = false;
	}
	
	@Override
	public void addLine(String ligne) {
		this.text.add(ligne);
		setChanged();
		this.notifyObservers();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLine(int index) {
		return text.get(index);
	}

	@Override
	public int getLineCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLine(int i, String ligne) {
		// TODO Auto-generated method stub
	
	}
	
	public Color getColor(String name) {
		return hm.get(name);
	}
	
	public String getLastColor() {
		return LastColor;
	}
	
	public Boolean getAddColor() {
		return ajouterC;
	}
	
	public Boolean getChangeColor() {
		return changeC;
	}
	
	public Color getApplyColor() {
		return ColorApply;
	}
	
	public void setColor(String name) {
		changeC = true;
		ColorApply = getColor(name);
		setChanged();
		this.notifyObservers();
		changeC = false;
	}
	
	public void classColor(Class<?> c) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<?> co = c.getConstructor();
		Method m_color = c.getMethod("getColor");
		Method m_name = c.getMethod("getName");
		Object o = co.newInstance();
		String nam = (String)m_name.invoke(o);
		Color col = (Color)m_color.invoke(o);
		hm.put(nam, col);
		LastColor = nam;
		ajouterC = true;
		setChanged();
		this.notifyObservers();
		ajouterC = false;
	}
	
	public void addColor(File fichier, String classe){
		try {
			URLClassLoader loader = new URLClassLoader(new URL[]{
				new URL("file:///"+fichier.getAbsolutePath()) });
			Class<?> c = loader.loadClass(classe);
			classColor(c);
			loader.close();
		} catch (ClassNotFoundException
				| NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) {
			System.out.println("Erreur!");
		}
	}
	
	public void addColor(String name, Color c) {
		ajouterC = true;
		LastColor = name;
		hm.put(name, c);
		setChanged();
		this.notifyObservers();
		ajouterC = false;
	}
	
	public void addColor(String classe){
			try {
				Class<?> c = Class.forName(classe);
				classColor(c);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				System.out.println("Class non existante!");
			}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (String s : text) {
			sb.append(s+"\n");
		}
		return sb.toString();
	}
}

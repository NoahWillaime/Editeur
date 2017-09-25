package editeurtexte.modele;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import editeurtexte.Itexte;

public class ModeleT extends Observable implements Itexte, Iterable<String>{
	private Preferences color_pref;
	private ArrayList<String> text;
	private Map<String, Color> hm ;
	private String LastColor;
	private Color ColorApply;
	private int size;
	private int nb_line;
	
	public ModeleT() {
		this.color_pref = Preferences.userRoot().node(this.getClass().getName());
		this.text = new ArrayList<String>();
		this.hm = new HashMap<>();
		this.hm.put("Bleu", Color.BLUE);
		this.hm.put("Rouge", Color.RED);
		this.size = 0;
		this.nb_line = 0;
		try {
			String[] mem = this.color_pref.keys();
			for (String s : mem) {
				Color c = new Color(color_pref.getInt(s, 0));
				this.hm.put(s, c);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addLine(String ligne) {
		this.text.add(ligne);
		this.size += ligne.length();
		this.nb_line += 1;
		setChanged();
		this.notifyObservers();
	}

	@Override
	public void clear() {
		text.clear();
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public String getLine(int index) {
		assert(index >= 0 && index < text.size()):"Index non compris";
		return text.get(index);
	}

	@Override
	public int getLineCount() {
		return this.nb_line;
	}

	@Override
	public void setLine(int i, String ligne) {
		assert(i >= 0 && i < text.size()):"i non compris dans l'index";
		this.text.remove(i);
		this.text.add(i, ligne);
	}
	
	public Color getColor(String name) {
		return hm.get(name);
	}
	
	public String getLastColor() {
		return LastColor;
	}
	
	public Color getApplyColor() {
		return ColorApply;
	}
	
	public int getSizeHM() {
		return hm.size();
	}
	
	public void setColor(String name) {
		ColorApply = getColor(name);
		setChanged();
		this.notifyObservers();
	}
	
	public void classColor(Class<?> c) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<?> co = c.getConstructor();
		Method m_color = c.getMethod("getColor");
		Method m_name = c.getMethod("getName");
		Object o = co.newInstance();
		String nam = (String)m_name.invoke(o);
		Color col = (Color)m_color.invoke(o);
		this.color_pref.putInt(nam, col.getRGB());
		hm.put(nam, col);
		LastColor = nam;
		setChanged();
		this.notifyObservers();
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
		LastColor = name;
		hm.put(name, c);
		setChanged();
		this.notifyObservers();
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
	
	public Iterator<String> iterator(){
		Set<String> s = hm.keySet();
		return s.iterator();
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

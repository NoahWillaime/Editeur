package styles;

import java.awt.Color;

public class StyleJaune {
	private Color c_name;
	private String s_name;
	
	public StyleJaune(){
		c_name = Color.yellow;
		s_name = "Jaune";
	}
	
	public void setColor(Color c){
		this.c_name = c;
	}
	
	public void setName(String name){
		this.s_name = name;
	}
	
	public Color getColor(){
		return c_name;
	}
	
	public String getName(){
		return s_name;
	}
}

package styles;

import java.awt.Color;

public class StyleVert {
	private Color c_name;
	private String s_name;
	
	public StyleVert(){
		c_name = Color.green;
		s_name = "Vert";
	}
	
	public void setColor(Color c){
		c_name = c;
	}
	
	public void setName(String name){
		s_name = name;
	}
	
	public Color getColor(){
		return c_name;
	}
	
	public String getName(){
		return s_name;
	}
}

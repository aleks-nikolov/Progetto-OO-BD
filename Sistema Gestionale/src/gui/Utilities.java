package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

public class Utilities {
	
	//Font
	Font arial = new Font("Arial", Font.PLAIN, 14);
	
	Font arialXS = new Font("Arial Black", Font.PLAIN, 12);
	Font arialS = new Font("Arial Black", Font.PLAIN, 14);
	Font arialM = new Font("Arial Black", Font.PLAIN, 16);
	Font arialL = new Font("Arial Black", Font.PLAIN, 20);
	
	
	//Colori
	Color bg = new Color(220, 220, 220);
	Color fg = new Color(50, 50, 50);
	
	Color black = new Color(0, 0, 0);
	Color white = new Color(255, 255, 255);
	Color greenBtn = new Color(60, 179, 113);
	Color redBtn = new Color(178, 34, 34);
	Color blueBtn = new Color(100, 149, 237);
	
//************************************************************************************
	
	public void changeFont (Component component, Font font) {
	
	    component.setFont (font);
	    
	    if(component instanceof Container)
	    {
	        for (Component child : ((Container) component).getComponents ())
	        {
	            changeFont(child, font);
	        }
	    }
	    
	}
	
}
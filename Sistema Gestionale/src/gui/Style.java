package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Style {
	
	//Font
	Font arial = new Font("Arial", Font.PLAIN, 14);
	
	Font defaultXS = new Font("Arial Black", Font.PLAIN, 12);
	Font defaultS = new Font("Arial Black", Font.PLAIN, 14);
	Font defaultM = new Font("Arial Black", Font.PLAIN, 16);
	Font defaultL = new Font("Arial Black", Font.PLAIN, 20);
	
	
	//Colori
	Color bg = new Color(220, 220, 220);
	Color fg = new Color(50, 50, 50);
	
	Color border1 = Color.decode("#545E75");
	Color border2 = Color.decode("#82A0BC");
	
	Color boxBackground = Color.decode("#545E75");
	
	Color black = new Color(0, 0, 0);
	Color white = new Color(255, 255, 255);
	Color greenBtn = new Color(60, 179, 113);
	Color redBtn = new Color(178, 34, 34);
	Color blueBtn = new Color(100, 149, 237);
	
	Icon refreshIcon = new ImageIcon("res\\icons\\refresh.png");
	Icon inventoryIcon = new ImageIcon("res\\icons\\inventory.png");
	Icon sellsIcon = new ImageIcon("res\\icons\\sells.png");
	Icon deleteIcon = new ImageIcon("res\\icons\\delete.png");
	Icon addIcon = new ImageIcon("res\\icons\\add.png");
	Icon backIcon = new ImageIcon("res\\icons\\back.png");
	Icon exitIcon = new ImageIcon("res\\icons\\exit.png");
	Icon saveIcon = new ImageIcon("res\\icons\\save.png");
	
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
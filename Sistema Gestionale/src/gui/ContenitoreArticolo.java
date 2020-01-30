package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import logic.Articolo;
import logic.Controller;

public class ContenitoreArticolo extends JPanel {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private Style style;
	
	private ContenutoVendita contenutoVendita;
	private Catalogo catalogo;
	private FinestraInventario inventario;
	
	private JPanel pannelloImmagine;
	private JLabel labelImg;
	private ImageIcon img;
	private JPanel contenitoreDati;
	private JLabel labelTaglia;
	private JLabel labelMarca;
	private JPanel panelloDati;
	private JLabel labelNome;
	private JLabel labelColore;
	private JLabel labelDescrizione;
	private JPanel panelloQualità;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	
	private Articolo articolo;
	
	public ContenitoreArticolo(Controller controller, ContenutoVendita contenutoVendita) {
		//Reference al parente
		this.controller = controller;
		this.contenutoVendita = contenutoVendita;
		style = new Style();
		
		ImpostaPanello();
		ImpostaPanelloImmagine();
		ImpostaPanelloDati();
	}
	
	public ContenitoreArticolo(Controller controller, Catalogo catalogo) {
		//Reference al parente
		this.controller = controller;
		this.catalogo = catalogo;
		style = new Style();
						
		ImpostaPanello();
		ImpostaPanelloImmagine();
		ImpostaPanelloDati();
	}
	
	public ContenitoreArticolo(Controller controller, FinestraInventario inventario) {
		//Reference al parente
		this.controller = controller;
		this.inventario = inventario;
		style = new Style();
				
		ImpostaPanello();
		ImpostaPanelloImmagine();
		ImpostaPanelloDati();
	}
	
	
	public ImageIcon GetImage(int width, int height, String path) {
		
		try {
			BufferedImage temp = ImageIO.read(new File(path));
			Image image = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			img = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	public void InserisciDati() {
		articolo = controller.getArticolo();
		
		labelNome.setText(articolo.getNome());
		labelDescrizione.setText(articolo.getDescrizione());
		labelMarca.setText(articolo.getMarca());
		labelTaglia.setText(articolo.getTaglia());
		labelColore.setText(articolo.getColore());
		labelImg.setIcon(GetImage(labelImg.getPreferredSize().width, labelImg.getPreferredSize().height, articolo.getImagePath()));
	}
	
	public void ImpostaPanello() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1200, 150));
		setMaximumSize(new Dimension(1200, 150));
		setBackground(style.bg);
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	}
	
	public void ImpostaPanelloImmagine() {
		pannelloImmagine = new JPanel();
		pannelloImmagine.setBorder(new MatteBorder(0, 0, 0, 2, (Color) Color.GRAY));
		pannelloImmagine.setBackground(style.bg);
		add(pannelloImmagine, BorderLayout.WEST);
		pannelloImmagine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelImg = new JLabel("");
		labelImg.setHorizontalAlignment(SwingConstants.CENTER);
		labelImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		pannelloImmagine.add(labelImg);
		labelImg.setBorder(null);
		labelImg.setPreferredSize(new Dimension(140, 140));
		labelImg.setMaximumSize(new Dimension(180, 300));
	}
	
	public void ImpostaPanelloDati() {
		
		contenitoreDati = new JPanel();
		contenitoreDati.setBackground(style.bg);
		contenitoreDati.setLayout(new BorderLayout(0, 0));
		contenitoreDati.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(getContenitoreDati(), BorderLayout.CENTER);

		panelloDati = new JPanel();
		panelloDati.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panelloDati.setBackground(style.bg);
		contenitoreDati.add(panelloDati, BorderLayout.CENTER);
		panelloDati.setLayout(new BorderLayout(0, 0));
		
		labelNome = new JLabel();
		panelloDati.add(labelNome, BorderLayout.NORTH);
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		labelNome.setHorizontalTextPosition(SwingConstants.CENTER);
		labelNome.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		labelDescrizione = new JLabel();
		labelDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescrizione.setVerticalAlignment(SwingConstants.TOP);
		labelDescrizione.setFont(style.arial);
		panelloDati.add(labelDescrizione, BorderLayout.CENTER);
		labelDescrizione.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelloQualità = new JPanel();
		panelloQualità.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		panelloQualità.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelloQualità.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelloQualità.setBackground(style.bg);
		panelloDati.add(panelloQualità, BorderLayout.SOUTH);
		
		
		labelMarca = new JLabel();
		panelloQualità.add(labelMarca);
		labelMarca.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelMarca.setFont(style.defaultS);
		labelMarca.setPreferredSize(new Dimension(150, 15));
		
		horizontalStrut = Box.createHorizontalStrut(200);
		panelloQualità.add(horizontalStrut);
		
		labelColore = new JLabel();
		labelColore.setPreferredSize(new Dimension(100, 14));
		panelloQualità.add(labelColore);
		labelColore.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelColore.setFont(style.defaultS);
		
		horizontalStrut_1 = Box.createHorizontalStrut(200);
		panelloQualità.add(horizontalStrut_1);
		
		labelTaglia = new JLabel();
		panelloQualità.add(labelTaglia);
		labelTaglia.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelTaglia.setFont(style.defaultS);
		labelTaglia.setPreferredSize(new Dimension(100, 15));	
	}
	
	//Getter
	public JPanel getContenitoreDati() {
		return contenitoreDati;
	}

	public Style getStyle() {
		return style;
	}
	
	public float getPrezzoDiListino() {
		return articolo.getPrezzoDiListino();
	}
	
	public int getQuantità() {
		return articolo.getQuantità();
	}

}

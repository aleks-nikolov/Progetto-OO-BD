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
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;

public class ContenitoreArticolo extends JPanel {

	private static final long serialVersionUID = 1L;

	private UtilitàAspetto utilità;
	
	private ContenutoVendita contenutoVendita;
	private JPanel pannelloImmagine;
	private JLabel labelImg;
	private ImageIcon img;
	private JPanel panelloLaterale;
	private JPanel contenitoreDati;
	private JLabel labelTaglia;
	private JLabel labelMarca;
	private JLabel labelQuantità;
	private JButton btnRimuovi;
	private JPanel panelloDati;
	private JLabel labelNome;
	private JLabel labelPrezzo;
	private JLabel labelColore;
	private JLabel labelDescrizione;
	private JPanel panelloQualità;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	
	public ContenitoreArticolo(ContenutoVendita contenutoVendita) {
		//Reference al parente
		this.contenutoVendita = contenutoVendita;
		utilità = new UtilitàAspetto();
		
		setBackground(utilità.bg);
		setMinimumSize(new Dimension(1200, 150));
		
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		ImpostaPanello();
		ImpostaPanelloImmagine();
		ImpostaPanelloDati();
		
		AggiungiListener();
	}
	
	public void AggiungiListener() {
	
		btnRimuovi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				contenutoVendita.RimuoviArticolo(ContenitoreArticolo.this);
			}
			
		});
		
	}
	
	public ImageIcon GetImage(int width, int height) {
		
		try {
			BufferedImage temp = ImageIO.read(new File("res\\images\\magliette\\nike_black.png"));
			Image image = temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			img = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	
	public void ImpostaPanello() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1200, 150));
		setMaximumSize(new Dimension(1200, 150));
	}
	
	public void ImpostaPanelloImmagine() {
		pannelloImmagine = new JPanel();
		pannelloImmagine.setBorder(new MatteBorder(0, 0, 0, 2, (Color) Color.GRAY));
		pannelloImmagine.setBackground(utilità.bg);
		add(pannelloImmagine, BorderLayout.WEST);
		pannelloImmagine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelImg = new JLabel("");
		labelImg.setHorizontalAlignment(SwingConstants.CENTER);
		labelImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		pannelloImmagine.add(labelImg);
		labelImg.setBorder(null);
		labelImg.setPreferredSize(new Dimension(140, 140));
		labelImg.setMaximumSize(new Dimension(180, 300));
		labelImg.setIcon(GetImage(labelImg.getPreferredSize().width, labelImg.getPreferredSize().height));
	}
	
	public void ImpostaPanelloDati() {
		
		contenitoreDati = new JPanel();
		contenitoreDati.setBackground(utilità.bg);
		contenitoreDati.setLayout(new BorderLayout(0, 0));
		contenitoreDati.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contenitoreDati, BorderLayout.CENTER);

		panelloDati = new JPanel();
		panelloDati.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panelloDati.setBackground(utilità.bg);
		contenitoreDati.add(panelloDati, BorderLayout.CENTER);
		panelloDati.setLayout(new BorderLayout(0, 0));
		
		labelNome = new JLabel("Maglietta di cotone");
		panelloDati.add(labelNome, BorderLayout.NORTH);
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		labelNome.setHorizontalTextPosition(SwingConstants.CENTER);
		labelNome.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		labelDescrizione = new JLabel("Descrizione articolo");
		labelDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescrizione.setVerticalAlignment(SwingConstants.TOP);
		labelDescrizione.setFont(utilità.arial);
		panelloDati.add(labelDescrizione, BorderLayout.CENTER);
		labelDescrizione.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelloQualità = new JPanel();
		panelloQualità.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.LIGHT_GRAY));
		panelloQualità.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelloQualità.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelloQualità.setBackground(utilità.bg);
		panelloDati.add(panelloQualità, BorderLayout.SOUTH);
		
		
		labelMarca = new JLabel("Marca: Adidas");
		panelloQualità.add(labelMarca);
		labelMarca.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelMarca.setFont(utilità.arialS);
		labelMarca.setPreferredSize(new Dimension(150, 15));
		
		horizontalStrut = Box.createHorizontalStrut(200);
		panelloQualità.add(horizontalStrut);
		
		labelColore = new JLabel("Colore: nero");
		labelColore.setPreferredSize(new Dimension(100, 14));
		panelloQualità.add(labelColore);
		labelColore.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelColore.setFont(utilità.arialS);
		
		horizontalStrut_1 = Box.createHorizontalStrut(200);
		panelloQualità.add(horizontalStrut_1);
		
		labelTaglia = new JLabel("Taglia: M");
		panelloQualità.add(labelTaglia);
		labelTaglia.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelTaglia.setFont(utilità.arialS);
		labelTaglia.setPreferredSize(new Dimension(100, 15));
		
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(utilità.bg);
		panelloLaterale.setPreferredSize(new Dimension(150, 10));
		contenitoreDati.add(panelloLaterale, BorderLayout.EAST);
		panelloLaterale.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panelloLaterale.setLayout(new BoxLayout(panelloLaterale, BoxLayout.Y_AXIS));
		
		labelPrezzo = new JLabel("15,20\u20AC");
		panelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		labelQuantità = new JLabel("Quantita: 10");
		labelQuantità.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantità.setFont(utilità.arialS);
		labelQuantità.setPreferredSize(new Dimension(49, 15));
		panelloLaterale.add(labelQuantità);
		
		btnRimuovi = new JButton("X");
		btnRimuovi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(btnRimuovi);
		btnRimuovi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRimuovi.setBackground(utilità.redBtn);
		btnRimuovi.setMargin(new Insets(20, 20, 20, 20));
		btnRimuovi.setPreferredSize(new Dimension(10, 10));
	}
	
	

}

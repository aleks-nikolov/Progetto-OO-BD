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

public class ContenitoreArticolo extends JPanel {

	private static final long serialVersionUID = 1L;

	private Utilit‡Aspetto utilit‡;
	
	private ContenutoVendita contenutoVendita;
	private JPanel pannelloImmagine;
	private JLabel labelImg;
	private ImageIcon img;
	private JPanel panel;
	private JPanel contenitoreDati;
	private JLabel labelTaglia;
	private JLabel labelMarca;
	private JLabel labelQuantit‡;
	private JButton btnRimuovi;
	private JPanel panel_1;
	private JLabel labelNome;
	private JLabel labelPrezzo;
	private JLabel labelColore;
	
	public ContenitoreArticolo(ContenutoVendita contenutoVendita) {
		//Reference al parente
		this.contenutoVendita = contenutoVendita;
		utilit‡ = new Utilit‡Aspetto();
		
		setBackground(utilit‡.bg);
		setMinimumSize(new Dimension(1200, 150));
		
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
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
			BufferedImage temp = ImageIO.read(new File("res\\images\\black_tshirt.png"));
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
		pannelloImmagine.setBackground(utilit‡.bg);
		add(pannelloImmagine, BorderLayout.WEST);
		pannelloImmagine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelImg = new JLabel("");
		pannelloImmagine.add(labelImg);
		labelImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelImg.setPreferredSize(new Dimension(140, 140));
		labelImg.setMaximumSize(new Dimension(180, 300));
		labelImg.setIcon(GetImage(labelImg.getPreferredSize().width, labelImg.getPreferredSize().height));
	}
	
	public void ImpostaPanelloDati() {
		
		contenitoreDati = new JPanel();
		contenitoreDati.setBackground(utilit‡.bg);
		contenitoreDati.setLayout(new BorderLayout(0, 0));
		contenitoreDati.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contenitoreDati, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(utilit‡.bg);
		panel.setPreferredSize(new Dimension(150, 10));
		contenitoreDati.add(panel, BorderLayout.WEST);
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		labelMarca = new JLabel("Marca: Adidas");
		labelMarca.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelMarca.setFont(utilit‡.arialS);
		labelMarca.setPreferredSize(new Dimension(68, 15));
		panel.add(labelMarca);
		
		panel.add(Box.createRigidArea(new Dimension(20, 15)));
		
		labelColore = new JLabel("Colore: nero");
		labelColore.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelColore.setFont(utilit‡.arialS);
		panel.add(labelColore);
		
		panel.add(Box.createRigidArea(new Dimension(20, 15)));
		
		labelTaglia = new JLabel("Taglia: M");
		labelTaglia.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelTaglia.setFont(utilit‡.arialS);
		labelTaglia.setPreferredSize(new Dimension(43, 15));
		panel.add(labelTaglia);

		panel.add(Box.createRigidArea(new Dimension(20, 15)));
		
		labelQuantit‡ = new JLabel("Quantita: 10");
		labelQuantit‡.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantit‡.setFont(utilit‡.arialS);
		labelQuantit‡.setPreferredSize(new Dimension(49, 15));
		panel.add(labelQuantit‡);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_1.setBackground(utilit‡.bg);
		contenitoreDati.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		labelNome = new JLabel("Maglietta di cotone");
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		labelNome.setHorizontalTextPosition(SwingConstants.CENTER);
		labelNome.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(labelNome, BorderLayout.NORTH);
		
		labelPrezzo = new JLabel("15,20\u20AC");
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		panel_1.add(labelPrezzo, BorderLayout.EAST);
		
		btnRimuovi = new JButton("X");
		btnRimuovi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRimuovi.setBackground(utilit‡.redBtn);
		contenitoreDati.add(btnRimuovi, BorderLayout.EAST);
		btnRimuovi.setMargin(new Insets(2, 2, 2, 2));
		btnRimuovi.setPreferredSize(new Dimension(100, 30));
	}
	
	

}

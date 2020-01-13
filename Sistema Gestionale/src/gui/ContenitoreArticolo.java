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
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContenitoreArticolo extends JPanel {

	private static final long serialVersionUID = 1L;

	private AggiungiArticoliFrame aaf;
	JPanel pannelloImmagine;
	
	JLabel labelImg;
	
	ImageIcon img;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnRimuovi;
	
	public ContenitoreArticolo(AggiungiArticoliFrame aaf) {
		this.aaf = aaf;
		
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
				aaf.RimuoviArticolo(ContenitoreArticolo.this);
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
		setPreferredSize(new Dimension(180, 300));
		setMaximumSize(new Dimension(180, 300));
	}
	
	public void ImpostaPanelloImmagine() {
		pannelloImmagine = new JPanel();
		add(pannelloImmagine, BorderLayout.CENTER);
		pannelloImmagine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelImg = new JLabel("");
		pannelloImmagine.add(labelImg);
		labelImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelImg.setPreferredSize(new Dimension(150, 166));
		labelImg.setMaximumSize(new Dimension(180, 300));
		labelImg.setIcon(GetImage(labelImg.getPreferredSize().width, labelImg.getPreferredSize().height));
	}
	
	public void ImpostaPanelloDati() {
		JPanel pannelloDati = new JPanel();
		pannelloDati.setPreferredSize(new Dimension(10, 100));
		add(pannelloDati, BorderLayout.SOUTH);
		pannelloDati.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Taglia: M");
		lblNewLabel.setPreferredSize(new Dimension(43, 15));
		pannelloDati.add(lblNewLabel, BorderLayout.NORTH);
		
		lblNewLabel_1 = new JLabel("Marca: Adidas");
		lblNewLabel_1.setPreferredSize(new Dimension(68, 15));
		pannelloDati.add(lblNewLabel_1, BorderLayout.CENTER);
		
		lblNewLabel_2 = new JLabel("Quantita: 10");
		lblNewLabel_2.setPreferredSize(new Dimension(49, 15));
		pannelloDati.add(lblNewLabel_2, BorderLayout.SOUTH);
		
		btnRimuovi = new JButton("X");
		btnRimuovi.setMargin(new Insets(2, 2, 2, 2));
		btnRimuovi.setPreferredSize(new Dimension(50, 30));
		pannelloDati.add(btnRimuovi, BorderLayout.EAST);
	}
	
	

}

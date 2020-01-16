package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class Catalogo extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private SwingController sc;
	private ContenutoVendita contenutoVendita;
	private Utilit‡Aspetto utilit‡;
	
	private JPanel contentPane;
	private JComboBox boxCategoria;
	private JComboBox boxMarca;
	private JComboBox boxTaglia;
	private JToggleButton tglbtnBlack;
	private JToggleButton tglbtnWhite;
	private JToggleButton tglbtnRed;
	private JToggleButton tglbtnGreen;
	private JToggleButton tglbtnBlue;

	public Catalogo(SwingController sc, ContenutoVendita contenutoVendita) {
		//Reference a ContenutoVendita
		this.sc = sc;
		this.contenutoVendita = contenutoVendita;
		
		utilit‡ = new Utilit‡Aspetto();
		
		ImpostaFinestra();
		ImpostaPanelloSuperiore();
		ImpostaPanelloCentrale();
		ImpostaPanelloInferiore();
	}
	
	public void ImpostaFinestra() {
		setVisible(true);
		setTitle("Catalogo");
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void ImpostaPanelloSuperiore() {
		JPanel panelloSuperiore = new JPanel();
		contentPane.add(panelloSuperiore, BorderLayout.NORTH);
		
		boxCategoria = new JComboBox(sc.getCategoria().toArray());
		boxCategoria.setPreferredSize(new Dimension(150, 25));
		boxCategoria.setFont(utilit‡.arialS);
		panelloSuperiore.add(boxCategoria);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		tglbtnBlack = new JToggleButton();
		tglbtnBlack.setPreferredSize(new Dimension(25, 25));
		tglbtnBlack.setBackground(utilit‡.black);
		btnGroup.add(tglbtnBlack);
		panelloSuperiore.add(tglbtnBlack);
		
		tglbtnWhite = new JToggleButton();
		tglbtnWhite.setPreferredSize(new Dimension(25, 25));
		tglbtnWhite.setBackground(utilit‡.white);
		btnGroup.add(tglbtnWhite);
		panelloSuperiore.add(tglbtnWhite);
		
		tglbtnRed = new JToggleButton();
		tglbtnRed.setPreferredSize(new Dimension(25, 25));
		tglbtnRed.setBackground(utilit‡.redBtn);
		btnGroup.add(tglbtnRed);
		panelloSuperiore.add(tglbtnRed);
		
		tglbtnGreen = new JToggleButton();
		tglbtnGreen.setPreferredSize(new Dimension(25, 25));
		tglbtnGreen.setBackground(utilit‡.greenBtn);
		btnGroup.add(tglbtnGreen);
		panelloSuperiore.add(tglbtnGreen);
		
		tglbtnBlue = new JToggleButton();
		tglbtnBlue.setPreferredSize(new Dimension(25, 25));
		tglbtnBlue.setBackground(utilit‡.blueBtn);
		btnGroup.add(tglbtnBlue);
		panelloSuperiore.add(tglbtnBlue);
		
		boxMarca = new JComboBox(sc.getMarca().toArray());
		boxMarca.setPreferredSize(new Dimension(150, 25));
		boxMarca.setFont(utilit‡.arialS);
		panelloSuperiore.add(boxMarca);
		
		boxTaglia = new JComboBox(sc.getTaglia().toArray());
		boxTaglia.setPreferredSize(new Dimension(150, 25));
		boxTaglia.setFont(utilit‡.arialS);
		panelloSuperiore.add(boxTaglia);
	}
	
	public void ImpostaPanelloCentrale() {
		JPanel panel = new JPanel();
		panel.setBackground(utilit‡.bg);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(Box.createRigidArea(new Dimension(1200, 10)));
        panel.add(new ContenitoreArticolo_Catalogo(contenutoVendita));
	}

	public void ImpostaPanelloInferiore() {
		JPanel panelloInferiore = new JPanel();
		contentPane.add(panelloInferiore, BorderLayout.SOUTH);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(utilit‡.arialL);
		btnAnnulla.setBackground(utilit‡.redBtn);
		panelloInferiore.add(btnAnnulla);
	}
}

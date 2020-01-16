package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Controller;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class Catalogo extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private ContenutoVendita contenutoVendita;
	private Utilities utilities;
	
	private JPanel contentPane;
	private JComboBox boxCategoria;
	private JComboBox boxMarca;
	private JComboBox boxTaglia;
	private JToggleButton tglbtnBlack;
	private JToggleButton tglbtnWhite;
	private JToggleButton tglbtnRed;
	private JToggleButton tglbtnGreen;
	private JToggleButton tglbtnBlue;
	JButton btnAnnulla;

	public Catalogo(Controller controller, ContenutoVendita contenutoVendita) {
		//Reference a ContenutoVendita
		this.controller = controller;
		this.contenutoVendita = contenutoVendita;
		
		utilities = new Utilities();
		
		ImpostaFinestra();
		ImpostaPanelloSuperiore();
		ImpostaPanelloCentrale();
		ImpostaPanelloInferiore();
		AggiungiListener();
	}
	
	
	public void AggiungiListener() {
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contenutoVendita.setEnabled(true);
				controller.ChiudiFrame(Catalogo.this);
			}
			
		});

		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                contenutoVendita.setEnabled(true);
                controller.ChiudiFrame(Catalogo.this);
            }
        });

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
		
		boxCategoria = new JComboBox(controller.getCategoria().toArray());
		boxCategoria.setPreferredSize(new Dimension(150, 25));
		boxCategoria.setFont(utilities.arialS);
		panelloSuperiore.add(boxCategoria);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		tglbtnBlack = new JToggleButton();
		tglbtnBlack.setPreferredSize(new Dimension(25, 25));
		tglbtnBlack.setBackground(utilities.black);
		btnGroup.add(tglbtnBlack);
		panelloSuperiore.add(tglbtnBlack);
		
		tglbtnWhite = new JToggleButton();
		tglbtnWhite.setPreferredSize(new Dimension(25, 25));
		tglbtnWhite.setBackground(utilities.white);
		btnGroup.add(tglbtnWhite);
		panelloSuperiore.add(tglbtnWhite);
		
		tglbtnRed = new JToggleButton();
		tglbtnRed.setPreferredSize(new Dimension(25, 25));
		tglbtnRed.setBackground(utilities.redBtn);
		btnGroup.add(tglbtnRed);
		panelloSuperiore.add(tglbtnRed);
		
		tglbtnGreen = new JToggleButton();
		tglbtnGreen.setPreferredSize(new Dimension(25, 25));
		tglbtnGreen.setBackground(utilities.greenBtn);
		btnGroup.add(tglbtnGreen);
		panelloSuperiore.add(tglbtnGreen);
		
		tglbtnBlue = new JToggleButton();
		tglbtnBlue.setPreferredSize(new Dimension(25, 25));
		tglbtnBlue.setBackground(utilities.blueBtn);
		btnGroup.add(tglbtnBlue);
		panelloSuperiore.add(tglbtnBlue);
		
		boxMarca = new JComboBox(controller.getMarca().toArray());
		boxMarca.setPreferredSize(new Dimension(150, 25));
		boxMarca.setFont(utilities.arialS);
		panelloSuperiore.add(boxMarca);
		
		boxTaglia = new JComboBox(controller.getTaglia().toArray());
		boxTaglia.setPreferredSize(new Dimension(150, 25));
		boxTaglia.setFont(utilities.arialS);
		panelloSuperiore.add(boxTaglia);
	}
	
	public void ImpostaPanelloCentrale() {
		JPanel panel = new JPanel();
		panel.setBackground(utilities.bg);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(Box.createRigidArea(new Dimension(1200, 10)));
        panel.add(new Contenitore_catalogo(contenutoVendita));
	}

	public void ImpostaPanelloInferiore() {
		JPanel panelloInferiore = new JPanel();
		contentPane.add(panelloInferiore, BorderLayout.SOUTH);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(utilities.arialL);
		btnAnnulla.setBackground(utilities.redBtn);
		panelloInferiore.add(btnAnnulla);
	}
}

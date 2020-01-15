package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class ContenutoVendita extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private SwingController sc;
	private Utilit‡Aspetto utilit‡;
	
	private ContenitoreArticolo contenitore;
	
	private JButton btnApplica;
	private JButton btnAnnulla;
	private JButton btnAdd;
		
	private JPanel pannelloInferiore;
	private JPanel panel;
	private JScrollPane scroll;
	private Component horizontalStrut;
	private Component horizontalStrut_1;

	
	public ContenutoVendita(SwingController sc){
		this.sc = sc;
		utilit‡ = new Utilit‡Aspetto();
		
		contenitore = new ContenitoreArticolo(this);
		contenitore.setMinimumSize(new Dimension(1200, 150));
		contenitore.setMaximumSize(new Dimension(1200, 150));
		contenitore.setPreferredSize(new Dimension(1200, 150));
		
		ImpostaFinestra();
		ImpostaPanelloCentrale();
		ImpostaPanelInferiore();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sc.CambiaFrame(ContenutoVendita.this, sc.getVendite());
			}
			
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AggiungiArticolo();
			}
			
		});
	}
	
	public void AggiungiArticolo() {
		ContenitoreArticolo temp = new ContenitoreArticolo(this);
		panel.add(temp);
		panel.add(Box.createRigidArea(new Dimension(1200, 10)));
		panel.revalidate();
		panel.repaint();
	}
	
	public void RimuoviArticolo(ContenitoreArticolo contenitoreDaRimuovere) {
		panel.remove(contenitoreDaRimuovere);
		panel.revalidate();
		panel.repaint();
	}
	
	public void ImpostaFinestra() {
		setTitle("Aggiunta Articoli");
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(utilit‡.bg);
	}
	
	public void ImpostaPanelloCentrale() {
		panel = new JPanel();
		panel.setBackground(utilit‡.bg);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.add(contenitore);
        panel.add(Box.createRigidArea(new Dimension(1200, 10)));
		
		scroll = new JScrollPane(panel);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(contenitore);
        panel.add(Box.createRigidArea(new Dimension(1200, 10)));
	}
	
	public void ImpostaPanelInferiore() {
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(utilit‡.bg);
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnAdd = new JButton("+");
		btnAdd.setMargin(new Insets(10, 20, 10, 20));
		btnAdd.setFont(utilit‡.arialM);
		btnAdd.setBackground(utilit‡.greenBtn);
		pannelloInferiore.add(btnAdd);
		
		horizontalStrut = Box.createHorizontalStrut(600);
		pannelloInferiore.add(horizontalStrut);
		
		btnApplica = new JButton("APPLICA");
		btnApplica.setFont(utilit‡.arialM);
		btnApplica.setBackground(utilit‡.greenBtn);
		btnApplica.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnApplica);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		pannelloInferiore.add(horizontalStrut_1);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBackground(utilit‡.redBtn);
		btnAnnulla.setFont(utilit‡.arialM);
		btnAnnulla.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnAnnulla);
	}
	
	
	public SwingController getsc() {
		return sc;
	}
}

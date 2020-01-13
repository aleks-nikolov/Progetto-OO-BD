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
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Font;

public class AggiungiArticoliFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private SwingController sc;
	
	private ContenitoreArticolo contenitore;

	
	private JButton btnApplica;
	private JButton btnAnnulla;
	private JButton btnAdd;
		
	private JPanel pannelloInferiore;
	private JPanel panel;
	private JScrollPane scroll;

	
	public AggiungiArticoliFrame(SwingController sc){
		this.sc = sc;
		
		contenitore = new ContenitoreArticolo(this);
		
		ImpostaFinestra();
		ImpostaPanelloCentrale();
		ImpostaPanelInferiore();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sc.CambiaFrame(AggiungiArticoliFrame.this, sc.getVendite());
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
		setBounds(100, 100, 639, 427);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void ImpostaPanelloCentrale() {
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		scroll = new JScrollPane(panel);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        
        panel.add(contenitore);
	}
	
	public void ImpostaPanelInferiore() {
		pannelloInferiore = new JPanel();
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnApplica = new JButton("APPLICA");
		btnApplica.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnApplica.setBackground(new Color(60, 179, 113));
		btnApplica.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnApplica);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pannelloInferiore.add(horizontalStrut);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBackground(new Color(165, 42, 42));
		btnAnnulla.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnAnnulla.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnAnnulla);
		
		btnAdd = new JButton("+");
		pannelloInferiore.add(btnAdd);
	}
}

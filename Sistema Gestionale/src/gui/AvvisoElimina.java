package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class AvvisoElimina extends JDialog {
	private static final long serialVersionUID = 1L;

	private Style style;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private final JPanel contentPanel = new JPanel();

//**************************************************************************************
	
	public AvvisoElimina(ContenitoreTransazione contenitore) {
		style = new Style();
		
		contenitore.getContenutoTransazione().setEnabled(false);
		
		setVisible(true);
		setTitle("Attenzione");
		setBounds(500, 300, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			JLabel text = new JLabel("Procedere con l'eliminazione dell'articolo?");
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setFont(style.defaultM);
			getContentPane().add(text);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("SI");
				okButton.setBackground(style.greenBtn);
				okButton.setFont(style.defaultS);
				okButton.setFocusable(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("NO");
				cancelButton.setBackground(style.redBtn);
				cancelButton.setFont(style.defaultS);
				cancelButton.setFocusable(false);
				buttonPane.add(cancelButton);
			}
		}
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				contenitore.getContenutoTransazione().setEnabled(true);
				contenitore.Rimuovi();
				contenitore.getContenutoTransazione().AggiornaPrezzoTotale();
				AvvisoElimina.this.setVisible(false);
				
			}});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				contenitore.getContenutoTransazione().setEnabled(true);
				AvvisoElimina.this.setVisible(false);
				
			}});

		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	
            	contenitore.getContenutoTransazione().setEnabled(true);
				AvvisoElimina.this.setVisible(false);
				
            }
        });
		
	}

}

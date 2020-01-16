package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class AvvisoElimina extends JDialog {

	private Utilities utilità;
	private JButton okButton;
	private JButton cancelButton;
	
	
	private final JPanel contentPanel = new JPanel();

	public AvvisoElimina(Contenitore_vendita contenitore) {
		utilità = new Utilities();
		
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
			text.setFont(utilità.arialM);
			getContentPane().add(text);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("SI");
				okButton.setBackground(utilità.greenBtn);
				okButton.setFont(utilità.arialS);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("NO");
				cancelButton.setBackground(utilità.redBtn);
				cancelButton.setFont(utilità.arialS);
				buttonPane.add(cancelButton);
			}
		}
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				contenitore.Rimuovi();
				AvvisoElimina.this.setVisible(false);
			}});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AvvisoElimina.this.setVisible(false);
			}});

	}

}

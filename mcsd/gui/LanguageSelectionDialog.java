package mcsd.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mcsd.gui.lang.LanguageFile;
import mcsd.instruction.MCSInstruction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class LanguageSelectionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox langSelBox;
	private Map<Integer, MCSInstruction> instTable;

	public LanguageSelectionDialog(Map<Integer, MCSInstruction> inst, List<LanguageFile> languageFiles) {
		this.instTable = inst;
		setTitle("MCSD Language Selector");
		setResizable(false);
		setBounds(100, 100, 355, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSelectYourLanguage = new JLabel("Select your Language:");
		lblSelectYourLanguage.setBounds(10, 11, 153, 14);
		contentPanel.add(lblSelectYourLanguage);
		
		langSelBox = new JComboBox();
		for(LanguageFile f : languageFiles){
			langSelBox.addItem(f);
		}
		langSelBox.setBounds(10, 38, 329, 31);
		contentPanel.add(langSelBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						load();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton exitButton = new JButton("Exit");
				exitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				exitButton.setActionCommand("Cancel");
				buttonPane.add(exitButton);
			}
		}
	}
	private void load() {
		LanguageFile f = (LanguageFile) langSelBox.getSelectedItem();
		new MCSDUserInterface(instTable, f).setVisible(true);
		dispose();
	}
}

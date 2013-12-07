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

public class LanguageSelectionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public LanguageSelectionDialog(Map<Integer, MCSInstruction> inst, List<LanguageFile> languageFiles) {
		setTitle("MCSD Language Selector");
		setResizable(false);
		setBounds(100, 100, 450, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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

}

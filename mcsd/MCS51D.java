package mcsd;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mcsd.gui.MCSDUserInterface;
import mcsd.instruction.MCSInstruction;

public class MCS51D {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if(info.getName().equals("Nimbus")) {
				UIManager.setLookAndFeel(info.getClassName());
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MCSDUserInterface ui = new MCSDUserInterface(MCSInstruction.INSTRUCTION_MAP);
				ui.setVisible(true);
			}
		});
	}

}

package mcsd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mcsd.gui.MCSDUserInterface;
import mcsd.gui.lang.LanguageFile;
import mcsd.instruction.MCSInstruction;

public class MCS51D {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		//Initialize LookAndFeel
		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if(info.getName().equals("Nimbus")) {
				UIManager.setLookAndFeel(info.getClassName());
			}
		}
		//Load language Files
		InputStream lfs = ClassLoader.getSystemResourceAsStream("mcsd/gui/lang/langfiles");
		ArrayList<LanguageFile> languageFiles = new ArrayList<>();
		Scanner scn = new Scanner(lfs);
		while(scn.hasNextLine()) {
			String path = scn.nextLine();
			languageFiles.add(LanguageFile.readLangFile(ClassLoader.getSystemResourceAsStream(path),path.replace("mcd/gui/lang/", "")));
		}
		//Show the Language Selection Dialog
		
		//Load the Main GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MCSDUserInterface ui = new MCSDUserInterface(MCSInstruction.INSTRUCTION_MAP);
				ui.setVisible(true);
			}
		});
	}

}

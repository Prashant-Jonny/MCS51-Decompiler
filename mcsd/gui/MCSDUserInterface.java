package mcsd.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import mcsd.decompiler.MCSDecompiler;
import mcsd.gui.lang.LanguageFile;
import mcsd.hex.HEXFile;
import mcsd.instruction.MCSInstruction;

public class MCSDUserInterface extends JFrame {

	private JPanel contentPane;
	
	private Map<Integer, MCSInstruction> instructions;
	private HEXFile loadedHEX = null;
	private LanguageFile lnf;
	private MCSDecompiler decompiler = new MCSDecompiler(MCSInstruction.INSTRUCTION_MAP);
	private JTextField pathTextField;
	private JTextArea inputTextArea;
	private JTextArea decompTextArea;
	private JButton saveButton;

	public MCSDUserInterface(Map<Integer, MCSInstruction> inst, LanguageFile l) {
		this.lnf = l;
		setResizable(false);
		this.instructions = inst;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pathTextField = new JTextField();
		pathTextField.setEditable(false);
		pathTextField.setEnabled(false);
		pathTextField.setBounds(10, 27, 449, 28);
		contentPane.add(pathTextField);
		pathTextField.setColumns(10);
		
		JButton searchButton = new JButton(l.getSearchButtonText());
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadHexFile();
			}
		});
		searchButton.setBounds(469, 26, 115, 29);
		contentPane.add(searchButton);
		
		JLabel lblPfadZurIntel = new JLabel(l.getPathLabelText());
		lblPfadZurIntel.setBounds(10, 11, 200, 14);
		contentPane.add(lblPfadZurIntel);
		
		JLabel lblUrsprungsdaten = new JLabel(l.getSourceDataLabelText());
		lblUrsprungsdaten.setBounds(10, 58, 200, 14);
		contentPane.add(lblUrsprungsdaten);
		
		JLabel lblDekompilierterProgrammcode = new JLabel(l.getDecompDataLabelText());
		lblDekompilierterProgrammcode.setBounds(10, 227, 200, 16);
		contentPane.add(lblDekompilierterProgrammcode);
		
		saveButton = new JButton(l.getSaveButtonText());
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveAsm();
			}
		});
		saveButton.setEnabled(false);
		saveButton.setBounds(10, 472, 574, 42);
		contentPane.add(saveButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 574, 144);
		contentPane.add(scrollPane);
		
		inputTextArea = new JTextArea();
		scrollPane.setViewportView(inputTextArea);
		inputTextArea.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 251, 574, 209);
		contentPane.add(scrollPane_1);
		
		decompTextArea = new JTextArea();
		scrollPane_1.setViewportView(decompTextArea);
		decompTextArea.setEditable(false);
		setTitle("MCS51-Decompiler Version 1.0 by HALive (C) 2013");
	}
	private void loadHexFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return lnf.getFileChooserHEXFilter();
			}
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.toString().endsWith(".hex") || f.toString().endsWith(".HEX");
			}
		});
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			this.pathTextField.setText(f.toString());
			try {
				this.loadedHEX = new HEXFile(f);
				this.inputTextArea.setText(loadedHEX.getInitialFile());
				String[] dec = decompiler.decompile(loadedHEX.getData());
				for(String s : dec) {
					decompTextArea.append(s+"\n");
				}
				this.saveButton.setEnabled(true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, lnf.getDialogErrLoad());
				return;
			}
		}
	}
	private void saveAsm() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return lnf.getFileChooserHEXFilter();
			}
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.toString().endsWith(".hex") || f.toString().endsWith(".HEX");
			}
		});
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			try {
				decompTextArea.write(new FileWriter(f));
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, lnf.getDialogErrSave());
				return;
			}
		}
	}
}

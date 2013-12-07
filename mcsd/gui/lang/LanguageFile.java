package mcsd.gui.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageFile {
	private String searchButtonText;
	private String pathLabelText;
	private String sourceDataLabelText;
	private String decompDataLabelText;
	private String saveButtonText;
	private String fileName;
	
	private LanguageFile() {}
	
	public String getDecompDataLabelText() {
		return decompDataLabelText;
	}
	public String getPathLabelText() {
		return pathLabelText;
	}
	public String getSaveButtonText() {
		return saveButtonText;
	}
	public String getSearchButtonText() {
		return searchButtonText;
	}
	public String getSourceDataLabelText() {
		return sourceDataLabelText;
	}
	public String getFileName() {
		return fileName;
	}
	@Override
	public String toString() {
		return fileName;
	}
	public static LanguageFile readLangFile(InputStream stream, String fileName) throws IOException {
		Properties props = new Properties();
		props.load(stream);
		LanguageFile langFile = new LanguageFile();
		langFile.fileName = fileName;
		langFile.searchButtonText = props.getProperty("button.open", "Open file");
		langFile.saveButtonText = props.getProperty("button.save", "Save decompiled program");
		langFile.pathLabelText = props.getProperty("labels.hexpath", "Path to the Intel-HEX (.hex) file:");
		langFile.sourceDataLabelText = props.getProperty("label.source", "Contents of the sourcefile:");
		langFile.decompDataLabelText = props.getProperty("label.decompCode", "Decompiled Code:");
		return langFile;
	}
}

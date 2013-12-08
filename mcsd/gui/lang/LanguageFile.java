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
	private String dialogErrLoad;
	private String dialogErrSave;
	private String fileChooserHEXFilter;
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
	public String getDialogErrLoad() {
		return dialogErrLoad;
	}
	public String getDialogErrSave() {
		return dialogErrSave;
	}
	public String getFileChooserHEXFilter() {
		return fileChooserHEXFilter;
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
		langFile.searchButtonText = props.getProperty("button.open");
		langFile.saveButtonText = props.getProperty("button.save");
		langFile.pathLabelText = props.getProperty("labels.hexpath");
		langFile.sourceDataLabelText = props.getProperty("label.source");
		langFile.decompDataLabelText = props.getProperty("label.decompCode");
		langFile.dialogErrLoad = props.getProperty("dialog.err.load");
		langFile.dialogErrSave = props.getProperty("dialog.err.save");
		langFile.fileChooserHEXFilter = props.getProperty("filechooser.hexdesc");
		return langFile;
	}
}

package mcsd.hex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HEXFile {
	private HEXRecord[] records;
	private String initialFile;
	private int length;
	public HEXFile(File f) throws FileNotFoundException {
		Scanner scn = new Scanner(f);
		String s = "";
		while(scn.hasNextLine()) {
			s += scn.nextLine()+"\n";
		}
		createFromString(s);
	}
	public HEXFile(String s) {
		createFromString(s);
	}
	private void createFromString(String s) {
		this.initialFile = s;
		String[] recordLines = s.split("\n");
		int lns = 0;
		for(String sr : recordLines) {
			if(sr.startsWith(":")) {
				lns++;
			}
		}
		records = new HEXRecord[lns];
		for(int i = 0; i < records.length; i++) {
			if(!recordLines[i].startsWith(":")) {
				throw new RuntimeException("Invalid File!");
			}
			records[i] = new HEXRecord(recordLines[i]);
		}
		length = records.length;
	}
	public HEXRecord[] getRecords() {
		return records;
	}
	public int getDataLength() {
		int j = 0;
		for(HEXRecord rec : records) {
			j += rec.getContentLength();
		}
		return j;
	}
	public int[] getData() {
		int[] data = new int[getDataLength()];
		int pos = 0;
		for(int i = 0; i < records.length-1; i++) {
			int[] rec = records[i].getData();
			for(int j = 0; j < rec.length; j++) {
				data[pos] = rec[j];
				pos++;
			}
		}
		return data;
	}
	public String getInitialFile() {
		return initialFile;
	}
}

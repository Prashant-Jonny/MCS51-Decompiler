package mcsd.hex;

import java.util.Arrays;

public class HEXRecord {
	public static final int RECORD = 0;
	public static final int END_OF_FILE = 1;
		
	private int contentLength;
	private int recordType;
	private int[] firstAddress = new int[2];
	private int[] data = null;
	private int checksum;
	
	public HEXRecord(String hexLine) {
		hexLine = hexLine.replace(":", "");
		char[] hexData = hexLine.toCharArray();
		contentLength = Integer.parseInt(new String(hexData, 0, 2),16);
		int addrPtr = 0;
		for(int i = 2; i < 6; i = i+2) {
			firstAddress[addrPtr] = Integer.parseInt(new String(hexData, i, 2),16);
			addrPtr++;
		}
		recordType = Integer.parseInt(new String(hexData, 6, 2),16);
		if(contentLength > 0) {
			data = new int[contentLength];
			int byteCount = 0;
			for(int i = 8; i < hexData.length-2; i = i + 2) {
				data[byteCount] = Integer.parseInt(new String(hexData, i, 2),16);
				byteCount++;
			}
		}
		checksum = Integer.parseInt(new String(hexData, hexData.length-2, 2),16);
	}
	
	public int getChecksum() {
		return checksum;
	}
	public int getContentLength() {
		return contentLength;
	}
	public int[] getData() {
		return data;
	}
	public int[] getFirstAddress() {
		return firstAddress;
	}
	public int getRecordType() {
		return recordType;
	}
	public boolean  isEndOfFile() {
		return recordType == END_OF_FILE;
	}
	public int calcualteChecksum() {
		int c = contentLength+recordType;
		for(int d : firstAddress) {
			c += d;
		}
		for(int d : data) {
			c += d;
		}
		c = ((~c)+1)&0xFF;
		return c;
	}
	public boolean isChecksumEqual() {
		return checksum == calcualteChecksum();
	}
	@Override
	public String toString() {
		String s = "[length: "+contentLength+" firstAddress: "+Arrays.toString(firstAddress)
				+" rectype: "+recordType+" data:{";
		for(int i : data) {
			s += "0x"+Integer.toHexString(i).toUpperCase()+"; ";
		}
		s += "} checksum: "+checksum+"]";
		return s;
	}
}

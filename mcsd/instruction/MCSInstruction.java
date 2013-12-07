package mcsd.instruction;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MCSInstruction {
	public static Map<Integer, MCSInstruction> INSTRUCTION_MAP = new HashMap<>();
	public static List<MCSInstruction> INSTRUCTIONS = new ArrayList<>();
	static {
		InputStream instIn = ClassLoader.getSystemResourceAsStream("mcsd/instruction/instructions");
		Scanner scn = new Scanner(instIn);
		StringBuilder builder = new StringBuilder();
		while(scn.hasNextLine()) {
			builder.append(scn.nextLine()+ "\n");
		}
		String[] instLns = builder.toString().split("\n");
		for(String s : instLns) {
			MCSInstruction i = fromString(s);
			INSTRUCTIONS.add(i);
			INSTRUCTION_MAP.put(i.machineCode, i);
		}
		INSTRUCTION_MAP = Collections.unmodifiableMap(INSTRUCTION_MAP);
		INSTRUCTIONS = Collections.unmodifiableList(INSTRUCTIONS);
	}
	private int machineCode;
	private String commandName;
	private int argLen;
	private MCSInstruction(int cmd, String cmdname, int arglen) {
		this.commandName = cmdname;
		this.machineCode = cmd;
		this.argLen = arglen;
	}
	public int getMachineCode() {
		return machineCode;
	}
	public String getCommandName() {
		return commandName;
	}
	public int getArgLen() {
		return argLen;
	}
	public static MCSInstruction fromString(String s) {
		String[] contents = s.split(":");
		if(contents.length != 4) {
			return null;
		}
		return new MCSInstruction(Integer.parseInt(contents[0], 16), contents[2] +" "+contents[3], Integer.parseInt(contents[1])-1);
	}
	@Override
	public String toString() {
		return "HEX: "+Integer.toHexString(machineCode)+ " NAME: "+commandName;
	}
}
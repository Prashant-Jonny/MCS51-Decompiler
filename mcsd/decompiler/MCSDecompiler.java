package mcsd.decompiler;

import java.util.Map;

import mcsd.instruction.MCSInstruction;

public class MCSDecompiler {
	private Map<Integer, MCSInstruction> instructions;
	public MCSDecompiler(Map<Integer,MCSInstruction> instructionTable) {
		instructions = instructionTable;
	}
	public String[] decompile(int[] data) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < data.length; i++) {
			MCSInstruction cmdInstruction = instructions.get(data[i]);
			switch(cmdInstruction.getArgLen()) {
			case 0:
				builder.append(cmdInstruction.getCommandName()+"\n");
				break;
			case 1:
				builder.append(cmdInstruction.getCommandName().replace("arg", Integer.toHexString(data[i+1]).toUpperCase()+"h")+"\n");
				i = i + cmdInstruction.getArgLen();
				break;
			case 2:
				if(cmdInstruction.getMachineCode() == 0x02 || cmdInstruction.getMachineCode() == 0x12){
					String argument = Integer.toHexString(data[i+1]).toUpperCase()+Integer.toHexString(data[i+2]).toUpperCase()+"h";
					builder.append(cmdInstruction.getCommandName().replace("arg", argument)+"\n");
					i = i + cmdInstruction.getArgLen();
					break;
				}
				builder.append(cmdInstruction.getCommandName().replace("arg", Integer.toHexString(data[i+1]).toUpperCase()+"h")
						.replace("arg2", Integer.toHexString(data[i+2]).toUpperCase()+"h")+"\n");
				i = i + cmdInstruction.getArgLen();
				break;
			default:
				throw new NullPointerException("Invalid Instruction");	
			}
		}
		return builder.toString().split("\n");
	}
}

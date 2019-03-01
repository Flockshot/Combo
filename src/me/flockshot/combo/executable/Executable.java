package me.flockshot.combo.executable;

public interface Executable
{
	String getIdentifier();
	String getValue();	
	void setValue(String value);
	boolean passesValidity(String value);
}

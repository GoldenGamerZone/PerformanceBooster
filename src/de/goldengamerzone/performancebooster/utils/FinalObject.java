package de.goldengamerzone.performancebooster.utils;

/*
 * []===================================[] 
 *     FinalObject by GoldenGamerZone
 * []===================================[]
 */

public class FinalObject<type> {
	private type obj;
	
	public FinalObject() {
		this.obj = null;
	}
	
	public FinalObject(type object) {
		this.obj = object;
	}
	
	public void set(type object) {
		this.obj = object;
	}
	
	public type get() {
		return this.obj;
	}
}

package org.mess.pridict.model;

public class currStoke {
    private String prName;
    private int used_qty;
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public int getUsed_qty() {
		return used_qty;
	}
	public void setUsed_qty(int used_qty) {
		this.used_qty = used_qty;
	}
	public currStoke(String prName, int used_qty) {
		this.prName = prName;
		this.used_qty = used_qty;
	}
	public currStoke() {
	}
    
    
}

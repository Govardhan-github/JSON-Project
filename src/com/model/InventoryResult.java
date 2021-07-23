package com.model;

public class InventoryResult {
	
	String item;
	long totalWeight;
	long totalPrice;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public long getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(long totalWeight) {
		this.totalWeight = totalWeight;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "InventoryResult [item=" + item + ", totalWeight=" + totalWeight + ", totalPrice=" + totalPrice + "]";
	}

}

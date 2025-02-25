package Junit;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

public class OrderService {
	private int stock = 10;
	private double discount = 0.10;
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double calPrice(double price, int quantity) {
		return price*quantity*(1-discount);
		
	}
	
	public boolean placeOrder(int quantity) {
		if(quantity>stock) {
			throw new IllegalArgumentException("Insufficient stock");
		}
		stock -= quantity;
		return true;
	}
	
	
}

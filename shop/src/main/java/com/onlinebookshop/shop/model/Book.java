package com.onlinebookshop.shop.model;
public class Book {
	private int id, author_id;
	private String title;
	private double price;
	public Book(int id, int author_id, String title, double price) {
		super();
		this.id = id;
		this.author_id = author_id;
		this.title = title;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", author_id=" + author_id + ", title=" + title + ", price=" + price + "]";
	}
 
}
package model;

public class Car {
	
	/*Attributes*/
	String type;
	int vin;
	String brand;
	String color;
	String engineType;
	double price;
	int year;
	
	public Car(String type, int vin, String brand, String color, String engineType, double price, int year)
	{
		this.type = type;
		this.vin = vin;
		this.brand = brand;
		this.color = color;
		this.engineType = engineType;
		this.price = price;
		this.year = year;
	}
	/*Getters and Setters*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVin() {
		return vin;
	}
	public void setVin(int vin) {
		this.vin = vin;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
